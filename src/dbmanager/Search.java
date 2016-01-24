package dbmanager;

import java.util.List;

public abstract class Search<T> {

	int searchIndex = 0;
	
	public abstract void find (String text);
	
	public abstract List<T> getSearchResults();
	
	public T getNext(){
		List<T> searchResults = getSearchResults();
		if (searchResults.size() > 0) {
			if (searchIndex >= searchResults.size() - 1){
				searchIndex = 0;
			} else {
				searchIndex++;
			}
			return searchResults.get(searchIndex);
		}
		return null;
	}

	public T getPrevious(){
		List<T> searchResults = getSearchResults();
		if (searchResults.size() > 0) {
			if (searchIndex <= 0){
				searchIndex = searchResults.size() - 1;
			} else {
				searchIndex--;
			}
			return searchResults.get(searchIndex);
		}
		return null;
	}
	
}


