package datmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.BufferUnderflowException;
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
			entryGroups.get(0).name = datStructure.getFileName();
		}
		for (EntryGroup entryGroup : entryGroups){
			System.out.println("Load file: " + datFile.getName() + "  >  Group: " + entryGroup + "  >  Num entries: " + entryGroup.entries.size());
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
		boolean defineNumEntries = datStructure.defineNumEntries();
		int numEntries;
		if (datStructure.defineNumEntries()){
			numEntries = reader.readInt() + datStructure.getAdjustNumEntries();
		} else {
			numEntries = -1;
		}
		int numFields = datStructure.getEntries().length;
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
				List<Object> values = new ArrayList<Object>(numFields);
				
				for (int j = 0; j < numFields; j++){
					fieldStruct = datStructure.getEntries()[j];
					size = fieldStruct.getSize();
					
					switch(fieldStruct.getType()){
						case STRING:
							if (fieldStruct.getIndexStringLengthExtra() >= 0){
								size += (int) values.get(fieldStruct.getIndexStringLengthExtra());
							}
							if (size > 0){
								read = reader.readString(size);
							} else {
								read = "";
							}
							break;
						case FLOAT:
							read = reader.readFloat(); break;
						default:
							if (size > 1){
								read = reader.readInt(); break;
							} else {
								read = reader.readByte(); break;
							}
					}
					values.add(read);
				}
				if (datStructure.getIndexCountExtra() >= 0 && datStructure.getExtraEntry() != null){
					int numExtra = (int) values.get(datStructure.getIndexCountExtra());
					fieldStruct = datStructure.getExtraEntry();
					for (int j = 0; j < numExtra; j++){
						read = reader.readInt();
						values.add(read);
					}
				}
				entry = new Entry(datStructure, values, i);
				//				System.out.println("Load entry: " + datStructure.getName() + " - " + i + " - " + entry.getName());
				entries.add(entry);
				update.accept((float) (1 - (double) reader.getRemaining() / fileSize), threadIndex);
			}
		} catch (BufferUnderflowException e){
			throw new IOException(e);
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
		File origFile = new File(datFile.getAbsolutePath() + ".orig");
		if (!origFile.exists()) {
			Files.copy(datFile.toPath(), origFile.toPath());
		}
		File backup = new File(datFile.getAbsolutePath() + ".bak");
		Files.deleteIfExists(backup.toPath());
		Files.copy(datFile.toPath(), backup.toPath());
		
		
		int numBaseFields = datStructure.getEntries().length;
		Entry entry;
		FieldStruct fieldStruct;
		int numEntries;
		int numFields;
		int size;
		boolean defineNumEntries = datStructure.defineNumEntries();
		try (DatWriter writer = new DatWriter(datFile, datContent)) {
			for (EntryGroup entryGroup : datContent){
				numEntries = entryGroup.entries.size();
				if (defineNumEntries){
					writer.writeInt(numEntries - datStructure.getAdjustNumEntries());
				}
				System.out.println("Save file: " + datFile.getName() + "  >  Group: " + entryGroup + "  >  Num entries: " + numEntries);

				//				StringBuilder sb;
				for (int i = 0; i < numEntries; i++){
					update.run();
					entry = entryGroup.entries.get(i);
					numFields = entry.values.size();
					for (int j = 0; j < numFields; j++){
						if (j < numBaseFields) {
							fieldStruct = datStructure.getEntries()[j];
						} else {
							fieldStruct = datStructure.getExtraEntry();
						}
						size = fieldStruct.getSize();
						switch (fieldStruct.getType()){
							case STRING:
								if (fieldStruct.getIndexStringLengthExtra() >= 0){
									size += (int) entry.values.get(fieldStruct.getIndexStringLengthExtra());
								}
								if (size > 0){
									writer.writeString((String) entry.values.get(j), size);
								}
								break;
							case FLOAT:
								writer.writeFloat((float) entry.values.get(j)); break;
							default:
								if (size > 1) {
									writer.writeInt((int) entry.values.get(j)); break;
								} else {
									writer.writeByte((int) entry.values.get(j)); break;
								}
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
