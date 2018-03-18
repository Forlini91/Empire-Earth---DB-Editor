/**
 *
 */
package gui.misc;

import java.util.Collection;


/**
 * @author MarcoForlini
 * @param <E>
 */
public class ListDataCollection <E> extends MyListModel <E> {

	private static final long serialVersionUID = 1L;

	public ListDataCollection () {
		super ();
	}

	public ListDataCollection (Collection <? extends E> listData) {
		super (listData);
	}

}
