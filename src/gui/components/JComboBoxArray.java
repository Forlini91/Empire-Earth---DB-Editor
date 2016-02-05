package gui.components;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;

import datstructure.FieldStruct;
import gui.FrameEditor;


public class JComboBoxArray extends JComboBox <Integer> implements AbstractEntryField, ItemListener {

	private static final long serialVersionUID = -5787229930995728192L;
	
	private JTextComponent editor = ((JTextComponent) getEditor().getEditorComponent());
	private FieldStruct fieldStruct;
	private int index;
	private Object defaultVal = null;
	private boolean altered = false;
	
	private static Integer[] collect(int[] arr){
		Integer[] arr2 = new Integer[arr.length];
		for (int i = 0, j = arr.length; i < j; i++){
			arr2[i] = Integer.valueOf(arr[i]);
		}
		return arr2;
	}
	
	public JComboBoxArray(FrameEditor frameEditor, FieldStruct fieldStruct, int index){
		super(collect(fieldStruct.arrValues));
		this.fieldStruct = fieldStruct;
		this.index = index;
		setEditable(true);
		addItemListener(this);
	}
	
	@Override
	public synchronized void addMouseListener (MouseListener l) {
		super.addMouseListener(l);
		if (editor != null){
			editor.addMouseListener(l);
		}
	}

	@Override
	public void resetColor () {
		setForeground(null);
	}

	@Override
	public FieldStruct getEntryStruct () {
		return fieldStruct;
	}

	@Override
	public int getIndex(){
		return index;
	}

	@Override
	public Object getVal(){
		Object obj = getSelectedItem();
		//		System.out.println("Getting: " + fieldStruct + " = " + obj + '(' + fieldStruct.defaultValue + '/' + defaultVal + ')');
		if (obj != null){
			return obj;
		} else {
			return fieldStruct.arrValues[0];
		}
	}

	@Override
	public void setVal(Object value){
		defaultVal = value;
		int index = Arrays.binarySearch(fieldStruct.arrValues, (int) value);
		if (index >= 0){
			setSelectedIndex(index);;
			altered = false;
			return;
		}
		setSelectedIndex(0);
		altered = false;
	}
	
	@Override
	public void refreshField() {}

	@Override
	public boolean isAltered () {
		return altered;
	}

	@Override
	public Object getDefaultVal () {
		return defaultVal;
	}
	
	@Override
	public void itemStateChanged (ItemEvent e) {
		altered = true;
	}

}
