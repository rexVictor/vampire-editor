package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import vampire.editor.fileformat.vmpcs.domain.ProtoSheet;

@SuppressWarnings({"nls"})
public class ProtoSheetImportTest {
	
	private ProtoSheet protoSheet;
	
	@Before
	public void setup() throws Throwable{
		Path path = Paths.get("testcases", "protosheet", "protosheet", "test1.json");
		protoSheet = ModelImporter.loadSheet(path);
	}
	
	@Test
	public void testProtoSheetImport(){
		assertEquals("vtmdefault", protoSheet.getBorder());
	}


}
