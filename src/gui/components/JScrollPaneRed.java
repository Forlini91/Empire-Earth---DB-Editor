package gui.components;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import gui.misc.EEScrollBarUI;

/**
 * A scrollpane with red color and customized aspect
 * @author MarcoForlini
 */
public class JScrollPaneRed extends JScrollPane {
	
	private static final long serialVersionUID = -7689733963310921975L;
	
	/**
	 * The viewport view
	 */
	public Component viewportView;
	
	/**
	 * Create a new {@link JScrollPaneRed}
	 */
	public JScrollPaneRed(){
		this(new JPanel());
	}
	
	/**
	 * Create a new {@link JScrollPaneRed}
	 * @param viewportView	The viewport view
	 */
	public JScrollPaneRed(Component viewportView){
		getVerticalScrollBar().setUnitIncrement(8);
		getVerticalScrollBar().setUI(new EEScrollBarUI());
		getHorizontalScrollBar().setUnitIncrement(4);
		getHorizontalScrollBar().setUI(new EEScrollBarUI());
		setOpaque(false);
		setViewportView(viewportView);
		getViewport().setOpaque(false);
	}

	/**
	 * Create a new {@link JScrollPaneRed}
	 * @param viewportView	The viewport view
	 * @param headerView	The header view
	 * @param column		If true, the headerView is a column, else it's a row
	 */
	public JScrollPaneRed(Component viewportView, Component headerView, boolean column){
		this(viewportView);
		if (column){
			setColumnHeaderView(headerView);
			getColumnHeader().setOpaque(false);
		} else {
			setRowHeaderView(headerView);
			getRowHeader().setOpaque(false);
		}
	}

	
	/**
	 * Create a new {@link JScrollPaneRed}
	 * @param viewportView		The viewport view
	 * @param columnHeaderView	The column header view
	 * @param rowHeaderView		The row header view
	 */
	public JScrollPaneRed(Component viewportView, Component columnHeaderView, Component rowHeaderView){
		this(viewportView);
		setColumnHeaderView(columnHeaderView);
		getColumnHeader().setOpaque(false);
	}
	
	/**
	 * Create a new {@link JScrollPaneRed}
	 * @param viewportView			The viewport view
	 * @param columnHeaderViewText	The column header view text
	 */
	public JScrollPaneRed(Component viewportView, String columnHeaderViewText){
		this(viewportView);
		JLabel columnHeaderView = new JLabel(columnHeaderViewText, SwingConstants.CENTER);
		columnHeaderView.setOpaque(false);
		setColumnHeaderView(columnHeaderView);
		getColumnHeader().setOpaque(false);
	}

	/**
	 * Create a new {@link JScrollPaneRed}
	 * @param viewportView			The viewport view
	 * @param columnHeaderViewText	The column header view text
	 * @param rowHeaderView		The row header view
	 */
	public JScrollPaneRed(Component viewportView, String columnHeaderViewText, Component rowHeaderView){
		this(viewportView);
		JLabel headerView = new JLabel(columnHeaderViewText, SwingConstants.CENTER);
		headerView.setOpaque(false);
		setColumnHeaderView(headerView);
		getColumnHeader().setOpaque(false);
		setRowHeaderView(rowHeaderView);
		getRowHeader().setOpaque(false);
	}
	
	@Override
	public void setViewportView (Component viewportView) {
		super.setViewportView(viewportView);
		this.viewportView = viewportView;
	}

}
