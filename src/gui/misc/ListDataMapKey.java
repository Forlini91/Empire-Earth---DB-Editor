/**
 *
 */
package gui.misc;

import java.util.Map;


/**
 * @author MarcoForlini
 * @param <K>
 */
public class ListDataMapKey <K extends Comparable <? super K>> extends MyListModel <K> {

	private static final long serialVersionUID = 1L;

	public ListDataMapKey () {}

	public ListDataMapKey (Map <? extends K, ?> mapData) {
		super (mapData.keySet ());
		sort (null);
	}

}
