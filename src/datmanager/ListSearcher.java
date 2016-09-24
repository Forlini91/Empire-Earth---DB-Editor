package datmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import datstructure.Entry;
import datstructure.Link;

/**
 * Class used to search for specific T elements in a given list of T elements.
 * @author MarcoForlini
 * @param <T>	Type of elements to search
 */
public class ListSearcher<T> {
	
	/** Default name matcher for entries */
	public static final BiPredicate<String, Entry> ENTRY_NAME_MATCHER = (text, entry) -> entry.isDefined() && entry.toString().toLowerCase().contains(text);

	/** Default ID matcher for entries */
	public static final BiPredicate<Integer, Entry> ENTRY_ID_MATCHER = (val, entry) -> entry.getID() == val;

	/** Default name matcher for links */
	public static final BiPredicate<String, Link> LINK_NAME_MATCHER = (text, link) -> link.target.isDefined() && link.target.toString().toLowerCase().contains(text);
	
	/** Default ID matcher for links */
	public static final BiPredicate<Integer, Link> LINK_ID_MATCHER = (val, link) -> link.target.getID() == val;

	

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
	
	private List<T> currentList = null;
	
	/** The current index of the search */
	public int searchIndex = 0;
	

	/**
	 * Create a new ListSearcher with the given BiPredicate
	 * @param stringMatchFunction the function to match the string
	 */
	public ListSearcher (BiPredicate<String, T> stringMatchFunction){
		this.stringMatchFunction = stringMatchFunction;
		this.numberMatchFunction = (str, t) -> true;
	}

	/**
	 * Create a new ListSearcher with the given BiPredicate
	 * @param stringMatchFunction the function to match the string
	 * @param numberMatchFunction the function to match the integers
	 */
	public ListSearcher (BiPredicate<String, T> stringMatchFunction, BiPredicate<Integer, T> numberMatchFunction){
		this.stringMatchFunction = stringMatchFunction;
		this.numberMatchFunction = numberMatchFunction;
	}
	


	/**
	 * Initialize the search, by finding all elements which match the passed string
	 * @param list				The list of elements where to search
	 * @param updateFunction	The function to call when a new match is found
	 * @param text				The text to search
	 * @return					The list of elements found
	 */
	public List<T> find (List<T> list, Consumer<T> updateFunction, String text){
		this.updateFunction = updateFunction;
		if (text != null && text.length() > 0) {
			if (list != currentList || !text.equalsIgnoreCase(currentSearch)){
				System.out.print("Search: " + currentSearch + "   >   " + text);
				clearResult();
				currentList = list;
				currentSearch = text;
				if (!text.isEmpty()){
					try {
						int val = Integer.valueOf(text);
						results = list.parallelStream().filter(t -> numberMatchFunction.test(val, t)).collect(Collectors.toList());
					} catch (NumberFormatException e){
						String lText = text.toLowerCase();
						results = list.parallelStream().filter(t -> stringMatchFunction.test(lText, t)).collect(Collectors.toList());
					}
				} else {
					results = new ArrayList<>();
				}
			}
		} else {
			clearResult();
		}
		System.out.println("Results: " + results);
		return results;
	}

	/**
	 * Initialize the search, by finding all elements which match the passed string
	 * @param array				The array of elements where to search
	 * @param updateFunction	The function to call when a new match is found
	 * @param text				The text to search
	 * @return					The list of elements found
	 */
	public List<T> find (T[] array, Consumer<T> updateFunction, String text){
		this.updateFunction = updateFunction;
		if (text != null && text.length() > 0) {
			if (!text.equalsIgnoreCase(currentSearch)){
				clearResult();
				currentSearch = text;
				if (!text.isEmpty()){
					try {
						int val = Integer.valueOf(text);
						results = Arrays.stream(array).parallel().filter(t -> numberMatchFunction.test(val, t)).collect(Collectors.toList());
					} catch (NumberFormatException e){
						String lText = text.toLowerCase();
						results = Arrays.stream(array).parallel().filter(t -> stringMatchFunction.test(lText, t)).collect(Collectors.toList());
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
	 * @return	The next element in the {@code results} list.
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
	 * @return	The previous element in the {@code results} list.
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
	
	/**
	 * Return the last result of this search instance
	 * @return the last result
	 */
	public T getCurrent(){
		if (results != null && searchIndex >= 0 && searchIndex < results.size()){
			return results.get(searchIndex);
		}
		return null;
	}
	
	@SuppressWarnings ("javadoc")
	public void resetCurrent(){
		searchIndex = -1;
	}
	
	
	/**
	 * Gets the current search text
	 * @return	The current search text
	 */
	public String getCurrentSearch(){
		return currentSearch;
	}

	/**
	 * Gets the current results list
	 * @return	The current results list
	 */
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


