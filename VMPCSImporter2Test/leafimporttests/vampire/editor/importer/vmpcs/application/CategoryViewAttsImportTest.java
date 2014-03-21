package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import vampire.editor.domain.sheet.view.MViewConstructors;
import vampire.editor.fileformat.vmpcs.domain.Constructors;
import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.ViewAttConstructors;
import vampire.editor.sheetloader.application.importer.mock.FontObjectsMock;
import vampire.editor.sheetloader.application.importer.mock.ResourcesHolderMock;

public class CategoryViewAttsImportTest {

	private Objects<CategoryViewAttributes> objects;
	
	private static ViewAttConstructors constructors;
	
	@BeforeClass
	public static void beforeClass(){
		constructors = new MViewConstructors();
		Constructors.viewAttConstructors = constructors;
	}
	
	@Before
	public void setup() throws Throwable{
		Path testPath = Paths.get("testcases","catviewatts","categoryviewatts.json");
		objects = new Objects<>(CategoryViewAttributes.class, testPath,
						new ResourcesHolderMock(), FontObjectsMock.getInstance(), null);
	}
	
	@Test
	public void testCategoryViewAttsImportTest1() throws Throwable{
		CategoryViewAttributes expected = constructors.createCategoryViewAttributes();
		expected.setShowLine(true);
		//expected.setImage("vtmdefault");
		expected.setTitle("");
		expected.setFont(FontObjectsMock.getInstance().getObjectByID(1));
		assertEquals(expected, objects.getObjectByID(0));
	}
	
	@Test
	public void testCategoryViewAttsImportTest2() throws Throwable{
		CategoryViewAttributes expected = constructors.createCategoryViewAttributes();
		expected.setShowLine(false);
		//expected.setImage("vtmdefault2");
		expected.setTitle("");
		expected.setFont(FontObjectsMock.getInstance().getObjectByID(0));
		assertEquals(expected, objects.getObjectByID(1));
	}
	

}
