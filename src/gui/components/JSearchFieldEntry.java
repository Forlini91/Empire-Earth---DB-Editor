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

import dbmanager.Searcher;

public class JSearchFieldEntry<T> extends JTextField implements KeyListener, FocusListener {
	
	private static final long serialVersionUID = 7654828155231434223L;

	public Searcher<T> search;
	private JSearchFieldHintUI textUI = new JSearchFieldHintUI();
	
	public JSearchFieldEntry (Searcher<T> search) {
		this.search = search;
		addKeyListener(this);
		addFocusListener(this);
		setUI(textUI);
	}
	
	@Override public void keyPressed (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			search.find(getText());
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