package vampire.editor.sheetloader.application;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import vampire.editor.plugin.Manager;
import vampire.editor.plugin.fullapi.sheet.view.ITraitViewAttributes;

public class TraitViewAttributesHolderTest {

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		Path path = Paths.get("sheetpersistencyprototype", "traitviewatts.json");
		Path fontPath = Paths.get("sheetpersistencyprototype", "fonts.json");
		TraitViewAttributesHolder holder = new TraitViewAttributesHolder(path, new FontHolder(fontPath, new Manager().getResourcesHolder()));
		System.out.println(holder);
		fail("Not yet implemented");
	}

}
