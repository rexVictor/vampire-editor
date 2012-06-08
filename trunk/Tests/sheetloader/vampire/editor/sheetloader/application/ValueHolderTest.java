package vampire.editor.sheetloader.application;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


public class ValueHolderTest {

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		Path path = Paths.get("sheetpersistencyprototype", "values.json");
		ValueHolder holder = new ValueHolder(path);
		System.out.println(holder);
		fail("Not implemented");
	}

}
