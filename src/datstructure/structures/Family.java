package datstructure.structures;

import java.util.List;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.Knowledge;
import datstructure.FieldType;
import gui.GUI;


/**
 * Represents the file dbfamily.dat
 *
 * @author MarcoForlini
 */
@DatStructureParse (Vanilla = ParseState.UNKNOWN_PARSED, AOC = ParseState.UNKNOWN_PARSED)
public class Family extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Family instance = new Family ();

	/**
	 * Creates a new {@link Family}
	 */
	private Family () {
		super ("Families", "dbfamily.dat", true, 0, 1, 0, 0, 1, 2, 4, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[] {
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct ("Hovercraft, UFO, Rock thrower", FieldType.INTEGER),
				new FieldStruct ("Frigate", FieldType.INTEGER), new FieldStruct ("Machine gun", FieldType.INTEGER), new FieldStruct ("Galley", FieldType.INTEGER), new FieldStruct ("Tank", FieldType.INTEGER),
				new FieldStruct ("AT Gun", FieldType.INTEGER), new FieldStruct ("Catapult, Bombard", FieldType.INTEGER), new FieldStruct ("AA Tower, Stinger, Flat halftrack", FieldType.INTEGER), new FieldStruct ("<Unused by objects>", FieldType.INTEGER, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_UNCERTAIN),
				new FieldStruct ("Sea king", FieldType.INTEGER), new FieldStruct ("Field weapon", FieldType.INTEGER), new FieldStruct ("<Unused by objects>", FieldType.INTEGER, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Fighter, Cruiser", FieldType.INTEGER),
				new FieldStruct ("Cavalry spear", FieldType.INTEGER), new FieldStruct ("Pre-atomic gun infantry", FieldType.INTEGER), new FieldStruct ("ICBM", FieldType.INTEGER), new FieldStruct ("Cavalry gun", FieldType.INTEGER),
				new FieldStruct ("Halbedier", FieldType.INTEGER), new FieldStruct ("Submarine", FieldType.INTEGER), new FieldStruct ("Ram, Sampson", FieldType.INTEGER), new FieldStruct ("Animals", FieldType.INTEGER),
				new FieldStruct ("Archer, Javelin", FieldType.INTEGER), new FieldStruct ("<Unused by objects>", FieldType.INTEGER, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct ("Mech Minotaur", FieldType.INTEGER), new FieldStruct ("<Unused by objects>", FieldType.INTEGER, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_UNCERTAIN),
				new FieldStruct ("Elite Guard", FieldType.INTEGER), new FieldStruct ("Grigor II, Mech Hyperion", FieldType.INTEGER), new FieldStruct ("Mech Zeus", FieldType.INTEGER), new FieldStruct ("Mech Pandora", FieldType.INTEGER),
				new FieldStruct ("Mech Ares", FieldType.INTEGER), new FieldStruct ("AT Aircraft", FieldType.INTEGER), new FieldStruct ("Bomber", FieldType.INTEGER), new FieldStruct ("Modern/Future gun infantry", FieldType.INTEGER),
				new FieldStruct ("AT Tank", FieldType.INTEGER), new FieldStruct ("Battleship", FieldType.INTEGER), new FieldStruct ("AT Helicopter", FieldType.INTEGER), new FieldStruct ("Mech Poseidon, Mech Hades", FieldType.INTEGER),
				new FieldStruct ("Medic, Mech Apollo", FieldType.INTEGER), new FieldStruct ("Citizen, Diplomat", FieldType.INTEGER), new FieldStruct ("Crossbow, Sniper, Mech Tempest", FieldType.INTEGER), new FieldStruct ("Carrier fighter", FieldType.INTEGER),
				new FieldStruct ("Gunship Helicopter", FieldType.INTEGER), new FieldStruct ("Persian cavalry", FieldType.INTEGER), new FieldStruct ("Barbarian, Celtic, Gallic", FieldType.INTEGER), new FieldStruct ("Tower", FieldType.INTEGER),
				new FieldStruct ("Flamethrower, Howitzer", FieldType.INTEGER), new FieldStruct ("Viking", FieldType.INTEGER), new FieldStruct ("Priest, Priest Tower", FieldType.INTEGER), new FieldStruct ("Sword", FieldType.INTEGER),
				new FieldStruct ("Cavalry sword", FieldType.INTEGER), new FieldStruct ("Partisan", FieldType.INTEGER), new FieldStruct ("Atomic Submarine", FieldType.INTEGER), new FieldStruct ("Bazooka", FieldType.INTEGER),
				new FieldStruct ("Spear", FieldType.INTEGER), new FieldStruct ("Cavalry archer", FieldType.INTEGER), new FieldStruct ("Heroes", FieldType.INTEGER), new FieldStruct ("Hand Cannoner", FieldType.INTEGER),
				new FieldStruct ("Mortar", FieldType.INTEGER), new FieldStruct ("Red Baron", FieldType.INTEGER), new FieldStruct ("Fishing boat", FieldType.INTEGER), new FieldStruct ("Anti-Missile Battery", FieldType.INTEGER),
				new FieldStruct ("Capitol ship", FieldType.INTEGER), new FieldStruct ("Space fighter", FieldType.INTEGER), new FieldStruct ("Space corvette", FieldType.INTEGER), new FieldStruct ("ICBM (Power)", FieldType.INTEGER),
				new FieldStruct ("Space turret", FieldType.INTEGER), FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4
		};
		newEntryValues = new Object[] {
				"<New family>", 0, -1, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100,
		};
	}

	@Override
	public int indexExtraFields () {
		return -1;
	}

	@Override
	public boolean hasCustomEntryName () {
		return false;
	}

	@Override
	public String getCustomEntryName (int index, List <Object> values) {
		return null;
	}

	@Override
	public String getEntryDescription (Entry entry) {
		return null;
	}

}
