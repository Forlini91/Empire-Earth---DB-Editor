package gui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Objects;

import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import datstructure.LocalizedObject;

public class LocalizedListCellRenderer<T extends LocalizedObject> extends JLabel implements ListCellRenderer<T>, Serializable {
	private static final long serialVersionUID = 3815227661160144400L;

	private static final Border DEFAULT_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

	private final JCheckBox localizeToggle;

	public LocalizedListCellRenderer(JCheckBox localizeToggle) {
		this.localizeToggle = localizeToggle;
		setOpaque(true);
		setBorder(DEFAULT_NO_FOCUS_BORDER);
		setName("List.cellRenderer");
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends T> list, T value, int index, boolean isSelected, boolean cellHasFocus) {
		setComponentOrientation(list.getComponentOrientation());

		final Color bg = null;
		final Color fg = null;

		if (isSelected) {
			setBackground(bg == null ? list.getSelectionBackground() : bg);
			setForeground(fg == null ? list.getSelectionForeground() : fg);
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		if (value instanceof Icon) {
			setIcon((Icon) value);
			setText("");
		} else {
			setIcon(null);
			setText((value == null) ? "" : localizeToggle.isSelected() ? value.toLocalizedString() : value.toString());
		}

		setEnabled(list.isEnabled());
		setFont(list.getFont());
		setBorder(DEFAULT_NO_FOCUS_BORDER);

		return this;
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 *
	 * @since 1.5
	 * @return <code>true</code> if the background is completely opaque
	 *         and differs from the JList's background;
	 *         <code>false</code> otherwise
	 */
	@Override
	public boolean isOpaque() {
		final Color back = getBackground();
		Component p = getParent();
		if (p != null) {
			p = p.getParent();
		}
		// p should now be the JList.
		final boolean colorMatch = (back != null) && (p != null) && back.equals(p.getBackground()) && p.isOpaque();
		return !colorMatch && super.isOpaque();
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 */
	@Override
	public void validate() {
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 *
	 * @since 1.5
	 */
	@Override
	public void invalidate() {
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 *
	 * @since 1.5
	 */
	@Override
	public void repaint() {
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 */
	@Override
	public void revalidate() {
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 */
	@Override
	public void repaint(long tm, int x, int y, int width, int height) {
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 */
	@Override
	public void repaint(Rectangle r) {
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 */
	@Override
	protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		// Strings get interned...
		if (propertyName == "text"
				|| ((isScaleChanged(propertyName, oldValue, newValue)
						|| propertyName == "font" || propertyName == "foreground")
						&& oldValue != newValue
						&& getClientProperty(javax.swing.plaf.basic.BasicHTML.propertyKey) != null)) {

			super.firePropertyChange(propertyName, oldValue, newValue);
		}
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 */
	@Override
	public void firePropertyChange(String propertyName, byte oldValue, byte newValue) {
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 */
	@Override
	public void firePropertyChange(String propertyName, char oldValue, char newValue) {
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 */
	@Override
	public void firePropertyChange(String propertyName, short oldValue, short newValue) {
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 */
	@Override
	public void firePropertyChange(String propertyName, int oldValue, int newValue) {
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 */
	@Override
	public void firePropertyChange(String propertyName, long oldValue, long newValue) {
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 */
	@Override
	public void firePropertyChange(String propertyName, float oldValue, float newValue) {
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 */
	@Override
	public void firePropertyChange(String propertyName, double oldValue, double newValue) {
	}

	/**
	 * Overridden for performance reasons.
	 * See the <a href="#override">Implementation Note</a>
	 * for more information.
	 */
	@Override
	public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
	}

	public boolean isScaleChanged(final String name,
			final Object oldValue,
			final Object newValue) {
		if (oldValue == newValue || !"graphicsConfiguration".equals(name)) {
			return false;
		}
		final var newGC = (GraphicsConfiguration) oldValue;
		final var oldGC = (GraphicsConfiguration) newValue;
		final var newTx = newGC != null ? newGC.getDefaultTransform() : null;
		final var oldTx = oldGC != null ? oldGC.getDefaultTransform() : null;
		return !Objects.equals(newTx, oldTx);
	}
}
