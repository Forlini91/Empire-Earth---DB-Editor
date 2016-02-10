package gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import datstructure.Entry;

public class JListEntry extends JListDouble<Entry> {

	private static final long serialVersionUID = -1460528354644591567L;
	public boolean allowMove = false;

	public JListEntry(String checkBoxText){
		this(new ArrayList<>(0), checkBoxText);
	}

	public JListEntry (Entry[] array, String checkBoxText){
		this(Arrays.asList(array), checkBoxText, true);
	}
	
	public JListEntry (List<Entry> list, String checkBoxText){
		this(list, buildListClean(list), checkBoxText, true);
	}

	public JListEntry (List<Entry> list, String checkBoxText, boolean hideUnused){
		this(list, buildListClean(list), checkBoxText, hideUnused);
	}

	public JListEntry (List<Entry> list, List<Entry> listClean, String checkBoxText){
		this(list, listClean, checkBoxText, true);
	}

	public JListEntry (List<Entry> list, List<Entry> listClean, String checkBoxText, boolean hideUnused){
		this(list, listClean, new JCheckBoxExtended(checkBoxText, hideUnused));
	}

	public JListEntry (List<Entry> list, List<Entry> listClean, JCheckBox switchList){
		super(list, listClean, switchList);
		registerKeyboardAction((e) -> moveUp(e), KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_DOWN_MASK), JComponent.WHEN_FOCUSED);
		registerKeyboardAction((e) -> moveUp(e), KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK), JComponent.WHEN_FOCUSED);
		registerKeyboardAction((e) -> moveDown(e), KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_DOWN_MASK), JComponent.WHEN_FOCUSED);
		registerKeyboardAction((e) -> moveDown(e), KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK), JComponent.WHEN_FOCUSED);
	}

	public void moveUp(ActionEvent e){
		//		System.out.println("CTRL + Up");
		if (allowMove && hasFocus()){
			int amount = (e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK ? 10 : 1;
			int index = getSelectedIndex();
			if (switchList.isSelected()){
				Entry sel = listClean.get(index);
				index = list.indexOf(sel);
			}
			if (index >= 0){
				if (index - amount < 0){
					amount = index;
				}
				Entry sel = list.get(index);
				while (amount > 0){
					Entry selUp = list.get(index-1);
					list.set(index, selUp);
					list.set(index-1, sel);
					index--;
					amount--;
				}
				listClean = buildListClean(list);
				refresh();
			}
		}
	}


	public void moveDown(ActionEvent e){
		//		System.out.println("CTRL + Down");
		if (allowMove && hasFocus()){
			int amount = (e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK ? 10 : 1;
			int index = getSelectedIndex();
			if (switchList.isSelected()){
				Entry sel = listClean.get(index);
				index = list.indexOf(sel);
			}
			if (index >= 0){
				if (index + amount >= list.size()){
					amount = list.size() - index - 1;
				}
				Entry sel = list.get(index);
				while (amount > 0){
					Entry selUp = list.get(index+1);
					list.set(index, selUp);
					list.set(index+1, sel);
					index++;
					amount--;
				}
				listClean = buildListClean(list);
				refresh();
			}
		}
	}


	@Override
	public void setList(Entry[] newList){
		list = Arrays.asList(newList);
		listClean = buildListClean(list);
		refresh();
	}

	@Override
	public void setList(List<Entry> newList){
		list = newList;
		listClean = buildListClean(list);
		refresh();
	}
	

	/**
	 * Build and return a "clean" list: a list without undefined fields.
	 * @return	The clean list
	 */
	public static List<Entry> buildListClean(List<Entry> list){
		int n = list.size();
		List<Entry> newListClean = new ArrayList<>();
		Entry entry;
		for (int i = 0; i < n; i++){
			entry = list.get(i);
			if (entry.isDefined()){
				newListClean.add(list.get(i));
			}
		}
		return newListClean;
	}

	@Override
	public void refresh(){
		super.refresh();
		allowMove = list.size() > 0 && list.get(0).datStructure.getNewEntryValues() != null;
	}

}
