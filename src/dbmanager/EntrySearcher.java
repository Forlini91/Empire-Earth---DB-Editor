
package dbmanager;

import java.util.List;
import java.util.stream.Collectors;

import dbstructure.Entry;
import gui.elements.JListEntry;

public class EntrySearcher extends Searcher<Entry> {

	public EntrySearcher(JListEntry<Entry> list){
		super(list);
	}

	@Override
	public List<Entry> buildResults(String text, JListEntry<Entry> list){
		int val = Integer.MIN_VALUE;
		try{
			val = Integer.parseInt(text);
		} catch (NumberFormatException e){}
		final int ID = val;
		return list.getVector().parallelStream().filter(entry -> {
			return entry.ID == ID || entry.toString().toLowerCase().contains(text);
		}).collect(Collectors.toList());
	}
	
}
