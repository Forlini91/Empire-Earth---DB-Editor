package gui.misc;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * A customized {@link GridBagConstraints} with useful constructors
 * @author MarcoForlini
 */
public class GridBagConstraintsExtended extends GridBagConstraints {
	
	private static final long serialVersionUID = 3530583877836650118L;
	
	/**
	 * Create a new {@link GridBagConstraintsExtended}
	 * @param top		Top margin
	 * @param left		Left margin
	 * @param bottom	Bottom margin
	 * @param right		Right margin
	 * @param gridx		X position
	 * @param gridy		Y position
	 */
	public GridBagConstraintsExtended (int top, int left, int bottom, int right, int gridx, int gridy) {
		insets = new Insets(top, left, bottom, right);
		fill = GridBagConstraints.BOTH;
		this.gridx = gridx;
		this.gridy = gridy;
	}

	/**
	 * Create a new {@link GridBagConstraintsExtended}
	 * @param fill		Fill mode
	 * @param top		Top margin
	 * @param left		Left margin
	 * @param bottom	Bottom margin
	 * @param right		Right margin
	 * @param gridx		X position
	 * @param gridy		Y position
	 */
	public GridBagConstraintsExtended (int fill, int top, int left, int bottom, int right, int gridx, int gridy) {
		insets = new Insets(top, left, bottom, right);
		this.fill = fill;
		this.gridx = gridx;
		this.gridy = gridy;
	}

	/**
	 * Create a new {@link GridBagConstraintsExtended}
	 * @param top			Top margin
	 * @param left			Left margin
	 * @param bottom		Bottom margin
	 * @param right			Right margin
	 * @param gridx			X position
	 * @param gridy			Y position
	 * @param gridwidth		Width size
	 * @param gridheight	Height size
	 */
	public GridBagConstraintsExtended (int top, int left, int bottom, int right, int gridx, int gridy, int gridwidth, int gridheight) {
		insets = new Insets(top, left, bottom, right);
		fill = GridBagConstraints.BOTH;
		this.gridx = gridx;
		this.gridy = gridy;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}
	
}
