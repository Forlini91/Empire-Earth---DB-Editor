package gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

/**
 * This dialog show a progress bar with a label.
 * It's used to display the progress of an operation and support multi-thread operations.
 * @author MarcoForlini
 *
 */
public class DialogProgressBar extends JWindow {

	private static final long serialVersionUID = 4973946165556391770L;
	
	private JProgressBar progressBar = new JProgressBar();
	private int cur = 0;
	private int max;
	private float[] fractions;
	
	/**
	 * Create a progress bar dialog. It will have a label with the given text and a progress bar with the given max.
	 * @param text			Text of the label to display above the progress bar
	 * @param max			Progress bar max value
	 * @param multiThread	If true, the bar is used in a multiThread operation
	 */
	public DialogProgressBar(String text, int max, boolean multiThread){
		setBounds(GUI.getBounds(this, 400, 60));
		setAlwaysOnTop(true);
		setEnabled(false);
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(GUI.COLOR_UI_BACKGROUND);

		progressBar.setBorder(new EmptyBorder(4, 8, 8, 8));
		progressBar.setStringPainted(true);
		progressBar.setForeground(GUI.COLOR_UI_ELEMENT.brighter());
		progressBar.setBackground(GUI.COLOR_UI_BACKGROUND);

		JLabel lblTitle = new JLabel(text);
		lblTitle.setLabelFor(progressBar);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle, BorderLayout.NORTH);
		contentPane.add(progressBar, BorderLayout.CENTER);
		
		if (multiThread){
			fractions = new float[max];
			progressBar.setString(null);
			this.max = 10000;
		} else {
			this.max = max;
		}
		progressBar.setMaximum(this.max);

		setVisible(true);
	}
	
	/**
	 * Increase the current by 1. This method is synchronized, so it can be safety used by threads.
	 */
	public synchronized void updateSync(){
		cur++;
		progressBar.setValue(cur);
		progressBar.setString(cur + " / " + max);
	}

	/**
	 * Increase the current by 1.
	 */
	public void update(){
		cur++;
		progressBar.setValue(cur);
		progressBar.setString(cur + " / " + max);
	}

	/**
	 * Set the new cur value
	 * @param cur The new cur value
	 */
	public void update(int cur){
		this.cur = cur;
		progressBar.setValue(cur);
		progressBar.setString(cur + " / " + max);
	}

	/**
	 * Set the new fraction value
	 * @param fraction	The new fraction value
	 */
	public void updatePerc(float fraction){
		cur = (int) (max * fraction);
		progressBar.setValue(cur);
		progressBar.setString(null);
	}

	/**
	 * Set the new fraction value for the given thread
	 * @param fraction	The new fraction value
	 * @param threadIndex	The thread index
	 */
	public synchronized void updatePercPart(float fraction, int threadIndex){
		if (fraction < 0 || fraction > 1){
			fractions[threadIndex] = max;
		} else {
			fractions[threadIndex] = max * fraction;
		}
		long total = 0;
		for (double f : fractions){
			total += (long) f;
		}
		cur = (int) (total / fractions.length);
		progressBar.setValue(cur);
	}
	
}
