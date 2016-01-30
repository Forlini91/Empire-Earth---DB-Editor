package gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.BiPredicate;

import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.JTextComponent;

import datmanager.ListSearcher;
import datstructure.Entry;

public class JSearchFieldEntry extends JTextField implements KeyListener, FocusListener {

	private static final long serialVersionUID = -516645984968177458L;
	private static final BiPredicate<String, Entry> NAME_MATCHER = (text, entry) -> entry.isDefined() && entry.toString().toLowerCase().contains(text);
	private static final BiPredicate<Integer, Entry> ID_MATCHER = (val, entry) -> entry.ID == val;

	public AbstractJListExtended<Entry> entryList;
	public ListSearcher <Entry> search;

	public JSearchFieldEntry (AbstractJListExtended<Entry> entryList) {
		this.entryList = entryList;
		search = new ListSearcher<Entry>(NAME_MATCHER, ID_MATCHER);
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
	
	@Override
	public void keyTyped (KeyEvent e) {}

	@Override
	public void keyReleased (KeyEvent e) {}
	
	@Override
	public void focusGained(FocusEvent e) {
		repaint();
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		repaint();
	}
	
	
	
	public class JSearchFieldHintUI extends BasicTextFieldUI {
		private static final String HINT_TEXT = "Search by Name or ID";
		
		@Override
		protected void paintSafely(Graphics g) {
			super.paintSafely(g);
			JTextComponent comp = getComponent();
			if (comp.getText().length() == 0 && !comp.hasFocus()) {
				g.setColor(Color.GRAY);
				int padding = (comp.getHeight() - comp.getFont().getSize()) / 2;
				int inset = 3;
				g.drawString(HINT_TEXT, inset, comp.getHeight() - padding - inset);
			}
		}
	}
	
}