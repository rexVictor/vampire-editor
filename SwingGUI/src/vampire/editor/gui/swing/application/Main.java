package vampire.editor.gui.swing.application;

import javax.swing.JFrame;

import vampire.editor.gui.swing.view.SValueView;

public class Main {
	
	/*
	 * Testzwecke
	 */
	public static void main(String[] args){
		JFrame frame = new JFrame();
		SValueView view = new SValueView();
		frame.setContentPane(view.getPanel());
		frame.pack();
		frame.setVisible(true);
		frame.pack();
	}

}
