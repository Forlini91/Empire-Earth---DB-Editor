package dbstructure;

import java.util.function.Supplier;

public enum Type {
	BOOLEAN,
	INTEGER,
	FLOAT,
	STRING,
	
	ID_AREA_EFFECT (() -> DatStructure.DB_AREA_EFFECT),
	ID_BUTTON (() -> DatStructure.DB_BUTTONS),
	ID_CALAMITY (() -> DatStructure.DB_CALAMITY),
	ID_CIVILIZATION (() -> DatStructure.DB_CIVILIZATION),
	ID_FAMILY (() -> DatStructure.DB_FAMILY),
	//ID_GRAPHIC (() -> DatStructure.DB_GRAPHIC),
	ID_GAME_VARIANT (() -> DatStructure.DB_GAME_VARIANT),
	ID_MUSIC (() -> DatStructure.DB_MUSIC),
	ID_OBJECT (() -> DatStructure.DB_OBJECT),
	ID_STARTING_RESOURCHES (() -> DatStructure.DB_STARTING_RESOURCHES),
	ID_TECH (() -> DatStructure.DB_TECH_TREE),
	ID_UIHOTKEY (() -> DatStructure.DB_UIHOTKEY),
	ID_UPGRADE (() -> DatStructure.DB_UPGRADE),
	ID_WEAPON_TO_HIT (() -> DatStructure.DB_WEAPON_TO_HIT),
	ID_LANGUAGE (null)

	;

	public final Supplier<DatStructure> datStructure;

	Type(){
		datStructure = null;
	}

	Type(Supplier<DatStructure> datStructure){
		this.datStructure = datStructure;
	}
}