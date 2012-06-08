package vampire.editor.gui.swing.mainframe.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainFrame {
	
	private final JMenuBar menuBar;
	
	private final JPanel contentPane = new JPanel();
	
	private final JTabbedPane tabs = new JTabbedPane();
	
	
	
	public MainFrame(JMenuBar menuBar) {
		super();
		this.menuBar = menuBar;
		JFrame frame = new JFrame();
		frame.setContentPane(contentPane);
		frame.setSize(200, 300);
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
	}



	public void test(){
		
	}
	
	
	
	
	
	

}
