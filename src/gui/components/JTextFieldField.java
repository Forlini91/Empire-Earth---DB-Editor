package gui.components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.function.Consumer;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import datmanager.Core;
import datstructure.FieldStruct;
import datstructure.FieldType;


/**
 * A JTextField which can hold the value of a field
 *
 * @author MarcoForlini
 */
public class JTextFieldField extends JTextField implements EntryFieldInterface, FocusListener, DocumentListener {

	private static final long				serialVersionUID	= -7134081240220832439L;
	private static final Color				BROWN				= new Color (127, 51, 0);
	private static final Consumer <String>	nullUpdater			= text -> {/* Do nothing */};

	private final FieldStruct				fieldStruct;
	private final int						index;
	private final Color						defaultColor;
	private Object							defaultVal			= null;
	private boolean							altered				= false;
	private Consumer <String>				updater				= nullUpdater;


	/**
	 * Create a new {@link JTextFieldField}
	 *
	 * @param fieldStruct The field structure
	 * @param index Index of the field
	 */
	public JTextFieldField (FieldStruct fieldStruct, int index) {
		this.fieldStruct = fieldStruct;
		this.index = index;
		if (fieldStruct.getColor () == Color.RED) {
			defaultColor = BROWN;
		} else {
			defaultColor = fieldStruct.getColor ();
		}
		setColumns (10);
		setEditable (fieldStruct.isEditable ());
		setForeground (defaultColor);
		setCaretPosition (0);

		addFocusListener (this);
		getDocument ().addDocumentListener (this);
	}

	/**
	 * Register an updater function which run when a key is typed
	 *
	 * @param updater The updater function
	 */
	public void registerUpdater (Consumer <String> updater) {
		if (updater != null) {
			this.updater = updater;
		} else {
			this.updater = nullUpdater;
		}
	}

	@Override
	public void resetColor () {
		setForeground (defaultColor);
	}

	@Override
	public FieldStruct getEntryStruct () {
		return fieldStruct;
	}

	@Override
	public int getIndex () {
		return index;
	}

	@Override
	public Object getVal () {
		switch (fieldStruct.getType ()) {
			case STRING:
				return getText ();
			case FLOAT:
				return Float.valueOf (getText ());
			default:
				if (getText ().isEmpty ()) {
					return 0;
				}
				return Integer.valueOf (getText ());
		}
	}

	@Override
	public void setVal (Object value) {
		defaultVal = value;
		if (fieldStruct.getType () == FieldType.STRING) {
			setText (((String) value).trim ());
		} else {
			if (value instanceof Float) {
				setText (Core.numberFormat.format ((float) value));
			} else {
				setText (Integer.toString ((int) value));
			}
		}
		setCaretPosition (0);
		altered = false;
	}

	@Override
	public boolean isAltered () {
		return altered;
	}

	@Override
	public Object getDefaultVal () {
		return defaultVal;
	}

	@Override
	public void refreshField () {/* Do nothing */}

	@Override
	public void focusGained (FocusEvent e) {
		repaint ();
	}

	@Override
	public void focusLost (FocusEvent e) {
		repaint ();
	}


	@Override
	public void insertUpdate (DocumentEvent e) {
		altered = true;
		String str = getText ();
		updater.accept (str);
	}

	@Override
	public void removeUpdate (DocumentEvent e) {
		altered = true;
		String str = getText ();
		updater.accept (str);
	}

	@Override
	public void changedUpdate (DocumentEvent e) {
		altered = true;
		String str = getText ();
		updater.accept (str);
	}

}
