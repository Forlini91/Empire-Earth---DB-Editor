package datstructure;


/**
 * This class represents a link between an entry and another entry
 * @author MarcoForlini
 */
public class Link {
	
	/** The pointed entry */
	public Entry entry;
	
	/**
	 * Create a new Link
	 * @param entry	The pointed entry
	 */
	public Link (Entry entry){
		this.entry = entry;
	}

	@Override
	public String toString(){
		return entry.toString();
	}
}
