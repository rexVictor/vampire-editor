package vampire.editor.sheetloader.application;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import vampire.editor.plugin.Manager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class FontHolderTest {

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		Path path = Paths.get("sheetpersistencyprototype", "fonts.json");
		FontHolder fontHolder = new FontHolder(path, new Manager().getResourcesHolder());
		System.out.println(fontHolder);
		fail("Not implemented");
	}

}
