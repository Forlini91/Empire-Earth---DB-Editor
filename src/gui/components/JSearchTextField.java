/**
 *
 */
package gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.JTextComponent;

import datstructure.LocalizedObject;

/**
 * @author MarcoForlini
 */
public class JSearchTextField<T extends LocalizedObject> extends JTextField implements DocumentListener, FocusListener {

	private static final long serialVersionUID = 1L;

	private final List<SearchTextListener> listeners = new ArrayList<>();

	private final JListLocalized<T> jList;
	private final FilterBuilder<T> filterGenerator;

	public JSearchTextField(JListLocalized<T> jList, FilterBuilder<T> filterGenerator) {
		this.jList = jList;
		this.filterGenerator = filterGenerator;
		this.jList.localizeToggle.addActionListener(e -> search());

		setBackground(Color.WHITE);
		setOpaque(true);
		setUI(new JSearchFieldHintUI());
		addFocusListener(this);
		getDocument().addDocumentListener(this);
	}

	public void addSearchListener(SearchTextListener listener) {
		listeners.add(listener);
	}

	public void removeSearchListener(SearchTextListener listener) {
		listeners.remove(listener);
	}

	void fireSearchEvent(String text) {
		for (final SearchTextListener stl : listeners) {
			stl.searched(text);
		}
	}

	public void search() {
		final String text = getText().trim().toLowerCase();
		if (text.isEmpty()) {
			fireSearchEvent(null);
			jList.filterOverride = null;
		} else {
			fireSearchEvent(text);
			jList.filterOverride = filterGenerator.build(text, jList.localizeToggle.isSelected());
		}
		jList.refresh();
	}

	@Override
	public void insertUpdate(DocumentEvent ev) {
		search();
	}

	@Override
	public void removeUpdate(DocumentEvent ev) {
		search();
	}

	@Override
	public void changedUpdate(DocumentEvent ev) {
		search();
	}

	/**
	 * The text shown in the text field
	 *
	 * @author MarcoForlini
	 */
	public class JSearchFieldHintUI extends BasicTextFieldUI {
		@Override
		protected void paintSafely(Graphics g) {
			super.paintSafely(g);
			final JTextComponent comp = getComponent();
			if (comp.getText().length() == 0 && !comp.hasFocus()) {
				g.setColor(Color.GRAY);
				final int padding = (comp.getHeight() - comp.getFont().getSize()) / 2;
				final int inset = 3;
				g.drawString("Search by Name or ID", inset, comp.getHeight() - padding - inset);
			}
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		repaint();
	}

	@Override
	public void focusLost(FocusEvent e) {
		repaint();
	}

	public interface SearchTextListener {
		void searched(String text);
	}

	public interface FilterBuilder<T> {
		Predicate<T> build(String text, boolean localized);
	}

}
