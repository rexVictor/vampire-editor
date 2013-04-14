package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import vampire.editor.domain.sheet.view.MViewConstructors;
import vampire.editor.fileformat.vmpcs.domain.Constructors;
import vampire.editor.plugin.api.domain.sheet.view.MetaEntryViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.ViewAttConstructors;
import vampire.editor.sheetloader.application.importer.mock.FontObjectsMock;
import vampire.editor.sheetloader.application.importer.mock.ResourcesHolderMock;

public class MetaEntryViewAttsImportTest {
	
	private Objects<MetaEntryViewAttributes> objects;
	
	private FontObjectsMock fontObjects;
	
	private static ViewAttConstructors constructors;
	
	@BeforeClass
	public static void beforeClass(){
		constructors = new MViewConstructors();
		Constructors.viewAttConstructors = constructors;
	}
	
	
	@Before
	public void setup() throws Throwable{
		Path testPath = Paths.get("testcases","metaentryviewatts","metaentryviewatts.json");
		fontObjects = FontObjectsMock.getInstance();
		objects = new Objects<>(MetaEntryViewAttributes.class, testPath,
						new ResourcesHolderMock(), fontObjects);
	}
	
	@Test
	public void testMetaEntryViewAttsImportTest1() throws Throwable{
		MetaEntryViewAttributes expected = constructors.createMetaEntryViewAttributes();
		expected.setContentFont(fontObjects.getObjectByID(0));
		expected.setLineCount(2);
		expected.setTitleFont(fontObjects.getObjectByID(0));
		expected.setTranslate(true);
		assertEquals(expected, objects.getObjectByID(0));
	}
	
	@Test
	public void testMetaEntryViewAttsImportTest2() throws Throwable{
		MetaEntryViewAttributes expected = constructors.createMetaEntryViewAttributes();
		expected.setContentFont(fontObjects.getObjectByID(1));
		expected.setLineCount(1);
		expected.setTitleFont(fontObjects.getObjectByID(0));
		expected.setTranslate(false);
		assertEquals(expected, objects.getObjectByID(1));
	}


}
