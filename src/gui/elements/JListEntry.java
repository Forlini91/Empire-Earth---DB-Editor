package gui.elements;

import java.util.Arrays;
import java.util.Collection;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class JListEntry<E> extends JList<E> {
	
	private Vector<E> elements;
	private static final long serialVersionUID = 1L;
	
	public JListEntry(){
		this(new Vector<E>(), false);
	}
	
	public JListEntry (E[] data){
		this(new Vector<E>(Arrays.asList(data)), false);
	}
	
	public JListEntry (Collection<E> data){
		this(data, false);
	}
	
	public JListEntry (E[] data, boolean sorted){
		this(new Vector<E>(Arrays.asList(data)), sorted);
	}
	
	public JListEntry (Collection<E> data, boolean sorted){
		super();
		setVector(data, sorted);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setVisibleRowCount(10);
	}
	

	public Vector<E> getVector(){
		return elements;
	}
	

	public void setVector(E[] newElements){
		setVector(newElements, false);
	}
	

	public void setVector(Collection<E> newElements){
		setVector(newElements, false);
	}
	

	public void setVector(E[] newElements, boolean sorted){
		elements = new Vector<E>(Arrays.asList(newElements));
		if (sorted) {
			sort();
		}
		refresh();
	}
	

	public void setVector(Collection<E> newElements, boolean sorted){
		elements = new Vector<E>(newElements);
		if (sorted) {
			sort();
		}
		refresh();
	}
	

	public int getLength(){
		return elements.size();
	}
	

	public E getSelectedElement(){
		return getSelectedValue();
	}
	

	public void setSelectedElement(E element){
		setSelectedValue(element, false);
	}
	

	public void setSelectedElement(E element, boolean showElement){
		setSelectedValue(element, showElement);
	}
	

	public void setSelectedIndex(int index, boolean showElement){
		setSelectedValue(elements.get(index), showElement);
	}
	

	public E get(int index){
		return elements.get(index);
	}
	

	public int indexOf(E element){
		return elements.indexOf(element);
	}
	

	public int add(E element){
		return add(element, -1, false);
	}
	

	public int add(E element, int index){
		return add(element, index, false);
	}
	

	public int add(E element, boolean sorted){
		return add(element, -1, sorted);
	}
	

	public int add(E element, int index, boolean sorted){
		if (index < 0) {
			elements.add(element);
		} else {
			elements.add(index, element);
		}
		if (sorted) {
			sort();
		}
		refresh();
		setSelectedValue(element, true);
		if (index < 0) {
			return getSelectedIndex();
		} else {
			return index;
		}
	}
	

	public int drop(E element){
		int index = indexOf(element);
		if (index == -1) {
			return -1;
		}
		drop(index);
		return index;
	}
	

	public E drop(int index) throws ArrayIndexOutOfBoundsException {
		E element = elements.remove(index);
		refresh();
		if (index < elements.size()){
			setSelectedIndex(index);
		}
		else{
			setSelectedIndex(elements.size()-1);
		}
		return element;
	}
	

	public void addAll(JListEntry<E> otherList){
		addAll(otherList.getVector(), false);
	}
	

	public void addAll(JListEntry<E> otherList, boolean sorted){
		addAll(otherList.getVector(), sorted);
	}
	

	public void addAll(Vector<E> otherElements){
		addAll(otherElements, false);
	}
	

	public void addAll(Vector<E> otherElements, boolean sorted){
		elements.addAll(otherElements);
		if (sorted) {
			sort();
		}
		refresh();
	}
	

	public void dropAll(int...indexes){
		for (int index : indexes){
			if (index >= getLength()){
				return;
			}
			elements.remove(index);
		}
		refresh();
	}
	

	public void dropAll(Vector<E> otherElements){
		elements.retainAll(otherElements);
		refresh();
	}
	
	public Vector<E> sort(){
		return sort(false);
	}

	public Vector<E> sort(boolean refresh){
		if (refresh) {
			return refresh(sort(elements));
		}
		return sort(elements);
	}
	
	public Vector<E> sort(Vector<E> elements){
		elements.sort(null);
		return elements;
	}

	public Vector<E> refresh(){
		setListData(elements);
		return elements;
	}
	
	public Vector<E> refresh(Vector<E> elements){
		setListData(elements);
		return elements;
	}
	
}
