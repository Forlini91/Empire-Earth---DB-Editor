package datstructure.structures;

import datstructure.DatStructure;
import datstructure.DatStructureParse;
import datstructure.DatStructureParse.ParseState;
import datstructure.FieldStruct;
import datstructure.Knowledge;
import datstructure.Type;
import gui.GUI;

/**
 * Represents the file dbfamily.dat
 * @author MarcoForlini
 */
@DatStructureParse(Vanilla = ParseState.UNKNOWN_PARSED, AOC = ParseState.UNKNOWN_PARSED)
public class Family extends DatStructure {

	/**
	 * Unique instance of this structure
	 */
	public static final Family instance = new Family();

	/**
	 * Creates a new {@link Family}
	 */
	private Family () {
		super("Families", "dbfamily.dat", true, 0, 1, 0, 0, 1, 2, 4, 125, 175);
	}

	@Override
	public void init () {
		fieldStructs = new FieldStruct[]{
				FieldStruct.NAME, FieldStruct.SEQ_NUMBER, FieldStruct.ID, new FieldStruct("Hovercraft, UFO, Rock thrower", Type.INTEGER),
				new FieldStruct("Frigate", Type.INTEGER), new FieldStruct("Machine gun", Type.INTEGER), new FieldStruct("Galley", Type.INTEGER), new FieldStruct("Tank", Type.INTEGER),
				new FieldStruct("AT Gun", Type.INTEGER), new FieldStruct("Catapult, Bombard", Type.INTEGER), new FieldStruct("AA Tower, Stinger, Flat halftrack", Type.INTEGER), new FieldStruct("<Unused by objects>", Type.INTEGER, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_UNCERTAIN),
				new FieldStruct("Sea king", Type.INTEGER), new FieldStruct("Field weapon", Type.INTEGER), new FieldStruct("<Unused by objects>", Type.INTEGER, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Fighter, Cruiser", Type.INTEGER),
				new FieldStruct("Cavalry spear", Type.INTEGER), new FieldStruct("Pre-atomic gun infantry", Type.INTEGER), new FieldStruct("ICBM", Type.INTEGER), new FieldStruct("Cavalry gun", Type.INTEGER),
				new FieldStruct("Halbedier", Type.INTEGER), new FieldStruct("Submarine", Type.INTEGER), new FieldStruct("Ram, Sampson", Type.INTEGER), new FieldStruct("Animals", Type.INTEGER),
				new FieldStruct("Archer, Javelin", Type.INTEGER), new FieldStruct("<Unused by objects>", Type.INTEGER, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_UNCERTAIN), new FieldStruct("Mech Minotaur", Type.INTEGER), new FieldStruct("<Unused by objects>", Type.INTEGER, 4, Knowledge.KNOWN, GUI.COLOR_FIELD_UNCERTAIN),
				new FieldStruct("Elite Guard", Type.INTEGER), new FieldStruct("Grigor II, Mech Hyperion", Type.INTEGER), new FieldStruct("Mech Zeus", Type.INTEGER), new FieldStruct("Mech Pandora", Type.INTEGER),
				new FieldStruct("Mech Ares", Type.INTEGER), new FieldStruct("AT Aircraft", Type.INTEGER), new FieldStruct("Bomber", Type.INTEGER), new FieldStruct("Modern/Future gun infantry", Type.INTEGER),
				new FieldStruct("AT Tank", Type.INTEGER), new FieldStruct("Battleship", Type.INTEGER), new FieldStruct("AT Helicopter", Type.INTEGER), new FieldStruct("Mech Poseidon, Mech Hades", Type.INTEGER),
				new FieldStruct("Medic, Mech Apollo", Type.INTEGER), new FieldStruct("Citizen, Diplomat", Type.INTEGER), new FieldStruct("Crossbow, Sniper, Mech Tempest", Type.INTEGER), new FieldStruct("Carrier fighter", Type.INTEGER),
				new FieldStruct("Gunship Helicopter", Type.INTEGER), new FieldStruct("Persian cavalry", Type.INTEGER), new FieldStruct("Barbarian, Celtic, Gallic", Type.INTEGER), new FieldStruct("Tower", Type.INTEGER),
				new FieldStruct("Flamethrower, Howitzer", Type.INTEGER), new FieldStruct("Viking", Type.INTEGER), new FieldStruct("Priest, Priest Tower", Type.INTEGER), new FieldStruct("Sword", Type.INTEGER),
				new FieldStruct("Cavalry sword", Type.INTEGER), new FieldStruct("Partisan", Type.INTEGER), new FieldStruct("Atomic Submarine", Type.INTEGER), new FieldStruct("Bazooka", Type.INTEGER),
				new FieldStruct("Spear", Type.INTEGER), new FieldStruct("Cavalry archer", Type.INTEGER), new FieldStruct("Heroes", Type.INTEGER), new FieldStruct("Hand Cannoner", Type.INTEGER),
				new FieldStruct("Mortar", Type.INTEGER), new FieldStruct("Red Baron", Type.INTEGER), new FieldStruct("Fishing boat", Type.INTEGER), new FieldStruct("Anti-Missile Battery", Type.INTEGER),
				new FieldStruct("Capitol ship", Type.INTEGER), new FieldStruct("Space fighter", Type.INTEGER), new FieldStruct("Space corvette", Type.INTEGER), new FieldStruct("ICBM (Power)", Type.INTEGER),
				new FieldStruct("Space turret", Type.INTEGER), FieldStruct.UNUSED_INT4, FieldStruct.UNUSED_INT4
		};
		newEntryValues = new Object[]{
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

}
