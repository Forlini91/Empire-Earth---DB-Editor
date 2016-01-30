package gui.components;


import java.util.List;


public interface AbstractJListExtended <E> {

	/**
	 * Get the list of elements
	 * @return	The list of elements
	 */
	List <E> getList ();

	/**
	 * Set the list of elements from an array
	 * @param newList	The array of elements
	 * @param newCleanList	The clean array of elements
	 */
	void setList (E[] newList);

	/**
	 * Set the list of elements
	 * @param newList	The list of elements
	 * @param newCleanList	The clean list of elements
	 */
	void setList (List <E> newList);

	/**
	 * Get the size of the list
	 * @return	The size of the list
	 */
	int getLength ();

	/**
	 * Get the selected element
	 * @return	The selected element
	 */
	E getSelectedElement ();

	/**
	 * Select an element
	 * @param element	The element to select
	 */
	void setSelectedElement (E element);

	/**
	 * Select an elemenet
	 * @param index		The index of the element to select
	 */
	void setSelectedElement (int index);

	/**
	 * Get the element at the given index
	 * @param index	The index of the element to get
	 * @return	The element at the given index
	 */
	E get (int index);

	/**
	 * Get the index of the given element or -1 if it's not in the list.
	 * @param element	The element to search
	 * @return		The index of the given element or -1 if it's not in the list.
	 */
	int indexOf (E element);

	/**
	 * Refresh the list
	 */
	void refresh ();

}
