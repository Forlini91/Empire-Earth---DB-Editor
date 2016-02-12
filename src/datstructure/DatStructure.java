package datstructure;

import java.util.function.Function;

public interface DatStructure {
	
	String getName ();
	
	String getFileName ();
	
	boolean defineNumEntries ();
	
	int getAdjustNumEntries ();
	
	int getMinSeq ();
	
	int getMinID ();
	
	int getIndexName ();
	
	int getIndexSequence ();
	
	int getIndexID ();
	
	int getIndexCountExtra ();
	
	FieldStruct getExtraEntry ();
	
	FieldStruct[] getFieldStructs ();
	
	Object[] getNewEntryValues ();
	
	int compareTo(DatStructure datStructure);

	Function <Entry, String> getNameBuilder ();

	int getNumBytes ();
	
}
