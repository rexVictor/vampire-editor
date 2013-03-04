package vampire.editor.sometests;

import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;

import javax.swing.JFrame;

import org.junit.Test;

import vampire.editor.application.sheet.controller.SheetController;
import vampire.editor.application.sheet.controller.SheetControllerFactory;
import vampire.editor.domain.sheet.ValueTest;
import vampire.editor.domain.sheet.VampireDocument;
import vampire.editor.gui.swing.application.SheetViewFactory;
import vampire.editor.gui.swing.view.SSheetView;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.sheetloader.application.importer.ResourcesHolderTestImplementation;
import vampire.editor.sheetloader.application.importer.VMPCSImporter;

public class ImporterTime {
	
	
	
	private void loadPackage(String packageName) throws Throwable{
		String resource = packageName.replace(".", "/");
		Enumeration<URL> urls = this.getClass().getClassLoader().getResources(resource);
		while (urls.hasMoreElements()){
			URL url = urls.nextElement();
			Path path = Paths.get(url.toURI());
			loadClasses(path, path, packageName);
		}
	}
	
	private void loadClasses(Path path, Path origin, String packageName) throws Throwable{
		DirectoryStream<Path> stream = Files.newDirectoryStream(path);
		for (Path p : stream){
			if (Files.isDirectory(p)){
				loadClasses(p, origin, packageName);
			}
			else{
				Path relative = origin.relativize(p);
				String className = packageName+"."+relative.toString().replace("/", ".");
				className = className.substring(0, className.length()-6);
				Class.forName(className);
			}
		}
	}
	
	@Test
	public void test() throws Throwable {
		Class.forName(ValueTest.class.getName());
		loadPackage("vampire.editor");
		Path path = Paths.get("defaultsheets", "vtmdefault.vmpcs");
		ResourcesHolderAPI resources = new ResourcesHolderTestImplementation();
		long start = System.currentTimeMillis();
		VMPCSImporter importer = new VMPCSImporter(resources, path);
		VampireDocument document = importer.load();
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("Import: ");
		System.out.println("millis: "+duration);
		System.out.println("secs: "+duration/1000);
		
		start = System.currentTimeMillis();
		importer = new VMPCSImporter(resources, path); 
		@SuppressWarnings("unused")
		VampireDocument document2 = importer.load();
		end = System.currentTimeMillis();
		duration = end-start;
		System.out.println("Import2: ");
		System.out.println("millis: "+duration);
		System.out.println("secs: "+duration/1000);
		
		
		start = System.currentTimeMillis();
		SheetViewFactory factory = new SheetViewFactory(resources);
		
		
		SSheetView view = factory.buildSheetView(document);
		end = System.currentTimeMillis();
		duration = end - start;
		System.out.println("ViewBuild: ");
		System.out.println("millis: "+duration);
		System.out.println("secs: "+duration/1000);
		
		start = System.currentTimeMillis();
		@SuppressWarnings("unused")
		SSheetView view2 = factory.buildSheetView(document);
		end = System.currentTimeMillis();
		duration = end - start;
		System.out.println("ViewBuild2: ");
		System.out.println("millis: "+duration);
		System.out.println("secs: "+duration/1000);
		
		SheetControllerFactory controllerFactory = new SheetControllerFactory();
		start = System.currentTimeMillis();
		@SuppressWarnings("unused")
		SheetController controller = controllerFactory.buildSheetController(document, view);
		
		end = System.currentTimeMillis();
		duration = end - start;
		System.out.println("ControllerBuild: ");
		System.out.println("millis: "+duration);
		System.out.println("secs: "+duration/1000);
		
		JFrame frame = new JFrame();
		frame.setContentPane(view.getPanel());
		frame.pack();
		System.out.println("Visible: ");
		start = System.currentTimeMillis();
		frame.setVisible(true);
		while(!frame.isVisible()){
			Thread.sleep(1);
		}
		end = System.currentTimeMillis();
		duration = end - start;
		System.out.println("millis: "+duration);
		System.out.println("secs: "+duration/1000);
		
		while(frame.isVisible()){
			Thread.sleep(100);
		}
		
		
	}

}
