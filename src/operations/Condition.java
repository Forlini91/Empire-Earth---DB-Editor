
package operations;

import datstructure.Entry;

/**
 * A condition used to match the entries
 * @author MarcoForlini
 */
public interface Condition {

	/** Condition which always return true */
	Condition ALWAYS_TRUE = (Entry entry) -> true;
	
	/** Condition which always return false */
	Condition ALWAYS_FALSE = (Entry entry) -> false;

	/**
	 * Check if the given entry match the condition
	 * @param entry		The entry to check
	 * @return			true if the entry match the condition, false otherwise
	 */
	boolean match(Entry entry);



	/**
	 * Build a condition which checks if the given entry satisfy both condition
	 * @param c1	The first condition
	 * @param c2	The second condition
	 * @return		a condition which return true if both condition are satisfied, false otherwise
	 */
	static Condition and(Condition c1, Condition c2){
		return (Entry entry) -> c1.match(entry) && c2.match(entry);
	}

	/**
	 * Build a condition which checks if the given entry satisfy all conditions
	 * @param conditions	The conditions
	 * @return		a condition which return true if all conditions are satisfied, false otherwise
	 */
	static Condition and(Condition...conditions){
		return (Entry entry) -> {
			for (Condition condition : conditions){
				if (!condition.match(entry)){
					return false;
				}
			}
			return true;
		};
	}

	/**
	 * Build a condition which checks if the given entry satisfy either condition
	 * @param c1	The first condition
	 * @param c2	The second condition
	 * @return		a condition which return true if either condition are satisfied, false otherwise
	 */
	static Condition or(Condition c1, Condition c2){
		return (Entry entry) -> c1.match(entry) || c2.match(entry);
	}
	
	
	/**
	 * Build a condition which checks if the given entry satisfy at least one condition
	 * @param conditions	The conditions
	 * @return		a condition which return true if at least one condition is satisfied, false otherwise
	 */
	static Condition or(Condition...conditions){
		return (Entry entry) -> {
			for (Condition condition : conditions){
				if (condition.match(entry)){
					return true;
				}
			}
			return false;
		};
	}
	
	

	/**
	 * Build a condition which checks if the given entry satisfy exactly one condition
	 * @param c1	The first condition
	 * @param c2	The second condition
	 * @return		a condition which return true if one condition is true and one condition is false, false otherwise
	 */
	static Condition xor(Condition c1, Condition c2){
		return (Entry entry) -> {
			boolean b1 = c1.match(entry), b2 = c2.match(entry);
			return (b1 && !b2) || (!b1 && b2);
		};
	}


	/**
	 * Build a condition which checks if the given entry satisfy exactly one condition
	 * @param conditions	The conditions
	 * @return		a condition which return true if exactly one condition is satisfied, false if none or more than 1 condition are satisfied
	 */
	static Condition xor(Condition...conditions){
		return (Entry entry) -> {
			boolean sat = false;
			for (Condition condition : conditions){
				if (condition.match(entry)){
					if (sat){
						return false;
					}
					sat = true;
				}
			}
			return sat;
		};
	}

}
