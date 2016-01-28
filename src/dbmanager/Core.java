package dbmanager;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import dbstructure.DatStructure;
import dbstructure.Entry;
import dbstructure.EntryGroup;
import dbstructure.EntryStruct;
import dbstructure.Identity;
import gui.GUIEditor;
import gui.GUIMain;

public class Core {

	public static final Color uiColorBackground = new Color(249, 241, 224);
	public static final Color uiColorElement = new Color (150, 15, 15);


	public static final Map<DatStructure, List<EntryGroup>> dbData = new HashMap<>();
	public static final Map<DatStructure, GUIEditor> dbEditors = new HashMap<>();
	public static final Map<Integer, Entry> dbIDs = new ConcurrentHashMap<>();

	static {
		System.out.println("Check entries definitions:");
		int count;
		for (DatStructure datStructure : DatStructure.values()){
			count = 0;
			for (EntryStruct entryStruct : datStructure.entries){
				count += entryStruct.size;
			}
			System.out.println('\t' + datStructure.fileName + ':' + ' ' + count);
		}
	}


	public static void main (String[] args) {
		EventQueue.invokeLater(() -> {
			new GUIMain();
		});
	}

	public static void addEntry(Entry entry){
		if (entry.isDefined()){
			Entry previous = dbIDs.put(entry.sequenceNumber, entry);
			if (previous != null){
				System.out.println("Duplicate ID: " + entry + "  <=>  " + previous);
			}
		}
	}

	public static Identity[] findEntryByID(DatStructure datStructure, int ID){
		for (EntryGroup entryGroup : dbData.get(datStructure)){
			for (Entry entry : entryGroup){
				if (entry.getID() == ID){
					return new Identity[]{entryGroup, entry};
				}
			}
		}
		return null;
	}
	
	
	private Core(){}

}
