package datmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import datstructure.DatStructure;
import datstructure.Entry;
import datstructure.EntryGroup;
import datstructure.FieldStruct;
import datstructure.Link;


/**
 * Manage a single DatFile with save/load methods
 * @author MarcoForlini
 */
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
	 * @throws IOException	If any IO exception occur
	 */
	public DatFileManager(DatFile datFile) throws IOException {
		this.datFile = datFile;
		datStructure = datFile.datStructure;
	}

	/**
	 * Read the whole file, perform regular updates of the progress on the GUI and return the content read.
	 * Support multi-thread loadings: you can load many files at once and display a single progress bar for them all.
	 * @param update	The method to call to update the state on the GUI
	 * @param threadIndex	Which thread is this
	 * @return	The list of EntryGroup read from the file, if any
	 * @throws IOException					If a problem occurs while reading
	 * @throws BufferUnderflowException		If loading the wrong/encrypted files
	 */
	public DatFile read(BiConsumer<Float, Integer> update, int threadIndex) throws IOException, BufferUnderflowException {
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
		for (EntryGroup entryGroup : entryGroups){
			System.out.println("Load file: " + datFile.getName() + "  >  Group: " + entryGroup + "  >  Num entries: " + entryGroup.entries.size());
		}
		datFile.loadData(entryGroups);
		return datFile;
	}
	
	/**
	 * Read a single EntryGroup from the file, perform regular updates on of the progress on the GUI and return the list of entries.
	 * Support multi-thread loadings: you can load many files at once and display a single progress bar for them all.
	 * @param reader	The reader used to read the file
	 * @param update	The method to call to update the state on the GUI
	 * @param threadIndex	Which thread is this
	 * @return	The list of Entry read from the file, if any
	 * @throws IOException					If a problem occurs while reading
	 * @throws BufferUnderflowException		If loading the wrong/encrypted files
	 */
	public EntryGroup readGroup(DatReader reader, BiConsumer<Float, Integer> update, int threadIndex) throws IOException, BufferUnderflowException {
		boolean defineNumEntries = datStructure.defineNumEntries;
		int numEntries;
		if (datStructure.defineNumEntries){
			numEntries = reader.readInt() + datStructure.adjustNumEntries;
		} else {
			numEntries = -1;
		}
		int numFields = datStructure.fieldStructs.length;
		List<Entry> entries;
		if (defineNumEntries) {
			try{
				entries = new ArrayList<>(numEntries);
			} catch (OutOfMemoryError e){
				throw new IOException(e);
			}
		} else {
			entries = new ArrayList<>();
		}
		Entry entry;
		FieldStruct fieldStruct;
		Object read;
		int size;
		//		StringBuilder sb;
		int i = 0;
		try{
			for (i = 0; (defineNumEntries && i < numEntries) || (!defineNumEntries && reader.getRemaining() > 0); i++) {	//<= because dbTechTree works differently...
				List<Object> values = new ArrayList<>(numFields);

				for (int j = 0; j < numFields; j++){
					fieldStruct = datStructure.fieldStructs[j];

					switch(fieldStruct.getType()){
						case STRING:
							if (fieldStruct.getIndexSize() >= 0){
								size = (int) values.get(fieldStruct.getIndexSize());
							} else {
								size = fieldStruct.getSize();
							}
							if (size > 0){
								read = reader.readString(size);
							} else {
								read = "";
							}
							break;
						case FLOAT:
							read = reader.readFloat();
							break;
						default:
							if (fieldStruct.getSize() == 1){
								read = reader.readByte();
							} else {
								read = reader.readInt();
							}
					}
					values.add(read);
				}
				fieldStruct = datStructure.extraField;
				if (fieldStruct != null){
					int numExtra = (int) values.get(datStructure.getIndexExtraFields());
					for (int j = 0; j < numExtra; j++){
						read = reader.readInt();
						values.add(read);
					}
				}
				entry = new Entry(datStructure, false, i, i, values);
				entries.add(entry);
				update.accept((float) (1.0 - (double) reader.getRemaining() / fileSize), threadIndex);
			}
		} catch (Exception e){
			throw new IOException(e);
		}
		return new EntryGroup(datStructure, entries);
	}


	/**
	 * Save the given list of EntryGroup to the file and perform regular updates of the progress on the GUI.
	 * @param update		The method to call to update the state on the GUI
	 * @throws IOException	If a problem occurs while saving
	 */
	public void save(Runnable update) throws IOException {
		if (datFile.isLoaded()){
			File origFile = new File(datFile.getAbsolutePath() + ".orig");
			if (!origFile.exists()) {
				Files.copy(datFile.toPath(), origFile.toPath());
			}
			File newBackup = new File(datFile.getAbsolutePath() + ".tempbak");
			Files.deleteIfExists(newBackup.toPath());
			Files.move(datFile.toPath(), newBackup.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			int numBaseFields = datStructure.fieldStructs.length;
			Entry entry;
			FieldStruct fieldStruct;
			Link link;
			int numEntries;
			int numFields;
			int size;
			boolean defineNumEntries = datStructure.defineNumEntries;
			
			List<Entry> allEntries = datFile.getAllEntries(false);
			try (DatWriter writer = new DatWriter(datFile, allEntries)) {
				for (EntryGroup entryGroup : datFile){
					numEntries = entryGroup.entries.size();
					if (defineNumEntries){
						writer.writeInt(numEntries - datStructure.adjustNumEntries);
					}
					System.out.println("Save file: " + datFile.getName() + "  >  Group: " + entryGroup + "  >  Num entries: " + numEntries);
					
					//				StringBuilder sb;
					for (int i = 0; i < numEntries; i++){
						update.run();
						entry = entryGroup.entries.get(i);
						numFields = entry.values.size();
						for (int j = 0; j < numFields; j++){
							if (j < numBaseFields) {
								fieldStruct = datStructure.fieldStructs[j];
							} else {
								fieldStruct = datStructure.extraField;
							}
							try{
								switch (fieldStruct.getType()){
									case STRING:
										if (fieldStruct.getIndexSize() >= 0){
											size = (int) entry.values.get(fieldStruct.getIndexSize());
										} else {
											size = fieldStruct.getSize();
										}
										if (size > 0){
											writer.writeString((String) entry.values.get(j), size);
										}
										break;
									case FLOAT:
										writer.writeFloat((float) entry.values.get(j));
										break;
									default:
										if (fieldStruct.getSize() == 1) {
											writer.writeByte((int) entry.values.get(j));
										} else if (fieldStruct.linkToStruct != null && fieldStruct.linkToStruct.datFile != null && Core.LINK_SYSTEM){
											link = (Link) entry.values.get(j);
											writer.writeInt(link.target.ID);
										} else {
											writer.writeInt((int) entry.values.get(j));
										}
								}
							} catch (Exception e){
								System.out.println("Error while writing entry: " + entry + "   | Field: (" + j + ") " + fieldStruct + "   >   " + entry.values.get(j));
								throw e;
							}
							//						sb = new StringBuilder().append('\t').append('\t').append('(').append(field.type).append(' ').append(field.size).append(')');
							//						sb.append(' ').append(field.name != null ? field.name : "Unknown").append(':').append(' ').append(entry.values.get(j));
							//						System.out.println(sb);
						}
						//					System.out.println('\n');
					}
				}
			} catch (Exception e){
				Files.move(newBackup.toPath(), datFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				Files.deleteIfExists(newBackup.toPath());
				throw new IOException(e);
			}
			
			File oldBackup = new File(datFile.getAbsolutePath() + ".bak");
			Files.move(newBackup.toPath(), oldBackup.toPath(), StandardCopyOption.REPLACE_EXISTING);
			Files.deleteIfExists(newBackup.toPath());
		}
	}

	
	
}
