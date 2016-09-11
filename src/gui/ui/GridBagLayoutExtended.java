package gui.ui;

import java.awt.GridBagLayout;
import java.util.Arrays;


/**
 * A customized {@link GridBagLayout} with useful constructors
 * @author MarcoForlini
 */
public class GridBagLayoutExtended extends GridBagLayout {
	
	private static final long serialVersionUID = -4735262329636335378L;
	
	
	/**
	 * Create a new {@link GridBagLayoutExtended}
	 * @param columnWidths		The size of the columns
	 * @param rowHeights		The size of the rows
	 * @param columnWeights		The weight of the columns
	 * @param rowWeights		The weight of the rows
	 */
	public GridBagLayoutExtended (int[] columnWidths, int[] rowHeights, double[] columnWeights, double[] rowWeights) {
		this.columnWidths = columnWidths;
		this.rowHeights = rowHeights;
		this.columnWeights = columnWeights;
		this.rowWeights = rowWeights;
	}

	
	/**
	 * Create a new {@link GridBagLayoutExtended}
	 * @param numRows		Number of rows
	 * @param numCols		Number of columns
	 * @param minWidth		Minimum width for the columns
	 * @param minHeight		Minimum height for the rows
	 */
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
	
	
	/**
	 * Create a new {@link GridBagLayoutExtended}
	 * @param numRows		Number of rows
	 * @param numCols		Number of columns
	 * @param minWidth		Minimum width for the columns
	 * @param minHeight		Minimum height for the rows
	 * @param columnWeight	The weight of the columns
	 * @param rowWeight		The weight of the rows
	 */
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
