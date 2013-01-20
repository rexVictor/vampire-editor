package vampire.editor.gui.swing.mainframe.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import vampire.editor.gui.swing.view.SSheetView;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.view.sheet.SheetView;

public class SheetViewTabPanel implements ChangeListener{
	
	private class CloseListener implements ActionListener{
		
		//private final SheetView view;
		
		//private final ManagerAPI manager;
		
		public CloseListener(SheetView view, ManagerAPI manager) {
			super();
			//this.view = view;
			//this.manager = manager;
		}



		@Override
		public void actionPerformed(ActionEvent e) {
			//manager.closed(view);
		}
		
	}
	
	private final ManagerAPI manager;
	
	
	
	public SheetViewTabPanel(ManagerAPI manager) {
		super();
		this.manager = manager;
	}

	private final JTabbedPane tabPane = new JTabbedPane();
	
	public void add(SheetControllerAPI sheetController){
		SheetView sheetView = sheetController.getView();
		if (sheetView instanceof SSheetView){
			tabPane.add(new SheetViewPanel(((SSheetView) sheetView).getPanel()));
			TabComponent tabComponent = new TabComponent();
			tabComponent.addCloseListener(new CloseListener(sheetView, manager));
			tabPane.setTabComponentAt(tabPane.getTabCount()-1, tabComponent.getComponent());
		}
		
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		
	}
	
	

}
