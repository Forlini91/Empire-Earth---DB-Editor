package gui.components;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import gui.ui.EEScrollBarUI;

public class JScrollPaneRed extends JScrollPane {

	private static final long serialVersionUID = -7689733963310921975L;

	public Component viewportView;

	public JScrollPaneRed(){
		this(new JPanel());
	}

	public JScrollPaneRed(Component viewportView){
		getVerticalScrollBar().setUnitIncrement(8);
		getVerticalScrollBar().setUI(new EEScrollBarUI());
		getHorizontalScrollBar().setUnitIncrement(4);
		getHorizontalScrollBar().setUI(new EEScrollBarUI());
		setOpaque(false);
		setViewportView(viewportView);
		getViewport().setOpaque(false);
	}
	
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
	
	public JScrollPaneRed(Component viewportView, Component columnHeaderView, Component rowHeaderView){
		this(viewportView);
		setColumnHeaderView(columnHeaderView);
		getColumnHeader().setOpaque(false);
	}

	public JScrollPaneRed(Component viewportView, String columnHeaderViewText){
		this(viewportView);
		JLabel columnHeaderView = new JLabel(columnHeaderViewText, SwingConstants.CENTER);
		columnHeaderView.setOpaque(false);
		setColumnHeaderView(columnHeaderView);
		getColumnHeader().setOpaque(false);
	}
	
	public JScrollPaneRed(Component viewportView, String headerViewText, Component rowHeaderView){
		this(viewportView);
		JLabel headerView = new JLabel(headerViewText, SwingConstants.CENTER);
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
