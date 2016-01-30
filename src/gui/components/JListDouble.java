package gui.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JCheckBox;

/**
 * JListExtended which support 2 lists: a provided JCheckBox can alternate between them at will.
 * @author MarcoForlini
 *
 * @param <E>
 */
public class JListDouble<E> extends JListExtended<E> implements AbstractJListExtended <E> {
	
	private static final long serialVersionUID = -1460528354644591567L;

	/** A list without undefined fields. */
	public List<E> listClean;
	/** A JCheckBox which allow the user to hide/show the undefined fields, by switching the normal and clean lists. */
	public JCheckBox switchList;



	public JListDouble (String checkBoxText){
		this(new ArrayList<>(0), new ArrayList<>(0), checkBoxText);
	}
	
	public JListDouble (String checkBoxText, boolean switchList){
		this(new ArrayList<>(0), new ArrayList<>(0), checkBoxText, switchList);
	}
	
	public JListDouble (List<E> list, List<E> listClean, String checkBoxText){
		this(list, listClean, checkBoxText, true);
	}
	
	public JListDouble (List<E> list, List<E> listClean, String checkBoxText, boolean switchList){
		this(list, listClean, new JCheckBoxExtended(checkBoxText, switchList));
	}
	
	public JListDouble (List<E> list, List<E> listClean, JCheckBox switchList){
		this.list = list;
		this.listClean = listClean;
		this.switchList = switchList;
		switchList.addChangeListener(E -> refresh());
		refresh();
	}
	
	

	public void setList(E[] newList, E[] newCleanList){
		setList(Arrays.asList(newList), Arrays.asList(newCleanList));
	}

	
	public void setList(List<E> newList, List<E> newCleanList){
		list = newList;
		listClean = newCleanList;
		refresh();
	}
	


	@Override
	public int getLength(){
		if (switchList.isSelected()) {
			return listClean.size();
		} else {
			return list.size();
		}
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
		if (switchList.isSelected()) {
			setSelectedValue(listClean.get(index), true);
		} else {
			setSelectedValue(list.get(index), true);
		}
	}
	

	@Override
	public E get(int index){
		if (switchList.isSelected()) {
			return listClean.get(index);
		} else {
			return list.get(index);
		}
	}

	@Override
	public int indexOf(E element){
		if (switchList.isSelected()) {
			return listClean.indexOf(element);
		} else {
			return list.indexOf(element);
		}
	}

	@Override
	public void refresh(){
		if (switchList.isSelected()) {
			setListData(new Vector<>(listClean));
		} else {
			setListData(new Vector<>(list));
		}
	}
	
}
