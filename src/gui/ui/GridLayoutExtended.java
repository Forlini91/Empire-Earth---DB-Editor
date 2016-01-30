package gui.ui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Insets;


public class GridLayoutExtended extends GridLayout {

	private static final long serialVersionUID = 4063754464776709203L;

	boolean vertical;
	boolean rightToLeft;
	int rows;
	int cols;
	int hgap;
	int vgap;
	
	public GridLayoutExtended (boolean vertical, boolean rightToLeft, int rows, int cols, int hgap, int vgap) {
		super(rows, cols, hgap, vgap);
		this.vertical = vertical;
		this.rightToLeft = rightToLeft;
		this.rows = rows;
		this.cols = cols;
		this.hgap = hgap;
		this.vgap = vgap;
	}
	
	@Override
	public void layoutContainer(Container parent) {
		synchronized (parent.getTreeLock()) {
			Insets insets = parent.getInsets();
			int ncomponents = parent.getComponentCount();
			int nrows = rows;
			int ncols = cols;

			if (ncomponents == 0) {
				return;
			}
			if (ncols == 0) {
				ncols = (ncomponents + nrows - 1) / nrows;
			} else if (nrows == 0){
				nrows = (ncomponents + ncols - 1) / ncols;
			} else if (vertical){
				ncols = (ncomponents + nrows - 1) / nrows;
			} else {
				nrows = (ncomponents + ncols - 1) / ncols;
			}
			int w = parent.getWidth() - (insets.left + insets.right);
			int h = parent.getHeight() - (insets.top + insets.bottom);
			w = (w - (ncols - 1) * hgap) / ncols;
			h = (h - (nrows - 1) * vgap) / nrows;

			if (!rightToLeft) {
				for (int c = 0, x = insets.left ; c < ncols ; c++, x += w + hgap) {
					for (int r = 0, y = insets.top ; r < nrows ; r++, y += h + vgap) {
						int i = (vertical ? c * nrows + r : r * ncols + c);
						if (i < ncomponents) {
							parent.getComponent(i).setBounds(x, y, w, h);
						}
					}
				}
			} else {
				for (int c = 0, x = parent.getWidth() - insets.right - w; c < ncols ; c++, x -= w + hgap) {
					for (int r = 0, y = insets.top ; r < nrows ; r++, y += h + vgap) {
						int i = (vertical ? c * nrows + r : r * ncols + c);
						if (i < ncomponents) {
							parent.getComponent(i).setBounds(x, y, w, h);
						}
					}
				}
			}
		}
	}
	
}
