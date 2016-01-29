package gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import dbmanager.Core;
import gui.components.AbstractGUI;

public class GUIProgressDialog extends JWindow {

	private static final long serialVersionUID = -2374279140190562979L;
	private JProgressBar progressBar = new JProgressBar();
	private int cur = 0;
	private int max;
	
	/**
	 * @wbp.parser.constructor
	 */
	public GUIProgressDialog(String name){
		this(name, 10000);
	}

	public GUIProgressDialog(String name, int max){
		setBounds(AbstractGUI.getBounds(this, 400, 60));
		setAlwaysOnTop(true);
		setEnabled(false);
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Core.uiColorBackground);

		progressBar.setBorder(new EmptyBorder(4, 8, 8, 8));
		progressBar.setStringPainted(true);
		progressBar.setMaximum(max);
		progressBar.setForeground(Core.uiColorElement.brighter());
		progressBar.setBackground(Core.uiColorBackground);

		JLabel lblTitle = new JLabel(name);
		lblTitle.setLabelFor(progressBar);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle, BorderLayout.NORTH);
		contentPane.add(progressBar, BorderLayout.CENTER);
		this.max = max;
		setVisible(true);
	}
	
	public synchronized void updateSync(){
		cur++;
		progressBar.setValue(cur);
		progressBar.setString(cur + " / " + max);
	}

	public void update(){
		cur++;
		progressBar.setValue(cur);
		progressBar.setString(cur + " / " + max);
	}

	public void update(int cur, int max){
		this.max = max;
		this.cur = cur;
		progressBar.setMaximum(max);
		progressBar.setValue(cur);
		progressBar.setString(cur + " / " + max);
	}

	public void update(float percent){
		max = 10000;
		cur = (int) (10000*percent);
		progressBar.setMaximum(cur);
		progressBar.setValue(max);
		progressBar.setString(null);
	}
	
}
