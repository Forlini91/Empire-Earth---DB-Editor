package gui.components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import datstructure.FieldStruct;
import datstructure.Type;


public class JTextFieldEntry extends JTextField implements AbstractEntryField, FocusListener {

	private static final long serialVersionUID = -7134081240220832439L;
	private static final Color BROWN = new Color(127, 51, 0);
	
	private FieldStruct fieldStruct;
	private final int index;
	private final Color defaultColor;
	//	private JTextFieldEntryUI textUI = new JTextFieldEntryUI();
	
	//	private String lastText = null;
	
	//	private final DatStructure linkToStruct;
	//	public EntryGroup pointToGroup = null;
	//	public Entry pointToEntry = null;
	
	public JTextFieldEntry(FieldStruct fieldStruct, int index){
		this.fieldStruct = fieldStruct;
		this.index = index;
		if (fieldStruct.getColor() == Color.RED){
			defaultColor = BROWN;
		} else {
			defaultColor = fieldStruct.getColor();
		}
		//		linkToStruct = fieldStruct.linkToStruct;
		
		//		setUI(textUI);
		setColumns(10);
		setEditable(fieldStruct.isEditable());
		setForeground(defaultColor);
		setCaretPosition(0);
		
		addFocusListener(this);
		//		addMouseListener(this);
		//		if (linkToStruct != null){
		//			getDocument().addDocumentListener(this);
		//		}
	}
	
	@Override
	public void resetColor () {
		setForeground(defaultColor);
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
		switch(fieldStruct.getType()){
			case STRING:
				return getText();
			case FLOAT:
				return Float.valueOf(getText());
			default:
				return Integer.valueOf(getText());
		}
	}
	
	@Override
	public void setVal (Object value) {
		if (fieldStruct.getType() == Type.STRING){
			setText(((String) value).trim());
		} else {
			setText(String.valueOf(value));
		}
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		repaint();
	}

	@Override
	public void focusLost(FocusEvent e) {
		//		checkResult(getText());
		System.out.println(fieldStruct + " = " + getVal());
	}


	
	//	private void checkResult(String text){
	//		if (fieldStruct != null){
	//			if (fieldStruct.type == Type.STRING){
	//				if (text.length() > fieldStruct.size) {
	//					JTextFieldEntry.super.setText(text.substring(0, 100));
	//					Toolkit.getDefaultToolkit().beep();
	//				}
	//			} else {
	//				try {
	//					if (fieldStruct.type == Type.FLOAT) {
	//						Float.valueOf(text);
	//					} else {
	//						int value = Integer.valueOf(text);
	//						if (fieldStruct.type == Type.BOOLEAN){
	//							if (value < 0) {
	//								JTextFieldEntry.super.setText("0");
	//								Toolkit.getDefaultToolkit().beep();
	//							} else if (value > 1) {
	//								JTextFieldEntry.super.setText("1");
	//								Toolkit.getDefaultToolkit().beep();
	//							}
	//						}
	//					}
	//				} catch (NumberFormatException exc) {
	//					setText(null);
	//					Toolkit.getDefaultToolkit().beep();
	//				}
	//			}
	//		}
	//	}
	
	
	//	public void scanEntry(){
	//		if (!getText().isEmpty()){
	//			DatContent dbManager = Core.DATA.get(linkToStruct);
	//			if (dbManager != null){
	//				try{
	//					Object[] results = null;
	//					Entry entry;
	//					for (EntryGroup entryGroup : Core.DATA.get(linkToStruct).entryGroups){
	//						entry = entryGroup.map.get(Integer.valueOf(getText()));
	//						if (entry != null){
	//							results = new Object[]{entryGroup, entry};
	//							break;
	//						}
	//					}
	//					if (results != null){
	//						EntryGroup findGroup = (EntryGroup) results[0];
	//						Entry findEntry = (Entry) results[1];
	//						if (findGroup != pointToGroup || findEntry != pointToEntry){
	//							pointToGroup = findGroup;
	//							pointToEntry = findEntry;
	//							//System.out.println("Field " + fieldStruct.toString() + "   >   Matches " + findGroup + " > " + findEntry);
	//						}
	//						return;
	//					}
	//				} catch (NumberFormatException e){}
	//			}
	//		}
	//		pointToGroup = null;
	//		pointToEntry = null;
	//	}

	
	//	@Override
	//	public void mouseClicked (MouseEvent e) {
	//		if (e.isControlDown() && pointToEntry != null){
	//			DatContent datContent = Core.DATA.get(linkToStruct);
	//			if (datContent != null){
	//				FrameEditor frameEditor = Core.openFile(this, datContent);
	//				frameEditor.goToEntry(pointToGroup, pointToEntry);
	//			}
	//		}
	//	}
	//	@Override public void mousePressed (MouseEvent e) {}
	//	@Override public void mouseReleased (MouseEvent e) {}
	//	@Override public void mouseEntered (MouseEvent e) {}
	//	@Override public void mouseExited (MouseEvent e) {}
	
	
	
	
	
	//	public class JTextFieldEntryUI extends BasicTextFieldUI {
	//		@Override
	//		protected void paintSafely(Graphics g) {
	//			super.paintSafely(g);
	//			if (linkToStruct != null) {
	//				String printText = printText();
	//				if (printText != null){
	//					g.setColor(Color.GRAY);
	//					int padding = (getHeight() - getFont().getSize()) / 2;
	//					int inset = 3;
	//					g.drawString(printText, inset + 10*getText().length(), getHeight() - padding - inset);
	//				}
	//			}
	//		}
	//
	//		public String printText(){
	//			if (linkToStruct == null){
	//				if (getText().isEmpty()){
	//					return "<empty>";
	//				} else {
	//					return null;
	//				}
	//			} else if (getText().isEmpty()) {
	//				return '(' + linkToStruct.name + " ID)";
	//			} else if (pointToEntry == null){
	//				return "<unknown ID>";
	//			} else {
	//				return pointToEntry.getName();
	//			}
	//		}
	//
	//	}
	
	
	
	
	//	@Override
	//	public void insertUpdate (DocumentEvent e) {
	//		if (!getText().equals(lastText)){
	//			scanEntry();
	//			lastText = getText();
	//		}
	//	}
	//
	//	@Override
	//	public void removeUpdate (DocumentEvent e) {
	//		if (!getText().equals(lastText)){
	//			scanEntry();
	//			lastText = getText();
	//		}
	//	}
	//
	//	@Override
	//	public void changedUpdate (DocumentEvent e) {
	//		if (!getText().equals(lastText)){
	//			scanEntry();
	//			lastText = getText();
	//		}
	//	}
	
}
