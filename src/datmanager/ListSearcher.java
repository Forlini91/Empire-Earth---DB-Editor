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
	private List<T> results = null;
	
	/** The current searched string */
	private String currentSearch = null;

	/** The current index of the search */
	public int searchIndex = 0;

	
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
	 * Initialize the search, by finding all elements which match the passed string
	 * @param text	The text to search
	 */
	public List<T> find (List<T> list, Consumer<T> updateFunction, String text){
		this.updateFunction = updateFunction;
		if (text != null && text.length() > 0) {
			if (!text.equalsIgnoreCase(currentSearch)){
				clearResult();
				currentSearch = text;
				if (!text.isEmpty()){
					try {
						int val = Integer.valueOf(text);
						results = list.parallelStream().filter(t -> numberMatchFunction.test(val, t)).collect(Collectors.toList());
					} catch (NumberFormatException e){
						results = list.parallelStream().filter(t -> stringMatchFunction.test(text, t)).collect(Collectors.toList());
					}
				} else {
					results = new ArrayList<>();
				}
			}
		} else {
			clearResult();
		}
		return results;
	}

	/**
	 * Search for the next element and select it in the list.
	 * Must be called after the method <code>find</code>.
	 */
	public T findNext(){
		if (results != null && results.size() > 0) {
			if (searchIndex < 0 || searchIndex >= results.size() - 1){
				searchIndex = 0;
			} else {
				searchIndex++;
			}
			T value = results.get(searchIndex);
			if (updateFunction != null && value != null){
				updateFunction.accept(value);
			}
			return value;
		}
		return null;
	}

	/**
	 * Search for the previous element and select it in the list
	 * Must be called after the method <code>find</code>.
	 */
	public T findPrevious(){
		if (results != null && results.size() > 0) {
			if (searchIndex <= 0 || searchIndex > results.size() - 1){
				searchIndex = results.size() - 1;
			} else {
				searchIndex--;
			}
			T value = results.get(searchIndex);
			if (updateFunction != null && value != null){
				updateFunction.accept(value);
			}
			return value;
		}
		return null;
	}



	public String getCurrentSearch(){
		return currentSearch;
	}
	
	public List<T> getResults(){
		return results;
	}
	
	/**
	 * Destroy the search results
	 */
	public void clearResult(){
		results = null;
		currentSearch = null;
		searchIndex = -1;
	}
	
}


