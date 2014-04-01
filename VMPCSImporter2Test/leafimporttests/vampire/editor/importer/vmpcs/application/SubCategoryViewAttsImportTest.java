package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import vampire.editor.domain.sheet.view.MViewConstructors;
import vampire.editor.fileformat.vmpcs.domain.Constructors;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.ViewAttConstructors;
import vampire.editor.sheetloader.application.importer.mock.FontObjectsMock;
import vampire.editor.sheetloader.application.importer.mock.ResourcesHolderMock;

@SuppressWarnings({"nls"})
public class SubCategoryViewAttsImportTest {
	
	private Objects<SubCategoryViewAttributes> objects;
	
	private static ViewAttConstructors constructors;
	
	@BeforeClass
	public static void beforeClass(){
		constructors = new MViewConstructors();
		Constructors.viewAttConstructors = constructors;
	}
	
	@Before
	public void setup() throws Throwable{
		Path testPath = Paths.get("testcases","subcatviewatts","subcategoryviewatts.json");
		objects = new Objects<>(SubCategoryViewAttributes.class, testPath,
						new ResourcesHolderMock(), FontObjectsMock.getInstance(), null);
	}
	
	@Test
	public void testSubCategoryViewAttsImportTest1() throws Throwable{
		SubCategoryViewAttributes expected = constructors.createSubCategoryViewAttributes();
		expected.setExpandable(false);
		expected.setShowTitle(true);
		expected.setFont(FontObjectsMock.getInstance().getObjectByID(0));
		assertEquals(expected, objects.getObjectByID(0));
	}
	
	@Test
	public void testSubCategoryViewAttsImportTest2() throws Throwable{
		SubCategoryViewAttributes expected = constructors.createSubCategoryViewAttributes();
		expected.setExpandable(true);
		expected.setShowTitle(true);
		expected.setFont(FontObjectsMock.getInstance().getObjectByID(1));
		assertEquals(expected, objects.getObjectByID(1));
	}
	
	@Test
	public void testSubCategoryViewAttsImportTest3() throws Throwable{
		SubCategoryViewAttributes expected = constructors.createSubCategoryViewAttributes();
		expected.setExpandable(false);
		expected.setShowTitle(false);
		expected.setFont(FontObjectsMock.getInstance().getObjectByID(1));
		assertEquals(expected, objects.getObjectByID(2));
	}

}
