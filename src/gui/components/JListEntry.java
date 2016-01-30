package gui.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JCheckBox;

import datstructure.Entry;

public class JListEntry extends JListDouble<Entry> {

	private static final long serialVersionUID = -1460528354644591567L;
	

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

	public JListEntry (List<Entry> list, List<Entry> listClean, JCheckBox hideUnusedBox){
		super(list, listClean, hideUnusedBox);
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

}
