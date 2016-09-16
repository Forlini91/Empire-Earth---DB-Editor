package gui.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JCheckBox;

/**
 * JListExtended which support 2 lists: a provided JCheckBox can alternate between them at will.
 * @author MarcoForlini
 * @param <E> Type of elements
 */
public class JListDouble<E> extends JListExtended<E> {

	private static final long serialVersionUID = -1460528354644591567L;
	
	/** A list without undefined fields. */
	public List<E> listClean;
	
	/** A JCheckBox which allow the user to hide/show the undefined fields, by switching the normal and clean lists. */
	public JCheckBox switchList;
	
	
	/**
	 * Create a new {@link JListDouble}
	 */
	public JListDouble (){
		this(new ArrayList<>(0), new ArrayList<>(0));
	}

	/**
	 * Create a new {@link JListDouble}
	 * @param switchList	The "hide" checkbox state
	 */
	public JListDouble (boolean switchList){
		this(new ArrayList<>(0), new ArrayList<>(0), switchList);
	}

	/**
	 * Create a new {@link JListDouble}
	 * @param list			The list of elements
	 * @param listClean		The list of not-undefined elements
	 */
	public JListDouble (List<E> list, List<E> listClean){
		this(list, listClean, true);
	}

	/**
	 * Create a new {@link JListDouble}
	 * @param list			The list of elements
	 * @param listClean		The list of not-undefined elements
	 * @param switchList	The "hide" checkbox state
	 */
	public JListDouble (List<E> list, List<E> listClean, boolean switchList){
		this(list, listClean, new JCheckBoxExtended("Hide undefined fields", switchList));
	}

	/**
	 * Create a new {@link JListDouble}
	 * @param list			The list of elements
	 * @param listClean		The list of not-undefined elements
	 * @param switchList	The "hide" checkbox
	 */
	public JListDouble (List<E> list, List<E> listClean, JCheckBox switchList){
		this.list = list;
		this.listClean = listClean;
		this.switchList = switchList;
		switchList.addActionListener(e -> refresh());
		refresh();
	}


	
	/**
	 * Set the lists
	 * @param newList		The array of elements
	 * @param newCleanList	The array of not-undefined elements
	 */
	public void setList(E[] newList, E[] newCleanList){
		setList(Arrays.asList(newList), Arrays.asList(newCleanList));
	}
	
	/**
	 * Set the lists
	 * @param newList		The list of elements
	 * @param newCleanList	The list of not-undefined elements
	 */
	public void setList(List<E> newList, List<E> newCleanList){
		list = newList;
		listClean = newCleanList;
		refresh();
	}

	
	
	@Override
	public int getLength(){
		return (switchList.isSelected() ? listClean : list).size();
	}

	@Override
	public E getSelectedElement(){
		return getSelectedValue();
	}
	
	@Override
	public void setSelectedElement(E element){
		setSelectedValue(element, true);
	}

	@Override
	public void setSelectedElement(int index){
		setSelectedValue((switchList.isSelected() ? listClean : list).get(index), true);
	}

	
	@Override
	public E get(int index){
		return (switchList.isSelected() ? listClean : list).get(index);
	}
	
	@Override
	public int indexOf(E element){
		return (switchList.isSelected() ? listClean : list).indexOf(element);
	}
	
	@Override
	public void refresh(){
		E elem = getSelectedElement();
		setListData(new Vector<>(switchList.isSelected() ? listClean : list));
		setSelectedElement(elem);
	}

}
