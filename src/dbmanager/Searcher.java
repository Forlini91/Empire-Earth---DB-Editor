package dbmanager;

import java.util.ArrayList;
import java.util.List;

import gui.components.JListEntry;

public abstract class Searcher<T> {
	
	private JListEntry<T> list;
	
	private List<T> searchResults = null;

	private String lastSearch = null;
	
	private int searchIndex = 0;
	
	
	
	public Searcher (JListEntry<T> list){
		this.list = list;
	}
	
	
	public void findNext(){
		if (searchResults.size() > 0) {
			if (searchIndex >= searchResults.size() - 1){
				searchIndex = 0;
			} else {
				searchIndex++;
			}
			T value = searchResults.get(searchIndex);
			if (value != null){
				list.setSelectedElement(value);
			}
		}
	}
	
	public void findPrevious(){
		if (searchResults.size() > 0) {
			if (searchIndex <= 0){
				searchIndex = searchResults.size() - 1;
			} else {
				searchIndex--;
			}
			T value = searchResults.get(searchIndex);
			if (value != null){
				list.setSelectedElement(value);
			}
		}
	}
	
	public void clearResult(){
		lastSearch = null;
		searchIndex = -1;
	}

	public void find (String text){
		if (!text.equalsIgnoreCase(lastSearch)){
			clearResult();
			lastSearch = text;
			if (!text.isEmpty()){
				searchResults = buildResults(text, list);
			} else {
				searchResults = new ArrayList<>();
			}
		}
	}

	abstract List<T> buildResults(String text, JListEntry<T> list);

}


