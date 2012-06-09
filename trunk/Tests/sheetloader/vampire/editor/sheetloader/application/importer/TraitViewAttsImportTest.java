package vampire.editor.sheetloader.application.importer;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import vampire.editor.domain.sheet.view.TraitViewAttributes;
import vampire.editor.plugin.Manager;

public class TraitViewAttsImportTest {

	@Test
	public void testTraitViewImport() throws JsonParseException, JsonMappingException, IOException {
		Path path = Paths.get("sheetpersistencyprototype");
		Objects<TraitViewAttributes> objects = new Objects<>(path, TraitViewAttributes.class, new Manager().getResourcesHolder());
		int id = 0;
		TraitViewAttributes atts = null;
		while ((atts = objects.getObjectByID(id)) != null){
			System.out.println(atts);
			id++;
		}
		fail("Not yet implemented");
		
		
	}

}
