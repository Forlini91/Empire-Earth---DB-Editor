package gui.elements;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;

import dbstructure.EntryStruct;


@SuppressWarnings ("serial")
public class JSliderEx extends JSlider implements ValueField, MouseMotionListener, MouseListener {
	
	private EntryStruct entryStruct;
	private int index;

	private final JPopupMenu pop = new JPopupMenu();
	private JMenuItem item = new JMenuItem();

	public JSliderEx (EntryStruct entryStruct, int index, int min, int max, int value){
		super(min, max, value);
		this.entryStruct = entryStruct;
		this.index = index;
		addMouseMotionListener(this);
		addMouseListener(this);
		setMajorTickSpacing(25);
		setMinorTickSpacing(1);
		setSnapToTicks(true);
		pop.add(item);
		pop.setDoubleBuffered(true);
	}


	
	@Override
	public EntryStruct getEntryStruct () {
		return entryStruct;
	}
	
	@Override
	public int getIndex(){
		return index;
	}
	
	@Override
	public boolean isFieldCompiled(){
		return super.getValue() >= 0;
	}
	
	@Override
	public Object getVal(){
		return getValue();
	}
	
	@Override
	public void setVal (Object value) {
		setValue((int) value);
	}
	
	
	public void showToolTip (MouseEvent me){
		int val = getValue();
		item.setText(val>=0?String.valueOf(val):"Default");
		pop.show(me.getComponent(), me.getX() - 5, -30);
		item.setArmed(false);
	}

	@Override
	public void mouseDragged (MouseEvent me){
		showToolTip(me);
	}

	@Override
	public void mousePressed (MouseEvent me){
		showToolTip(me);
	}

	@Override
	public void mouseReleased (MouseEvent me){
		pop.setVisible(false);
	}

	@Override
	public void mouseMoved (MouseEvent me){}
	@Override
	public void mouseClicked (MouseEvent me){}
	@Override
	public void mouseEntered (MouseEvent me){}
	@Override
	public void mouseExited (MouseEvent me){}

}
