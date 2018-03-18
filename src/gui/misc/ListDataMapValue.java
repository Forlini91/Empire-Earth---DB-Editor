/**
 *
 */
package gui.misc;

import java.util.Map;


/**
 * @author MarcoForlini
 * @param <V>
 */
public class ListDataMapValue <V extends Comparable <? super V>> extends MyListModel <V> {

	private static final long serialVersionUID = 1L;

	public ListDataMapValue () {}

	public ListDataMapValue (Map <?, ? extends V> mapData) {
		super (mapData.values ());
		sort (null);
	}

}
