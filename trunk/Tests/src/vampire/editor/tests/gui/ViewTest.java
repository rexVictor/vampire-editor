package vampire.editor.tests.gui;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.editor.domain.sheet.Classes;
import vampire.editor.domain.sheet.Sheet;
import vampire.editor.gui.swing.application.SheetViewFactory;
import vampire.editor.gui.swing.view.DictionaryTestImplementation;
import vampire.editor.gui.swing.view.SSheetView;
import vampire.editor.gui.swing.view.SSubCategoryView;
import vampire.editor.plugin.api.view.sheet.SheetView;
import vampire.editor.plugin.fullapi.sheet.IData;
import vampire.editor.plugin.fullapi.sheet.ISheet;
import vampire.editor.sheetloader.application.TraitsParser;

public class ViewTest {

	@Test
	public void test() throws  Throwable {
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		Path path = Paths.get("SheetPersistencyProtoType","default.json");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, ?> map = mapper.readValue(path.toFile(), Map.class);
		Object object = map.get("traits");
		TraitsParser parser = new TraitsParser(new Classes());
		Sheet sheet = new Sheet();
		sheet.setCategories(parser.getCategories(object));
		SheetViewFactory factory = new SheetViewFactory(new DictionaryTestImplementation());
		SSheetView view = factory.buildSheetView(sheet);
		JPanel panel = view.getPanel();
		JFrame frame = new JFrame();
		frame.setContentPane(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//SSubCategoryView subCategoryView = (SSubCategoryView) view.getCategoryViews().get(0).getEntries().get(0);
		/*JFrame frame2 = new JFrame();
		frame2.setContentPane(subCategoryView.getPanel());
		frame2.pack();
		frame2.setVisible(true);*/
		Thread.sleep(10000);
		
	}

}
