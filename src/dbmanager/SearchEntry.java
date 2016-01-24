
package dbmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dbstructure.Entry;

public class SearchEntry extends Search<Entry> {
	private List<Entry> entries;
	private List<Entry> searchResults = null;
	private String lastSearch = null;

	public SearchEntry(List<Entry> entries){
		this.entries = new ArrayList<>(entries);
	}
	
	@Override
	public void find(String text){
		if (!text.equalsIgnoreCase(lastSearch)){
			lastSearch = text;
			if (!text.isEmpty()){
				final String textL = text.toLowerCase();
				searchResults = entries.parallelStream().filter(entry -> {
					return entry.toString().toLowerCase().contains(textL);
				}).collect(Collectors.toList());
			} else {
				searchResults = new ArrayList<>();
			}
			searchIndex = -1;
		}
	}

	@Override
	public List<Entry> getSearchResults () {
		return searchResults;
	}

}
