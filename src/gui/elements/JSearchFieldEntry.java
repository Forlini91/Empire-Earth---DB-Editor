package gui.elements;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import dbmanager.Searcher;

public class JSearchFieldEntry<T> extends JTextField implements KeyListener {
	
	private static final long serialVersionUID = 7654828155231434223L;

	public Searcher<T> search;
	
	public JSearchFieldEntry (Searcher<T> search) {
		this.search = search;
		addKeyListener(this);
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

}