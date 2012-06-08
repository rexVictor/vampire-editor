package vampire.editor.sheetloader.application;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import vampire.editor.plugin.Manager;
import vampire.editor.plugin.fullapi.sheet.view.ISubCategoryViewAttributes;

public class SubCategoryViewAttributesHolderTest {

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		Path viewatts = Paths.get("sheetpersistencyprototype", "subcategoryviewatts.json");
		Path fonts = Paths.get("sheetpersistencyprototype", "fonts.json");
		
		SubCategoryViewAttributesHolder holder = new SubCategoryViewAttributesHolder(viewatts, new FontHolder(fonts, new Manager().getResourcesHolder()));
		System.out.println(holder);
		fail("Not yet implemented");
	}

}
