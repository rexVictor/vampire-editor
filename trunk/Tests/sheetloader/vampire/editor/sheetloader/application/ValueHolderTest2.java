package vampire.editor.sheetloader.application;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.crypto.spec.OAEPParameterSpec;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import vampire.editor.domain.sheet.Value;

public class ValueHolderTest2 {

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		Path path = Paths.get("importtest", "testcase1", "values.json");
		ValueHolder holder = new ValueHolder(path);
		System.out.println(holder.toString());
	}

}
