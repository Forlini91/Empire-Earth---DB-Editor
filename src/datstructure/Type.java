package datstructure;

import java.util.function.Supplier;

/**
 * Type of the fields.
 * @author MarcoForlini
 *
 */
public enum Type {
	/** Either 0 or 1 */
	BOOLEAN,
	/** An integer value */
	INTEGER,
	/** A float value */
	FLOAT,
	/** A string value */
	STRING,

	//These are IDs used by specific fields
	ID_AREA_EFFECT (() -> DatStructure.DB_AREA_EFFECT),
	ID_BUTTON (() -> DatStructure.DB_BUTTONS),
	ID_CALAMITY (() -> DatStructure.DB_CALAMITY),
	ID_CIVILIZATION (() -> DatStructure.DB_CIVILIZATION),
	ID_FAMILY (() -> DatStructure.DB_FAMILY),
	ID_GAME_VARIANT (() -> DatStructure.DB_GAME_VARIANT),
	ID_MUSIC (() -> DatStructure.DB_MUSIC),
	ID_OBJECT (() -> DatStructure.DB_OBJECT),
	ID_STARTING_RESOURCHES (() -> DatStructure.DB_STARTING_RESOURCHES),
	ID_TECH (() -> DatStructure.DB_TECH_TREE),
	ID_HOTKEY (() -> DatStructure.DB_HOTKEY),
	ID_UNIT_SET (() -> DatStructure.DB_UNITSET),
	ID_UPGRADE (() -> DatStructure.DB_UPGRADE),
	ID_WEAPON_TO_HIT (() -> DatStructure.DB_WEAPON_TO_HIT),
	
	;
	
	public final Supplier<DatStructure> datStructure;
	
	Type(){
		datStructure = null;
	}
	
	Type(Supplier<DatStructure> datStructure){
		this.datStructure = datStructure;
	}
}