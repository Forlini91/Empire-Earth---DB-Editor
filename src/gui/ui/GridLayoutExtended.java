package gui.ui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Insets;


public class GridLayoutExtended extends GridLayout {

	private static final long serialVersionUID = 4063754464776709203L;

	boolean vertical;
	boolean rightToLeft;
	int minRows = 0;
	int minCols = 0;
	
	public GridLayoutExtended (boolean vertical, boolean rightToLeft, int rows, int cols, int hgap, int vgap) {
		super(Math.max(rows, 0), Math.max(cols, 0), hgap, vgap);
		minRows = Math.abs(Math.min(rows, -1));
		minCols = Math.abs(Math.min(cols, -1));
		this.vertical = vertical;
		this.rightToLeft = rightToLeft;
	}

	
	@Override
	public void layoutContainer(Container parent) {
		synchronized (parent.getTreeLock()) {
			Insets insets = parent.getInsets();
			int ncomponents = parent.getComponentCount();
			int nrows = getRows();
			int ncols = getColumns();

			if (ncomponents == 0) {
				return;
			}
			int fakeNum = Math.max(ncomponents, nrows*ncols);
			if (ncols == 0) {
				ncols = (fakeNum + nrows - 1) / nrows;
			} else if (nrows == 0){
				nrows = (fakeNum + ncols - 1) / ncols;
			} else if (vertical){
				ncols = (fakeNum + nrows - 1) / nrows;
			} else {
				nrows = (fakeNum + ncols - 1) / ncols;
			}
			int w = parent.getWidth() - (insets.left + insets.right);
			int h = parent.getHeight() - (insets.top + insets.bottom);
			w = (w - (ncols - 1) * getHgap()) / ncols;
			h = (h - (nrows - 1) * getVgap()) / nrows;

			if (!rightToLeft) {
				for (int c = 0, x = insets.left ; c < ncols ; c++, x += w + getHgap()) {
					for (int r = 0, y = insets.top ; r < nrows ; r++, y += h + getVgap()) {
						int i = (vertical ? c * nrows + r : r * ncols + c);
						if (i < ncomponents) {
							parent.getComponent(i).setBounds(x, y, w, h);
						}
					}
				}
			} else {
				for (int c = 0, x = parent.getWidth() - insets.right - w; c < ncols ; c++, x -= w + getHgap()) {
					for (int r = 0, y = insets.top ; r < nrows ; r++, y += h + getVgap()) {
						int i = (vertical ? c * nrows + r : r * ncols + c);
						if (i < ncomponents) {
							parent.getComponent(i).setBounds(x, y, w, h);
						} else {

						}
					}
				}
			}
		}
	}
	
}
