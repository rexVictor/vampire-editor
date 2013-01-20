package vampire.editor.gui.swing.mainframe.view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import vampire.editor.gui.swing.view.SSheetView;
import vampire.editor.plugin.api.view.sheet.SheetView;

public class MainFrame {
	
//	private final JMenuBar menuBar;
	
	private final JPanel contentPane = new JPanel();
	
//	private final JTabbedPane tabs = new JTabbedPane();
	
	private final JFrame frame = new JFrame();
	
	
	
	public MainFrame(JMenuBar menuBar) {
		super();
//		this.menuBar = menuBar;
		frame.setContentPane(contentPane);
		frame.setSize(200, 300);
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setLayout(new GridLayout(1,1));
	}
	
	public void setVisible(){
		frame.setVisible(true);
	}
	
	public void addSheetView(SheetView view){
		if (view instanceof SSheetView){
			contentPane.add(((SSheetView) view).getPanel());
		}
	}


	
	
	
	
	
	
	

}
