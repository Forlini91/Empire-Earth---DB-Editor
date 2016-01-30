package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Supplier;

import javax.swing.JList;
import javax.swing.JPopupMenu;

/**
 * This class show a popup for the selected list.
 * @author MarcoForlini
 *
 */
public class PopupMenuListHandler extends MouseAdapter {
	public JPopupMenu popupMenu;
	public JList<?> list;
	public Supplier<Boolean> supportNumEntriesAlteration;
	
	public PopupMenuListHandler(JPopupMenu popupMenu, JList<?> list, Supplier<Boolean> supportNumEntriesAlteration){
		this.popupMenu = popupMenu;
		this.list = list;
		this.supportNumEntriesAlteration = supportNumEntriesAlteration;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JList<?> jlist = list;
		int selected = jlist.locationToIndex(e.getPoint());
		if (selected >= 0){
			jlist.setSelectedIndex(selected);
		}
		showMenu(e);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		showMenu(e);
	}
	
	public void showMenu(MouseEvent e){
		if (supportNumEntriesAlteration.get()){
			if (e.isPopupTrigger()) {
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
}