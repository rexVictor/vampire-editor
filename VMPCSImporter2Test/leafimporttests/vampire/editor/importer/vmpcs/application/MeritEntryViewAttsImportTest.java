package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import vampire.editor.domain.sheet.view.MViewConstructors;
import vampire.editor.fileformat.vmpcs.domain.Constructors;
import vampire.editor.plugin.api.domain.sheet.view.MeritEntryViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.ViewAttConstructors;
import vampire.editor.sheetloader.application.importer.mock.FontObjectsMock;
import vampire.editor.sheetloader.application.importer.mock.ResourcesHolderMock;

@SuppressWarnings({"nls"})
public class MeritEntryViewAttsImportTest {
	
	private Objects<MeritEntryViewAttributes> objects;
	
	private FontObjectsMock fontObjects;
	
	private static ViewAttConstructors constructors ;
	
	@BeforeClass
	public static void beforeClass(){
		constructors = new MViewConstructors();
		Constructors.viewAttConstructors = constructors;
	}
	
	
	@Before
	public void setup() throws Throwable{
		Path testPath = Paths.get("testcases","meritentryviewatts","meritentryviewatts.json");
		fontObjects = FontObjectsMock.getInstance();
		objects = new Objects<>(MeritEntryViewAttributes.class, testPath,
						new ResourcesHolderMock(), fontObjects, null);
	}
	
	@Test
	public void testMeritEntryViewAttsImportTest1() throws Throwable{
		MeritEntryViewAttributes expected = constructors.createMeritEntryViewAttributes();
		expected.setFont(fontObjects.getObjectByID(0));
		assertEquals(expected, objects.getObjectByID(0));
	}
	
	@Test
	public void testMeritEntryViewAttsImportTest2() throws Throwable{
		MeritEntryViewAttributes expected = constructors.createMeritEntryViewAttributes();
		expected.setFont(fontObjects.getObjectByID(1));
		assertEquals(expected, objects.getObjectByID(1));
	}
	
}
