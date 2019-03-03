package datmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import datstructure.DatStructure;
import datstructure.Entry;
import datstructure.EntryGroup;
import datstructure.FieldStruct;
import datstructure.Link;
import datstructure.structures.RandomMap;
import gui.DialogProgressBar.ProgressUpdater;

/**
 * Manage a single DatFile with save/load methods
 *
 * @author MarcoForlini
 */
public class DatFileManager {

	/** The file to read. */
	private final DatFile datFile;
	/** The structure of the file. */
	private final DatStructure datStructure;
	/** The size of the file. */
	private long fileSize;

	/**
	 * Create a new DatFileManager to read or write the given datFile.
	 *
	 * @param datFile The file to load with the relative structure
	 * @throws IOException If any IO exception occur
	 */
	public DatFileManager(DatFile datFile) {
		this.datFile = datFile;
		datStructure = datFile.datStructure;
	}

	/**
	 * Read the whole file, perform regular updates of the progress on the GUI and return the content read.
	 * Support multi-thread loadings: you can load many files at once and display a single progress bar for them all.
	 *
	 * @param progressUpdater The method to call to update the state on the GUI
	 * @param threadIndex     Which thread is this
	 * @throws IOException              If a problem occurs while reading
	 * @throws BufferUnderflowException If loading the wrong/encrypted files
	 */
	public void read(ProgressUpdater progressUpdater, int threadIndex) throws IOException, BufferUnderflowException {
		if (!datFile.exists()) {
			throw new FileNotFoundException(datFile.toString());
		}
		final List<EntryGroup> entryGroups = new ArrayList<>();
		try (DatReader reader = new DatReader(datFile)) {
			fileSize = reader.getRemaining();
			while (reader.getRemaining() > 0) {
				entryGroups.add(readGroup(reader, progressUpdater, threadIndex));
			}
		}
		if (entryGroups.size() == 1) {
			entryGroups.get(0).name = datStructure.fileName;
		}
		if (Settings.DEBUG) {
			for (final EntryGroup entryGroup : entryGroups) {
				System.out.println("Load file: " + datFile.getName() + "  >  Group: " + entryGroup + "  >  Num entries: " + entryGroup.entries.size());
			}
		}
		datFile.loadData(entryGroups);
	}

	/**
	 * Read a single EntryGroup from the file, perform regular updates on of the progress on the GUI and return the list of
	 * entries.
	 * Support multi-thread loadings: you can load many files at once and display a single progress bar for them all.
	 *
	 * @param reader          The reader used to read the file
	 * @param progressUpdater The method to call to update the state on the GUI
	 * @param threadIndex     Which thread is this
	 * @return The list of Entry read from the file, if any
	 * @throws IOException              If a problem occurs while reading
	 * @throws BufferUnderflowException If loading the wrong/encrypted files
	 */
	public EntryGroup readGroup(DatReader reader, ProgressUpdater progressUpdater, int threadIndex) throws IOException, BufferUnderflowException {
		final boolean defineNumEntries = datStructure.defineNumEntries;
		int numEntries;
		if (datStructure == RandomMap.instance) {
			reader.readInt();
		}
		if (datStructure.defineNumEntries) {
			numEntries = reader.readInt() + datStructure.adjustNumEntries;
		} else {
			numEntries = -1;
		}

		final int numFields = datStructure.fieldStructs.length;
		List<Entry> entries;
		if (defineNumEntries) {
			try {
				if (numEntries > 10000 || numEntries < 0) {
					System.out.println(datFile.toString() + ' ' + numEntries);
				}
				entries = new ArrayList<>(numEntries);
			} catch (final OutOfMemoryError e) {
				throw new IOException(e);
			}
		} else {
			entries = new ArrayList<>();
		}
		Entry entry;
		FieldStruct fieldStruct;
		Object read;
		int size;
		// StringBuilder sb;
		int i = 0;
		try {
			for (i = 0; (defineNumEntries && i < numEntries) || (!defineNumEntries && reader.getRemaining() > 0); i++) { // <= because dbTechTree works differently...
				final List<Object> values = new ArrayList<>(numFields);

				for (int j = 0; j < numFields; j++) {
					fieldStruct = datStructure.fieldStructs[j];

					switch (fieldStruct.getType()) {
						case STRING:
						case DYNAMIC_STRING:
							if (fieldStruct.getIndexSize() >= 0) {
								size = (int) values.get(fieldStruct.getIndexSize());
							} else {
								size = fieldStruct.getSize();
							}
							if (size > 0) {
								read = reader.readString(size);
							} else {
								read = "";
							}
							break;
						case FLOAT:
							read = reader.readFloat();
							break;
						default:
							if (fieldStruct.getSize() == 1) {
								read = reader.readByte();
							} else {
								read = reader.readInt();
							}
					}
					values.add(read);
				}
				fieldStruct = datStructure.extraField;
				if (fieldStruct != null) {
					final int numExtra = (int) values.get(datStructure.indexExtraFields());
					for (int j = 0; j < numExtra; j++) {
						read = reader.readInt();
						values.add(read);
					}
				}
				String name = null;
				if (datStructure.hasCustomEntryName()) {
					name = datStructure.getCustomEntryName(i, values);
				}
				entry = new Entry(datStructure, false, name, i, i, values);
				entries.add(entry);
				progressUpdater.update(1.0 - (double) reader.getRemaining() / fileSize, threadIndex);
			}
		} catch (final Exception e) {
			throw new IOException(e);
		}
		return new EntryGroup(datStructure, entries);
	}

	/**
	 * Save the given list of EntryGroup to the file and perform regular updates of the progress on the GUI.
	 *
	 * @param update The method to call to update the state on the GUI
	 * @throws IOException If a problem occurs while saving
	 */
	public void save(Runnable update) throws IOException {
		if (datFile.isLoaded()) {
			final File origFile = new File(datFile.getAbsolutePath() + ".orig");
			if (!origFile.exists()) {
				Files.copy(datFile.toPath(), origFile.toPath());
			}
			final File newBackup = new File(datFile.getAbsolutePath() + ".tempbak");
			Files.deleteIfExists(newBackup.toPath());
			Files.move(datFile.toPath(), newBackup.toPath(), StandardCopyOption.REPLACE_EXISTING);

			final int numBaseFields = datStructure.fieldStructs.length;
			Entry entry;
			FieldStruct fieldStruct;
			Link link;
			int numEntries;
			int numFields;
			int size;
			final boolean defineNumEntries = datStructure.defineNumEntries;

			final List<Entry> allEntries = datFile.getAllEntries(false);
			try (DatWriter writer = new DatWriter(datFile, allEntries)) {
				if (datFile.datStructure == RandomMap.instance) {
					final int numGroups = (int) datFile.getAllEntries(false).stream().mapToInt(entry2 -> entry2.get(1)).distinct().count();
					writer.writeInt(numGroups); // First 4 bytes are for value "Num groups"
				}

				for (final EntryGroup entryGroup : datFile) {
					numEntries = entryGroup.entries.size();
					if (defineNumEntries) {
						writer.writeInt(numEntries - datStructure.adjustNumEntries);
					}
					System.out.println("Save file: " + datFile.getName() + "  >  Group: " + entryGroup + "  >  Num entries: " + numEntries);

					for (int i = 0; i < numEntries; i++) {
						update.run();
						entry = entryGroup.entries.get(i);
						numFields = entry.size();
						for (int j = 0; j < numFields; j++) {
							if (j < numBaseFields) {
								fieldStruct = datStructure.fieldStructs[j];
							} else {
								fieldStruct = datStructure.extraField;
							}
							try {
								switch (fieldStruct.getType()) {
									case STRING:
									case DYNAMIC_STRING:
										if (fieldStruct.getIndexSize() >= 0) {
											size = entry.get(fieldStruct.getIndexSize());
										} else {
											size = fieldStruct.getSize();
										}
										if (size > 0) {
											writer.writeString(entry.get(j), size);
										}
										break;
									case FLOAT:
										writer.writeFloat(entry.get(j));
										break;
									default:
										if (fieldStruct.getSize() == 1) {
											writer.writeByte(entry.get(j));
										} else if (fieldStruct.linkToStruct != null && fieldStruct.linkToStruct.datFile != null) {
											link = entry.get(j);
											writer.writeInt(link.target.getID());
										} else {
											writer.writeInt(entry.get(j));
										}
								}
							} catch (final Exception e) {
								Util.printException(null, e, "Error while writing entry: " + entry + "   | Field: (" + j + ") " + fieldStruct + "   >   " + entry.get(j), "Error while saving", true);
								throw e;
							}
						}
					}
				}
			} catch (final Exception e) {
				Files.move(newBackup.toPath(), datFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				Files.deleteIfExists(newBackup.toPath());
				throw new IOException(e);
			}

			final File oldBackup = new File(datFile.getAbsolutePath() + ".bak");
			Files.move(newBackup.toPath(), oldBackup.toPath(), StandardCopyOption.REPLACE_EXISTING);
			Files.deleteIfExists(newBackup.toPath());
		}
	}

}
