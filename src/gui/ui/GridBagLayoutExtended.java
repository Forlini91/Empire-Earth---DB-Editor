package gui.ui;

import java.awt.GridBagLayout;
import java.util.Arrays;

public class GridBagLayoutExtended extends GridBagLayout {

	private static final long serialVersionUID = -4735262329636335378L;

	public GridBagLayoutExtended (int[] columnWidths, int[] rowHeights, double[] columnWeights, double[] rowWeights) {
		this.columnWidths = columnWidths;
		this.rowHeights = rowHeights;
		this.columnWeights = columnWeights;
		this.rowWeights = rowWeights;
	}
	
	public GridBagLayoutExtended (int numRows, int numCols, int minWidth, int minHeight) {
		columnWidths = new int[numCols];
		rowHeights = new int[numRows];
		columnWeights = new double[numCols];
		rowWeights = new double[numRows];
		Arrays.fill(columnWidths, minWidth);
		Arrays.fill(rowHeights, minHeight);
		Arrays.fill(columnWeights, 1.0);
		Arrays.fill(rowWeights, 1.0);
	}

	public GridBagLayoutExtended (int numRows, int numCols, int minWidth, int minHeight, double columnWeight, double rowWeight) {
		columnWidths = new int[numCols];
		rowHeights = new int[numRows];
		columnWeights = new double[numCols];
		rowWeights = new double[numRows];
		Arrays.fill(columnWidths, minWidth);
		Arrays.fill(rowHeights, minHeight);
		Arrays.fill(columnWeights, columnWeight);
		Arrays.fill(rowWeights, rowWeight);
	}

}
