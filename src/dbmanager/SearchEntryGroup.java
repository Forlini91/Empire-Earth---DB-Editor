package dbmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dbstructure.EntryGroup;

public class SearchEntryGroup extends Search<EntryGroup> {
	private List<EntryGroup> entryGroups;
	private List<EntryGroup> searchResults = null;
	private String lastSearch;

	public SearchEntryGroup(List<EntryGroup> entryGroups){
		this.entryGroups = new ArrayList<>(entryGroups);
	}
	
	@Override
	public void find(String text){
		if (!text.equalsIgnoreCase(lastSearch)){
			lastSearch = text;
			if (!text.isEmpty()){
				final String textL = text.toLowerCase();
				searchResults = entryGroups.parallelStream().filter(entryGroup -> {
					return entryGroup.name.toLowerCase().contains(textL);
				}).collect(Collectors.toList());
			} else {
				searchResults = new ArrayList<>();
			}
			searchIndex = -1;
		}
	}

	@Override
	public List <EntryGroup> getSearchResults () {
		return searchResults;
	}

}
