/**
 *
 */
package gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.JTextComponent;

import datmanager.Core;
import datstructure.DatStructure;
import datstructure.Entry;
import gui.misc.ListDataCollection;
import gui.misc.MyListModel;


/**
 * @author MarcoForlini
 */
public class JSearchTextField extends JTextField implements DocumentListener, FocusListener {

	private static final long serialVersionUID = 1L;


	private List <SearchTextListener>	listeners	= new ArrayList <> ();

	private DatStructure				datStructure;
	private JListEntry					jList;
	private MyListModel <Entry>			model		= new ListDataCollection <> ();

	private List <Entry>				savedElements;


	public JSearchTextField (JListEntry jList) {
		this (null, jList);
	}

	public JSearchTextField (DatStructure datStructure, JListEntry jList) {
		setDatStructure (datStructure);
		this.jList = jList;
		jList.setModel (model);

		setBackground (Color.WHITE);
		setOpaque (true);
		setUI (new JSearchFieldHintUI ());
		addFocusListener (this);
		getDocument ().addDocumentListener (this);
	}

	public void setDatStructure (DatStructure datStructure) {
		this.datStructure = datStructure;
	}

	public void addSearchListener (SearchTextListener listener) {
		listeners.add (listener);
	}

	public void removeSearchListener (SearchTextListener listener) {
		listeners.remove (listener);
	}

	void fireSearchEvent (String text) {
		for (SearchTextListener stl : listeners) {
			stl.searched (text);
		}
	}


	public void search (boolean refresh) {
		if (datStructure != null) {
			String text = getText ().trim ().toLowerCase ();
			if (text.isEmpty ()) {
				savedElements = null;
				jList.searching = false;
				fireSearchEvent(null);
			} else {
				model.clear ();
				if (savedElements == null || refresh) {
					savedElements = datStructure.datFile.getAllEntries (false);
				}
				jList.searching = true;
				Integer num = Core.toIntNumber (text);
				Stream <Entry> stream = savedElements.parallelStream ();
				if (num != null) {
					int id = num;
					stream = stream.filter (entry -> entry.getID () == id || (entry.isDefined () && entry.toString ().toLowerCase ().contains (text)));
				} else {
					stream = stream.filter (entry -> entry.isDefined () && entry.toString ().toLowerCase ().contains (text));
				}
				model.setList (stream.collect (Collectors.toList ()), true);
				fireSearchEvent(text);
			}
		}
	}

	@Override
	public void insertUpdate (DocumentEvent ev) {
		search (false);
	}

	@Override
	public void removeUpdate (DocumentEvent ev) {
		search (false);
	}

	@Override
	public void changedUpdate (DocumentEvent ev) {
		search (false);
	}


	/**
	 * The text shown in the text field
	 *
	 * @author MarcoForlini
	 */
	public class JSearchFieldHintUI extends BasicTextFieldUI {
		@Override
		protected void paintSafely (Graphics g) {
			super.paintSafely (g);
			JTextComponent comp = getComponent ();
			if (comp.getText ().length () == 0 && !comp.hasFocus ()) {
				g.setColor (Color.GRAY);
				int padding = (comp.getHeight () - comp.getFont ().getSize ()) / 2;
				int inset = 3;
				g.drawString ("Search by Name or ID", inset, comp.getHeight () - padding - inset);
			}
		}
	}


	@Override
	public void focusGained (FocusEvent e) {
		repaint ();
	}

	@Override
	public void focusLost (FocusEvent e) {
		repaint ();
	}



	public interface SearchTextListener {
		void searched (String text);
	}

}
