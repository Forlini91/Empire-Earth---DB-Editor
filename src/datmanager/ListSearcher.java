package datmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Class used to search for specific T elements in a given list of T elements.
 * @author MarcoForlini
 *
 * @param <T>
 */
public class ListSearcher<T> {

	/** The BiPredicate used for matching strings */
	private final BiPredicate<String, T> stringMatchFunction;

	/** The BiPredicate used for matching numbers */
	private final BiPredicate<Integer, T> numberMatchFunction;

	/** The function to update the GUI when calling {@code findNext} or {@code findPrevious} */
	private Consumer<T> updateFunction = null;
	
	/** The list with all search results */
	private List<T> searchResults = null;
	
	/** The last searched string */
	private String lastSearch = null;

	/** The current index of the search */
	private int searchIndex = 0;

	
	/**
	 * Create a new ListSearcher with the given AbstractJListExtended
	 * @param list The AbstractJListExtended
	 */
	public ListSearcher (BiPredicate<String, T> stringMatchFunction){
		this.stringMatchFunction = stringMatchFunction;
		this.numberMatchFunction = (str, t) -> true;
	}
	
	/**
	 * Create a new ListSearcher with the given AbstractJListExtended
	 * @param list The AbstractJListExtended
	 */
	public ListSearcher (BiPredicate<String, T> stringMatchFunction, BiPredicate<Integer, T> numberMatchFunction){
		this.stringMatchFunction = stringMatchFunction;
		this.numberMatchFunction = numberMatchFunction;
	}


	/**
	 * Search for the next element and select it in the list
	 */
	public void findNext(){
		if (searchResults != null && searchResults.size() > 0) {
			if (searchIndex >= searchResults.size() - 1){
				searchIndex = 0;
			} else {
				searchIndex++;
			}
			T value = searchResults.get(searchIndex);
			if (value != null){
				updateFunction.accept(value);
			}
		}
	}

	/**
	 * Search for the previous element and select it in the list
	 */
	public void findPrevious(){
		if (searchResults != null && searchResults.size() > 0) {
			if (searchIndex <= 0){
				searchIndex = searchResults.size() - 1;
			} else {
				searchIndex--;
			}
			T value = searchResults.get(searchIndex);
			if (value != null){
				updateFunction.accept(value);
			}
		}
	}

	/**
	 * Destroy the search results
	 */
	public void clearResult(){
		searchResults = null;
		lastSearch = null;
		searchIndex = -1;
	}
	
	/**
	 * Initialize the search, by finding all elements which match the passed string
	 * @param text	The text to search
	 */
	public void find (List<T> list, Consumer<T> updateFunction, String text){
		this.updateFunction = updateFunction;
		if (!text.equalsIgnoreCase(lastSearch)){
			clearResult();
			lastSearch = text;
			if (!text.isEmpty()){
				try {
					int val = Integer.valueOf(text);
					searchResults = list.parallelStream().filter(t -> numberMatchFunction.test(val, t)).collect(Collectors.toList());
				} catch (NumberFormatException e){
					searchResults = list.parallelStream().filter(t -> stringMatchFunction.test(text, t)).collect(Collectors.toList());
				}
			} else {
				searchResults = new ArrayList<>();
			}
		}
	}
	
}


