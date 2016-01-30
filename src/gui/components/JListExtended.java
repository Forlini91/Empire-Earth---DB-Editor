package gui.components;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**
 * JList which keep the reference to the passed list.
 * You can change the list from outside this class, then use the command refresh() to update the list.
 * @author MarcoForlini
 *
 * @param <E>
 */
public class JListExtended<E> extends JList<E> implements AbstractJListExtended <E> {

	private static final long serialVersionUID = -1460528354644591567L;

	/** The list of elements in the list */
	public List<E> list;
	

	JListExtended(){}
	
	public JListExtended (List<E> list){
		this.list = list;
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setVisibleRowCount(10);
		refresh();
	}
	

	
	@Override
	public List<E> getList(){
		return list;
	}

	@Override
	public void setList(E[] newList){
		setList(Arrays.asList(newList));
	}
	

	@Override
	public void setList(List<E> newList){
		list = newList;
		refresh();
	}

	@Override
	public int getLength(){
		return list.size();
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
		setSelectedValue(list.get(index), true);
	}

	
	@Override
	public E get(int index){
		return list.get(index);
	}
	
	@Override
	public int indexOf(E element){
		return list.indexOf(element);
	}
	
	@Override
	public void refresh(){
		setListData(new Vector<>(list));
	}

}
