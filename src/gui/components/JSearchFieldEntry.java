package gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.JTextComponent;

import datmanager.ListSearcher;
import datstructure.Entry;


/**
 * A field where the user can search for an entry
 * @author MarcoForlini
 */
public class JSearchFieldEntry extends JTextField implements KeyListener, FocusListener {

	private static final long serialVersionUID = -516645984968177458L;

	/** The associated JList with the entries */
	public AbstractJListExtended<Entry> entryList;
	
	/** The searcher which search the data in the JList */
	public ListSearcher <Entry> search = new ListSearcher<>(ListSearcher.ENTRY_NAME_MATCHER, ListSearcher.ENTRY_ID_MATCHER);

	/**
	 * Create a new {@link JSearchFieldEntry}
	 * @param entryList		The list of entries
	 */
	public JSearchFieldEntry (AbstractJListExtended<Entry> entryList) {
		this.entryList = entryList;
		setBackground(Color.WHITE);
		setOpaque(true);
		addKeyListener(this);
		addFocusListener(this);
		setUI(new JSearchFieldHintUI());
	}

	@Override public void keyPressed (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			search.find(entryList.getList(), entryList::setSelectedElement, getText());
			if (e.isShiftDown()){
				search.findPrevious();
			} else {
				search.findNext();
			}
		}
	}
	
	@Override public void keyTyped (KeyEvent e) {/*Do nothing*/}
	@Override public void keyReleased (KeyEvent e) {/*Do nothing*/}
	
	@Override
	public void focusGained(FocusEvent e) {
		repaint();
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		repaint();
	}
	
	
	
	/**
	 * The text shown in the text field
	 * @author MarcoForlini
	 */
	public class JSearchFieldHintUI extends BasicTextFieldUI {
		@Override
		protected void paintSafely(Graphics g) {
			super.paintSafely(g);
			JTextComponent comp = getComponent();
			if (comp.getText().length() == 0 && !comp.hasFocus()) {
				g.setColor(Color.GRAY);
				int padding = (comp.getHeight() - comp.getFont().getSize()) / 2;
				int inset = 3;
				g.drawString("Search by Name or ID", inset, comp.getHeight() - padding - inset);
			}
		}
	}
	
}