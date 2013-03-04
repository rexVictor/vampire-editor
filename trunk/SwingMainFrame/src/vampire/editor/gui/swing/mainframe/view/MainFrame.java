package vampire.editor.gui.swing.mainframe.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.plugin.ManagerAPI;

public class MainFrame {
	
//	private final JMenuBar menuBar;
	
	private final JPanel contentPane = new JPanel();
	
//	private final JTabbedPane tabs = new JTabbedPane();
	
	private final JFrame frame = new JFrame();
	
	private final SheetViewTabPanel tabPanel;
	
	private final FormLayout layout = new FormLayout("10cm, pref, 10cm", "20cm");
	
	
	public MainFrame(JMenuBar menuBar, ManagerAPI manager) {
		super();
//		this.menuBar = menuBar;
		
		contentPane.setLayout(layout);
		frame.setContentPane(contentPane);
		frame.setSize(1000, 700);
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tabPanel = new SheetViewTabPanel(manager);
		CellConstraints constraints = new CellConstraints(2,1,1,1);
		contentPane.add(tabPanel.getTabbedPane(), constraints);
	}
	
	public void setVisible(){
		frame.setVisible(true);
	}
	
	public void addSheetView(SheetControllerAPI controllerAPI){
		tabPanel.add(controllerAPI);
	}

}
