package gui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import datstructure.FieldStruct;
import gui.PopupMenuHandler;


/**
 * Declares all methods used by fields
 * @author MarcoForlini
 */
public interface EntryFieldInterface {

	/**
	 * Gets the structure of the field
	 * @return	the structure of the field
	 */
	FieldStruct getEntryStruct();

	/**
	 * Gets the
	 * @return	the
	 */
	int getIndex();

	/**
	 * Sets the value of the field
	 * @param value		the new value
	 * @throws IllegalArgumentException		If the value can't be assigned
	 */
	void setVal(Object value) throws IllegalArgumentException;
	
	/**
	 * Gets the
	 * @return	the
	 */
	Object getVal();
	
	/**
	 * Sets the visible state
	 * @param visible	the new visible state
	 */
	public void setVisible(boolean visible);

	/**
	 * Sets the new minimum size
	 * @param dimension 	the new miminum size
	 */
	public void setMinimumSize(Dimension dimension);

	/**
	 * Sets the new preferred size
	 * @param dimension 	the new preferred size
	 */
	public void setPreferredSize(Dimension dimension);

	/**
	 * Sets the new maximum size
	 * @param dimension 	the new minimum size
	 */
	public void setMaximumSize(Dimension dimension);

	/**
	 * Sets the opaque state
	 * @param opaque	the new opaque state
	 */
	public void setOpaque(boolean opaque);
	
	/**
	 * Sets whether or not this component is enabled.
	 * A component that is enabled may respond to user input,
	 * while a component that is not enabled cannot respond to
	 * user input.  Some components may alter their visual
	 * representation when they are disabled in order to
	 * provide feedback to the user that they cannot take input.
	 * <p>Note: Disabling a component does not disable its children.
	 *
	 * <p>Note: Disabling a lightweight component does not prevent it from
	 * receiving MouseEvents.
	 *
	 * @param enabled true if this component should be enabled, false otherwise
	 * @see java.awt.Component#isEnabled
	 * @see java.awt.Component#isLightweight
	 */
	public void setEnabled(boolean enabled);
	
	/**
	 * Reset the field color to the default one
	 */
	public void resetColor();
	
	/**
	 * Gets the default value
	 * @return	the default value
	 */
	public Object getDefaultVal();

	/**
	 * Check if the field content has been altered
	 * @return	true if the field has been altered, false otherwise
	 */
	boolean isAltered ();

	/**
	 * Refresh the field content
	 */
	void refreshField();
	
	/**
	 * Sets the foreground color of this component.  It is up to the
	 * look and feel to honor this property, some may choose to ignore
	 * it.
	 *
	 * @param fg  the desired foreground <code>Color</code>
	 * @see java.awt.Component#getForeground
	 */
	void setForeground (Color fg);
	
	/**
	 * Sets the popup menu handler
	 * @param popupMenuHandler the popup menu handler
	 */
	default void setPopupMenu(PopupMenuHandler popupMenuHandler){
		((Component) this).addMouseListener(popupMenuHandler);
	}
	
}
