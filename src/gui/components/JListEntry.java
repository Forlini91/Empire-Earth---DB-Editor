package gui.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import dbstructure.Identity;

public class JListEntry<E> extends JList<E> {
	
	private static final long serialVersionUID = 6271105907903664525L;

	private List<E> list;
	private List<E> listClean;
	public JCheckBox hideUnusedBox;
	


	public JListEntry(){
		this(new ArrayList<>(0));
	}
	
	public JListEntry (E[] array){
		this(Arrays.asList(array));
	}


	
	public JListEntry (List<E> list){
		super();
		this.list = list;
		listClean = buildListClean();
		hideUnusedBox = new JCheckBox("Hide undefined fields", true);
		hideUnusedBox.setOpaque(false);
		hideUnusedBox.addChangeListener(e -> refresh());
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setVisibleRowCount(10);
		refresh();
	}
	
	public JListEntry (List<E> list, boolean hideUnused){
		super();
		this.list = list;
		listClean = buildListClean();
		hideUnusedBox = new JCheckBox("Hide undefined fields", hideUnused);
		hideUnusedBox.setOpaque(false);
		hideUnusedBox.addChangeListener(e -> refresh());
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setVisibleRowCount(10);
		refresh();
	}
	
	public JListEntry (List<E> list, List<E> listClean){
		super();
		this.list = list;
		this.listClean = listClean;
		hideUnusedBox = new JCheckBox("Hide undefined fields", true);
		hideUnusedBox.setOpaque(false);
		hideUnusedBox.addChangeListener(e -> refresh());
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setVisibleRowCount(10);
		refresh();
	}
	
	public JListEntry (List<E> list, List<E> listClean, boolean hideUnused){
		super();
		this.list = list;
		this.listClean = listClean;
		hideUnusedBox = new JCheckBox("Hide undefined fields", hideUnused);
		hideUnusedBox.setOpaque(false);
		hideUnusedBox.addChangeListener(e -> refresh());
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setVisibleRowCount(10);
		refresh();
	}
	
	public JListEntry (List<E> list, List<E> listClean, JCheckBox hideUnusedBox){
		super();
		this.list = list;
		this.listClean = listClean;
		this.hideUnusedBox = hideUnusedBox;
		hideUnusedBox.addChangeListener(e -> refresh());
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setVisibleRowCount(10);
		refresh();
	}
	
	
	

	public List<E> getList(){
		return list;
	}
	

	public void setList(E[] newList){
		list = Arrays.asList(newList);
		listClean = buildListClean();
		refresh();
	}
	

	public void setList(List<E> newList){
		list = newList;
		listClean = buildListClean();
		refresh();
	}
	
	public void setList(E[] newList, E[] newListClean){
		list = Arrays.asList(newList);
		listClean = Arrays.asList(newListClean);
		refresh();
	}
	

	public void setList(List<E> newList, List<E> newListClean){
		list = newList;
		listClean = newListClean;
		refresh();
	}
	

	public int getLength(){
		if (hideUnusedBox.isSelected()) {
			return listClean.size();
		} else {
			return list.size();
		}
	}
	

	public E getSelectedElement(){
		return getSelectedValue();
	}
	

	public void setSelectedElement(E element){
		setSelectedValue(element, true);
	}
	
	public void setSelectedElement(int index){
		if (hideUnusedBox.isSelected()) {
			setSelectedValue(listClean.get(index), true);
		} else {
			setSelectedValue(list.get(index), true);
		}
	}
	

	public E get(int index){
		if (hideUnusedBox.isSelected()) {
			return listClean.get(index);
		} else {
			return list.get(index);
		}
	}
	

	public int indexOf(E element){
		if (hideUnusedBox.isSelected()) {
			return listClean.indexOf(element);
		} else {
			return list.indexOf(element);
		}
	}

	public void refresh(){
		if (hideUnusedBox.isSelected()) {
			setListData(new Vector<>(listClean));
		} else {
			setListData(new Vector<>(list));
		}
	}
	

	
	public List<E> buildListClean(){
		int n = list.size();
		List<E> newListClean = new ArrayList<>();
		Identity ident;
		for (int i = 0; i < n; i++){
			ident = (Identity) list.get(i);
			if (ident.getID() >= 0 && ident.getSequenceNumber() >= 0){
				newListClean.add(list.get(i));
			}
		}
		return newListClean;
	}
	
}
