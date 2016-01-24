package gui.elements;

import java.util.Collection;
import java.util.Vector;

public interface JExtended<E> {
	
	public Vector<E> getVector();
	
	public void setVector(E[] newElements);
	
	public void setVector(Collection<E> newElements);
	
	public void setVector(E[] newElements, boolean sorted);
	
	public void setVector(Collection<E> newElements, boolean sorted);
	
	public int getLength();
	
	public E getSelectedElement();
	
	public int getSelectedIndex();
	
	public void setSelectedElement(E element);
	
	public void setSelectedElement(E element, boolean showElement);
	
	public void setSelectedIndex(int index);
	
	public void setSelectedIndex(int index, boolean showElement);
	
	public E get(int index);
	
	public int indexOf(E element);
	
	public int add(E element);
	
	public int add(E element, int index);
	
	public int add(E element, boolean sorted);
	
	public int add(E element, int index, boolean sorted);
	
	public int drop(E element);

	public E drop(int index);
	
	public void addAll(JExtended<E> otherList);
	
	public void addAll(JExtended<E> otherList, boolean sorted);
	
	public void addAll(Vector<E> otherElements);
	
	public void addAll(Vector<E> otherElements, boolean sorted);
	
	public void dropAll(int...indexes);
	
	public void dropAll(Vector<E> otherElements);
	
	public int transferTo(E element, JExtended<E> otherList);
	
	public int transferTo(E element, JExtended<E> otherList, boolean sorted);
	
	public int transferTo(int element, JExtended<E> otherList);
	
	public int transferTo(int element, JExtended<E> otherList, boolean sorted);
	
	public void transferAll(JExtended<E> otherList, boolean sorted);
	
	public E moveUp(E element);
	
	public E moveUp(int element);
	
	public E moveDown(E element);
	
	public E moveDown(int element);
	
	public void switchElements(int indexA, int indexB);
	
	public void switchElements(E elemA, E elemB);
	
	public void switchElements(E elemA, int indexA, E elemB, int indexB);
	
	public Vector<E> sort();
	
	public Vector<E> sort(boolean refresh);
	
	public Vector<E> sort(Vector<E> elements);
	
	public Vector<E> refresh();
	
	public Vector<E> refresh(Vector<E> elements);
	
}
