package gui.ui;


import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Insets;


/**
 * A customized {@link GridLayout}
 * @author MarcoForlini
 */
public class GridLayoutExtended extends GridLayout {

	private static final long serialVersionUID = 4063754464776709203L;

	private boolean vertical;
	private boolean rightToLeft;

	/**
	 * Create a new {@link GridLayoutExtended}
	 * @param vertical If true, place the elements in column, else in row
	 * @param rightToLeft If true, place the element from right to left
	 * @param rows Number of rows
	 * @param cols Number of columns
	 * @param hgap Horizontal space between columns
	 * @param vgap Vertical space between rows
	 */
	public GridLayoutExtended (boolean vertical, boolean rightToLeft, int rows, int cols, int hgap, int vgap) {
		super (Math.max (rows, 0), Math.max (cols, 0), hgap, vgap);
		this.vertical = vertical;
		this.rightToLeft = rightToLeft;
	}

	@Override
	public void layoutContainer (Container parent) {
		synchronized (parent.getTreeLock ()) {
			Insets insets = parent.getInsets ();
			int ncomponents = parent.getComponentCount ();
			int nrows = getRows ();
			int ncols = getColumns ();

			if (ncomponents == 0) {
				return;
			}
			int fakeNum = Math.max (ncomponents, nrows * ncols);
			if (ncols == 0) {
				ncols = (fakeNum + nrows - 1) / nrows;
			} else if (nrows == 0) {
				nrows = (fakeNum + ncols - 1) / ncols;
			} else if (vertical) {
				ncols = (fakeNum + nrows - 1) / nrows;
			} else {
				nrows = (fakeNum + ncols - 1) / ncols;
			}
			int w = parent.getWidth () - (insets.left + insets.right);
			int h = parent.getHeight () - (insets.top + insets.bottom);
			w = (w - (ncols - 1) * getHgap ()) / ncols;
			h = (h - (nrows - 1) * getVgap ()) / nrows;

			if ( !rightToLeft) {
				for (int c = 0, x = insets.left; c < ncols; c++, x += w + getHgap ()) {
					for (int r = 0, y = insets.top; r < nrows; r++, y += h + getVgap ()) {
						int i = (vertical ? c * nrows + r : r * ncols + c);
						if (i < ncomponents) {
							parent.getComponent (i).setBounds (x, y, w, h);
						}
					}
				}
			} else {
				for (int c = 0, x = parent.getWidth () - insets.right - w; c < ncols; c++, x -= w + getHgap ()) {
					for (int r = 0, y = insets.top; r < nrows; r++, y += h + getVgap ()) {
						int i = (vertical ? c * nrows + r : r * ncols + c);
						if (i < ncomponents) {
							parent.getComponent (i).setBounds (x, y, w, h);
						}
					}
				}
			}
		}
	}

}
