package datstructure;


public interface DatStructure {
	
	String getName ();
	
	String getFileName ();
	
	int getAdjustNumEntries ();
	
	boolean isSupportNumEntriesAlteration ();
	
	int getIndexName ();
	
	int getIndexSequence ();
	
	int getIndexID ();
	
	int getIndexCountExtra ();
	
	FieldStruct getExtraEntry ();
	
	FieldStruct[] getEntries ();
	
	Object[] getDefaultValues ();
	
	int compareTo(DatStructure datStructure);
	
}
