
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
		try{
			int val = Integer.parseInt(text);
			return list.getList().parallelStream().filter(entry -> {
				return entry.ID == val;
			}).collect(Collectors.toList());
		} catch (NumberFormatException e){}
		return list.getList().parallelStream().filter(entry -> {
			return entry.isDefined() && entry.toString().toLowerCase().contains(text);
		}).collect(Collectors.toList());
	}
	
}
