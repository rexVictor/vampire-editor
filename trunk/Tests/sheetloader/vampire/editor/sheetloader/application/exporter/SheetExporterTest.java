package vampire.editor.sheetloader.application.exporter;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import vampire.editor.plugin.Manager;
import vampire.editor.sheetloader.application.SheetParser;

public class SheetExporterTest {

	@Test
	public void test() throws Throwable {
		SheetExporter exporter = new SheetExporter();
		Path path = Paths.get("exporttest", "output.json");
		Path importpath = Paths.get("sheetpersistencyprototype");
		SheetParser parser = new SheetParser(importpath, new Manager());
		exporter.export(parser.getSheet(), path);
	}

}
