package vampire.editor.sheetloader.application;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import vampire.editor.domain.sheet.Classes;
import vampire.editor.plugin.fullapi.sheet.IValue;

public class ValueHolderTest {

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		Path path = Paths.get("sheetpersistencyprototype", "values.json");
		Class<? extends IValue> clazz = new Classes().getImplementingClassOf(IValue.class);
		ValueHolder holder = new ValueHolder(path, clazz);
		System.out.println(holder);
		fail("Not implemented");
	}

}
