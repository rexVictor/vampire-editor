package vampire.editor.gui.swing.mainframe.view;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.junit.Test;

import vampire.editor.application.sheet.controller.SheetController;
import vampire.editor.application.sheet.controller.SheetControllerFactory;
import vampire.editor.domain.sheet.VampireDocument;
import vampire.editor.gui.swing.application.SheetViewFactory;
import vampire.editor.gui.swing.view.SSheetView;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.sheetloader.application.importer.ResourcesHolderTestImplementation;
import vampire.editor.sheetloader.application.importer.VMPCSImporter;
import vampire.editor.tests.TestManager;

public class SheetViewTabPanelTest {


	@Test
	public void test() throws Throwable {
		SheetViewTabPanel tabPanel = new SheetViewTabPanel(new TestManager());
		ResourcesHolderAPI resourcesHolder = new ResourcesHolderTestImplementation();
		
		SheetControllerFactory controllerFactory = new SheetControllerFactory();
		
		Path path1 = Paths.get("sheetpersistencyprototype");
		VMPCSImporter importer1 = new VMPCSImporter(resourcesHolder, path1);
		VampireDocument sheet1 = importer1.load();
		SheetViewFactory factory = new SheetViewFactory(resourcesHolder);
		SSheetView view1 = factory.buildSheetView(sheet1);
		
		SheetController controller1 = controllerFactory.buildSheetController(sheet1, view1);
		tabPanel.add(controller1);
		
		Path path2 = Paths.get("sheetpersistencyprototype");
		VMPCSImporter importer2 = new VMPCSImporter(resourcesHolder, path2);
		VampireDocument sheet2 = importer2.load();
		SSheetView view2 = factory.buildSheetView(sheet2);
		
		SheetController controller2 = controllerFactory.buildSheetController(sheet2, view2);
		tabPanel.add(controller2);
		
		JTabbedPane tabbedPane = tabPanel.getTabbedPane();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(tabbedPane);
		frame.setVisible(true);
		frame.pack();
		
		while(frame.isVisible()){
			Thread.sleep(10);
		}
		fail();
	}

}
