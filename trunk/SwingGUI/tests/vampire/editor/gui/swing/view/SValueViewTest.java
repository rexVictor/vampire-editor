package vampire.editor.gui.swing.view;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.Test;

public class SValueViewTest {

	@Test
	public void test() {
		JFrame frame = new JFrame();
		SValueView view = new SValueView();
		frame.setContentPane(view.getPanel());
		frame.pack();
		frame.setVisible(true);
		frame.pack();
	}

}
