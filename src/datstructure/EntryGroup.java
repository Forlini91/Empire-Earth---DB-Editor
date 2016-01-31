package datstructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class define a group of entries.
 *
 * Why "groups"?
 * In the files, the first 4 bytes are a counter, which state the number of entries to read.
 * In near all files this counter state the number of ALL entries in the file.
 *
 * In dbtechtree.dat there are many counters: one for each epoch:
 * At the beginning of this file there's a counter (929), followed by the tech "Epoch Paleo",
 * 		which is followed by all 929 techs which belong to epoch Paleo (note: many of them are undefined, but still counted).
 * 		As you may guess by these numbers, the "Epoch Paleo" itself is not counted.
 * After these 929 techs... there's another counter, followerd by the tech "Epoch Stone", which is followed by all techs which belong to epoch Stone.
 * There are 15 epochs, so there are 15 counters in the files, followed by their relative 15 Epochs, which are followed by their relative techs.
 * These 15 pieces (counter + epoch + epoch's techs) are the groups.
 *
 * I called them "Groups" and not "Epochs" because I still don't know if there's another file like dbtechtree.dat.
 *
 *
 * @author MarcoForlini
 *
 */
public class EntryGroup implements Iterable <Entry> {
	
	/** The structure of the entries in this group. A redundant value for quick access purposes. */
	public DatStructure datStructure;
	
	/** The list of entries in this group. */
	public List<Entry> entries;

	/** A map of all IDs of the entries. A redundant value for quick access purposes. */
	public Map<Integer, Entry> map;

	/** The name of this group. This is the name of the first entry in the group, but only if there are more groups. */
	public String name;

	public EntryGroup (DatStructure datStructure, List<Entry> entries) {
		this.datStructure = datStructure;
		this.entries = entries;
		if (entries.size() > 0){
			name = entries.get(0).toString();
		}
		if (datStructure.getIndexID() >= 0){
			map = entries.parallelStream().filter(entry -> entry.isDefined()).collect(Collectors.toMap(t -> t.ID, t -> t));
		} else {
			map = new HashMap<>();
		}
	}
	
	public EntryGroup (DatStructure datStructure, String name, int ID){
		this.datStructure = datStructure;
		this.name = name;
		entries = new ArrayList<>();
	}

	@Override
	public Iterator <Entry> iterator () {
		return entries.iterator();
	}

	@Override
	public String toString(){
		return name;
	}

}
