package vampire.editor.tests.gui;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.junit.Test;


import vampire.editor.application.SheetControllerFactory;
import vampire.editor.domain.sheet.Sheet;
import vampire.editor.gui.swing.application.SheetViewFactory;
import vampire.editor.gui.swing.view.DictionaryTestImplementation;
import vampire.editor.gui.swing.view.SSheetView;
import vampire.editor.plugin.Manager;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.sheetloader.application.importer.VMPCSImporter;

public class ViewTest {

	@Test
	public void test() throws  Throwable {
	
		UIManager.setLookAndFeel(NimbusLookAndFeel.class.getName());	
	
		//SheetParser parser = new SheetParser(Paths.get("sheetpersistencyprototype"),  new Manager());
		VMPCSImporter importer = new VMPCSImporter(new Manager().getResourcesHolder());
		Sheet sheet = importer.load(Paths.get("sheetpersistencyprototype"));
		importer = null;
		
		SheetViewFactory factory = new SheetViewFactory(new DictionaryTestImplementation());
		SSheetView view = factory.buildSheetView(sheet);
		
		SheetControllerFactory controllerFactory = new SheetControllerFactory();
		
		SheetControllerAPI controller = controllerFactory.buildSheetController(sheet, view);
		JPanel panel = view.getPanel();
		JFrame frame = new JFrame();
		frame.setContentPane(panel);
		frame.setAlwaysOnTop(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		while (frame.isVisible()){ 
			Thread.sleep(10);
			
			
		}
		assertTrue(true);
		
		
		
	}
	
	


}
