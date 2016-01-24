package gui.elements;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GridBagConstraintsExtended extends GridBagConstraints {

	/**
	 *
	 */
	private static final long serialVersionUID = 3530583877836650118L;

	public GridBagConstraintsExtended (int top, int left, int bottom, int right, int gridx, int gridy) {
		insets = new Insets(top, left, bottom, right);
		fill = GridBagConstraints.BOTH;
		this.gridx = gridx;
		this.gridy = gridy;
	}
	
	public GridBagConstraintsExtended (int fill, int top, int left, int bottom, int right, int gridx, int gridy) {
		insets = new Insets(top, left, bottom, right);
		this.fill = fill;
		this.gridx = gridx;
		this.gridy = gridy;
	}

	public GridBagConstraintsExtended (int top, int left, int bottom, int right, int gridx, int gridy, int gridwidth, int gridheight) {
		insets = new Insets(top, left, bottom, right);
		fill = GridBagConstraints.BOTH;
		this.gridx = gridx;
		this.gridy = gridy;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}

}
