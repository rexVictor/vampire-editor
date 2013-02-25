package vampire.editor.tests.gui;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.junit.Test;


import vampire.editor.application.sheet.controller.SheetControllerFactory;
import vampire.editor.domain.sheet.VampireDocument;
import vampire.editor.gui.swing.application.SheetViewFactory;
import vampire.editor.gui.swing.view.SSheetView;

import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.sheetloader.application.importer.ResourcesHolderTestImplementation;
import vampire.editor.sheetloader.application.importer.VMPCSImporter;

public class ViewTest {

	@Test
	public void test() throws  Throwable {
		UIManager.setLookAndFeel(NimbusLookAndFeel.class.getName());
		ResourcesHolderAPI resourcesHolder = new ResourcesHolderTestImplementation();
		
		Path path = Paths.get("sheetpersistencyprototype");
		VMPCSImporter importer = new VMPCSImporter(resourcesHolder, path);
		VampireDocument sheet = importer.load();
		importer = null;
		SheetViewFactory factory = new SheetViewFactory(resourcesHolder);
		SSheetView view = factory.buildSheetView(sheet);
		
		SheetControllerFactory controllerFactory = new SheetControllerFactory();
		
		controllerFactory.buildSheetController(sheet, view);
		JPanel panel = view.getPanel();
		JFrame frame = new JFrame();
		frame.setContentPane(panel);
		frame.setAlwaysOnTop(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		while (frame.isVisible()){ 
			Thread.sleep(10);
		}
		assertTrue(true);
		
	}
	
	


}
