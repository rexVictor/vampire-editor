package vampire.editor.sheetloader.application;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import vampire.editor.domain.sheet.Classes;
import vampire.editor.plugin.fullapi.sheet.view.IValueViewAttributes;
import vampire.editor.sheetloader.application.ValueViewAttributesHolder;

public class ValueViewAttributesHolderTest {

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		Path path = Paths.get("sheetpersistencyprototype", "valueviewatts.json");
		ValueViewAttributesHolder holder = new ValueViewAttributesHolder(path, new Classes().getImplementingClassOf(IValueViewAttributes.class));
		System.out.println(holder);
		fail("Not implemented");
		
	}

}
