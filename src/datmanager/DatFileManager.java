package datmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import datstructure.DatContent;
import datstructure.DatStructure;
import datstructure.Entry;
import datstructure.EntryGroup;
import datstructure.FieldStruct;

public class DatFileManager {

	/** The file to read. */
	private DatFile datFile;
	/** The structure of the file. */
	private DatStructure datStructure;
	/** The size of the file. */
	private long fileSize;
	
	/**
	 * Create a new DatFileManager to read or write the given datFile.
	 * @param datFile	The file to load with the relative structure
	 * @throws IOException
	 */
	public DatFileManager(DatFile datFile, DatStructure datStructure) throws IOException {
		this.datFile = datFile;
		this.datStructure = datStructure;
		File backup = new File(datFile.getAbsolutePath() + ".orig");
		if (!backup.exists()){
			Files.copy(datFile.toPath(), backup.toPath());
		} else {
			Files.deleteIfExists(datFile.toPath());
			Files.copy(backup.toPath(), datFile.toPath());
		}
	}
	
	/**
	 * Read the whole file, perform regular updates of the progress on the GUI and return the content read.
	 * Support multi-thread loadings: you can load many files at once and display a single progress bar for them all.
	 * @param update	The method to call to update the state on the GUI
	 * @param threadIndex	Which thread is this
	 * @return	The list of EntryGroup read from the file, if any
	 * @throws IOException	If a problem occurs while reading
	 */
	public DatContent read(BiConsumer<Float, Integer> update, int threadIndex) throws IOException {
		if (!datFile.exists()){
			throw new FileNotFoundException(datFile.toString());
		}
		List<EntryGroup> entryGroups = new ArrayList<>();
		try (DatReader reader = new DatReader(datFile)) {
			fileSize = reader.getRemaining();
			while (reader.getRemaining() > 0) {
				entryGroups.add(readGroup(reader, update, threadIndex));
			}
		}
		if (entryGroups.size() == 1){
			entryGroups.get(0).name = datStructure.fileName;
		}
		return new DatContent(datFile, entryGroups);
	}

	/**
	 * Read a single EntryGroup from the file, perform regular updates on of the progress on the GUI and return the list of entries.
	 * Support multi-thread loadings: you can load many files at once and display a single progress bar for them all.
	 * @param reader	The reader used to read the file
	 * @param update	The method to call to update the state on the GUI
	 * @param threadIndex	Which thread is this
	 * @return	The list of Entry read from the file, if any
	 * @throws IOException	If a problem occurs while reading
	 */
	public EntryGroup readGroup(DatReader reader, BiConsumer<Float, Integer> update, int threadIndex) throws IOException {
		int numEntries = reader.readInt(4) + datStructure.adjustNumEntries;
		int numFields = datStructure.entries.length;
		System.out.println("Loading: " + datFile.getName() + "  >  Num entries: " + numEntries);
		List<Entry> entries = new ArrayList<Entry>(numEntries);
		Entry entry;
		FieldStruct fieldStruct;
		Object read;
		//			StringBuilder sb;
		for (int i = 0; i < numEntries; i++) {	//<= because dbTechTree works differently...
			List<Object> values = new ArrayList<Object>(numFields);
			for (int j = 0; j < numFields; j++){
				fieldStruct = datStructure.entries[j];
				switch(fieldStruct.type){
					case STRING:
						read = reader.readChars(fieldStruct.size); break;
					case FLOAT:
						read = reader.readFloat(fieldStruct.size); break;
					default:
						read = reader.readInt(fieldStruct.size); break;
				}
				values.add(read);
				//					sb = new StringBuilder().append('\t').append('\t').append('(').append(fieldStruct.type).append(' ').append(fieldStruct.size).append(')');
				//					sb.append(' ').append(fieldStruct.name).append(':').append(' ').append(read);
				//					System.out.println(sb);
			}
			if (datStructure.indexCountExtra >= 0 && datStructure.extraEntry != null){
				int numExtra = (int) values.get(datStructure.indexCountExtra);
				fieldStruct = datStructure.extraEntry;
				for (int j = 0; j < numExtra; j++){
					read = reader.readInt(fieldStruct.size);
					values.add(read);
				}
			}
			entry = new Entry(datStructure, values);
			entries.add(entry);
			update.accept((float) (1 - (double) reader.getRemaining() / fileSize), threadIndex);
			//				System.out.println("Element: " + i + " | " + entry);
		}
		return new EntryGroup(datStructure, entries);
	}
	
	
	/**
	 * Save the given list of EntryGroup to the file and perform regular updates of the progress on the GUI.
	 * @param entryGroups	The list of EntryGroup to save
	 * @param update		The method to call to update the state on the GUI
	 * @throws IOException	If a problem occurs while saving
	 */
	public void save(DatContent datContent, Runnable update) throws IOException {
		File backup = new File(datFile.getAbsolutePath() + ".bak");
		Files.deleteIfExists(backup.toPath());
		Files.copy(datFile.toPath(), backup.toPath());
		
		int numBaseFields = datStructure.entries.length;
		Entry entry;
		FieldStruct fieldStruct;
		int numEntries;
		int numFields;
		try (DatWriter writer = new DatWriter(datFile)) {
			for (EntryGroup entryGroup : datContent){
				numEntries = entryGroup.entries.size();
				writer.writeInt(numEntries - datStructure.adjustNumEntries, 4);
				System.out.println("Save file: " + datFile.getName() + "  >  Group: " + entryGroup + "  >  Num entries: " + numEntries);

				//				StringBuilder sb;
				for (int i = 0; i < numEntries; i++){
					update.run();
					entry = entryGroup.entries.get(i);
					numFields = entry.values.size();
					for (int j = 0; j < numFields; j++){
						if (j < numBaseFields) {
							fieldStruct = datStructure.entries[j];
						} else {
							fieldStruct = datStructure.extraEntry;
						}
						switch (fieldStruct.type){
							case STRING:
								if (entry.values.get(j).equals(Entry.NAME_NONE)){
									System.out.println("NO NAME!!!");
								}
								writer.writeChars((String) entry.values.get(j), fieldStruct.size); break;
							case FLOAT:
								writer.writeFloat((float) entry.values.get(j), fieldStruct.size); break;
							default:
								writer.writeInt((int) entry.values.get(j), fieldStruct.size); break;
						}
						//						sb = new StringBuilder().append('\t').append('\t').append('(').append(field.type).append(' ').append(field.size).append(')');
						//						sb.append(' ').append(field.name != null ? field.name : "Unknown").append(':').append(' ').append(entry.values.get(j));
						//						System.out.println(sb);
					}
					//					System.out.println('\n');
				}
			}
		}
	}
	
}
