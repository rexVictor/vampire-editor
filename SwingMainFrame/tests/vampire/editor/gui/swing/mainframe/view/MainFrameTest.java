package vampire.editor.gui.swing.mainframe.view;

import static org.junit.Assert.*;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.junit.Test;

public class MainFrameTest {

	@Test
	public void test() throws InterruptedException {
		JMenuBar menuBar = new JMenuBar();
		MainFrame frame = new MainFrame(menuBar);
		Thread.sleep(1000);
		JMenu file = new JMenu("File");
		menuBar.add(file);
		Thread.sleep(1000);
		file.add(new JMenuItem("open"));
		Thread.sleep(2000);
	}

}
