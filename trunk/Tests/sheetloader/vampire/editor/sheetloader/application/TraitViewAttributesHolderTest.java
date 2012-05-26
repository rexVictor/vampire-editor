package vampire.editor.sheetloader.application;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import vampire.editor.domain.sheet.Classes;
import vampire.editor.plugin.fullapi.sheet.view.ITraitViewAttributes;

public class TraitViewAttributesHolderTest {

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		Path path = Paths.get("sheetpersistencyprototype", "traitviewatts.json");
		Path fontPath = Paths.get("sheetpersistencyprototype", "fonts.json");
		Class<? extends ITraitViewAttributes> clazz = new Classes().getImplementingClassOf(ITraitViewAttributes.class);
		TraitViewAttributesHolder holder = new TraitViewAttributesHolder(path, clazz, new FontHolder(fontPath));
		System.out.println(holder);
		fail("Not yet implemented");
	}

}
