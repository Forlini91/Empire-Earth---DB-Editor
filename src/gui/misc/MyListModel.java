/**
 *
 */
package gui.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.swing.AbstractListModel;

import datmanager.Util;


/**
 * @author MarcoForlini
 * @param <E>
 */
public abstract class MyListModel <E> extends AbstractListModel <E> implements Iterable <E> {

	private static final long serialVersionUID = 1L;


	protected final ArrayList <E> listData;


	public MyListModel () {
		listData = new ArrayList <> ();
	}

	public MyListModel (Collection <? extends E> listData) {
		this.listData = new ArrayList <> (listData);
	}

	public void setList (Collection <? extends E> listData) {
		clear ();
		addAll (listData);
	}

	public void setList (Collection <? extends E> listData, boolean sort) {
		setList (listData);
		if (sort) {
			sort (null);
		}
	}

	public List <E> getList () {
		return new ArrayList <> (listData);
	}

	public void sort (Comparator <? super E> c) {
		listData.sort (c);
		fireContentsChanged (this, 0, listData.size () - 1);
	}

	public void updateList () {
		fireContentsChanged (this, 0, listData.size () - 1);
	}



	@Override
	public int getSize () {
		return listData.size ();
	}

	@Override
	public E getElementAt (int i) {
		return listData.get (i);
	}

	public boolean contains (E element) {
		return listData.contains (element);
	}

	public boolean containsAny (Collection <? extends E> c) {
		return Util.containsAny (listData, c);
	}

	public boolean containsAll (Collection <? extends E> c) {
		return listData.containsAll (c);
	}

	public int indexOf (E element) {
		return listData.indexOf (element);
	}

	public int lastIndexOf (E element) {
		return listData.lastIndexOf (element);
	}



	public void ensureCapacity (int minCapacity) {
		listData.ensureCapacity (minCapacity);
	}

	public E set (int index, E element) {
		E previous = listData.set (index, element);
		fireContentsChanged (this, index, index);
		return previous;
	}

	public void swap (int index1, int index2) {
		int n = listData.size ();
		if (index1 != index2 && index1 >= 0 && index1 < n && index2 >= 0 && index2 < n) {
			E elem1 = listData.get (index1);
			E elem2 = listData.get (index2);
			listData.set (index1, elem2);
			listData.set (index2, elem1);
			if (Math.abs (index1 - index2) == 1) {
				fireContentsChanged (this, index1, index2);
			} else {
				fireContentsChanged (this, index1, index1);
				fireContentsChanged (this, index2, index2);
			}
		}

	}


	public boolean add (E element) {
		return add (element, false);
	}

	public boolean add (E element, boolean sort) {
		int index = listData.size ();
		if (listData.add (element)) {
			fireIntervalAdded (this, index, index);
			if (sort) {
				sort (null);
			}
			return true;
		}
		return false;
	}

	public void add (int index, E element) {
		listData.add (index, element);
		fireIntervalAdded (this, index, index);
	}

	public boolean addAll (Collection <? extends E> collection) {
		return addAll (collection, false);
	}

	public boolean addAll (Collection <? extends E> collection, boolean sort) {
		int index = listData.size ();
		if (listData.addAll (collection)) {
			fireIntervalAdded (this, index, listData.size () - index - 1);
			if (sort) {
				sort (null);
			}
			return true;
		}
		return false;
	}

	public void addIf (Collection <? extends E> collection, Predicate <E> filter) {
		addIf (collection, filter, false);
	}

	public void addIf (Collection <? extends E> collection, Predicate <E> filter, boolean sort) {
		int index = listData.size ();
		int numChanged = 0;
		for (E element : collection) {
			if (filter.test (element)) {
				if (listData.add (element)) {
					numChanged++;
				}
			}
		}
		if (numChanged > 0) {
			fireIntervalAdded (this, index, index + numChanged);
			if (sort) {
				sort (null);
			}
		}
	}

	public boolean remove (E element) {
		int index = indexOf (element);
		if (index >= 0) {
			listData.remove (index);
			fireIntervalRemoved (this, index, index);
			return true;
		}
		return false;
	}

	public E remove (int index) {
		E removed = listData.remove (index);
		fireIntervalRemoved (this, index, index);
		return removed;
	}

	public boolean removeAll (Collection <E> c) {
		switch (c.size ()) {
			case 0:
				return false;
			case 1:
				return remove (c.iterator ().next ());
			default:
				int size = listData.size ();
				listData.removeAll (c);
				if (size != listData.size ()) {
					fireIntervalRemoved (this, 0, size);
					return true;
				}
				return false;
		}
	}

	public boolean removeIf (Predicate <? super E> filter) {
		boolean removed = false;
		for (int i = listData.size () - 1; i >= 0; i--) {
			if (filter.test (listData.get (i))) {
				listData.remove (i);
				fireIntervalRemoved (this, i, i);
				removed = true;
			}
		}
		return removed;
	}

	public void clear () {
		int max = listData.size () - 1;
		if (max >= 0) {
			listData.clear ();
			fireIntervalRemoved (this, 0, max);
		}
	}



	@Override
	public Iterator <E> iterator () {
		return new MyListModelIterator ();
	}


	public Stream <E> stream () {
		return listData.stream ();
	}


	public Stream <E> parallelStream () {
		return listData.parallelStream ();
	}



	class MyListModelIterator implements Iterator <E> {

		private int index = -1;

		@Override
		public boolean hasNext () {
			return index + 1 < listData.size ();
		}

		@Override
		public E next () {
			return listData.get (++index);
		}

		@Override
		public void remove () {
			MyListModel.this.remove (index--);
		}

	}

}
