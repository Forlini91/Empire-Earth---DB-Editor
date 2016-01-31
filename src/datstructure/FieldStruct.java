package datstructure;


import java.awt.Color;


public interface FieldStruct {
	
	Color COLOR_ID = Color.BLUE;
	Color COLOR_LINK = new Color(50, 200, 50);

	String getName ();
	
	Type getType ();
	
	int getSize ();
	
	Knowledge getKnowledge ();
	
	boolean isEditable ();
	
	Color getColor ();
	
	DatStructure getLinkToStruct ();
	
}
