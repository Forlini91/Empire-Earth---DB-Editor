package dbmanager;

import java.util.List;
import java.util.stream.Collectors;

import dbstructure.EntryGroup;
import gui.elements.JListEntry;

public class EntryGroupSearcher extends Searcher<EntryGroup> {
	
	public EntryGroupSearcher(JListEntry<EntryGroup> list){
		super(list);
	}

	@Override
	public List<EntryGroup> buildResults(String text, JListEntry<EntryGroup> list){
		return list.getList().parallelStream().filter(entryGroup -> {
			return entryGroup.name.toLowerCase().contains(text);
		}).collect(Collectors.toList());
	}
	
}