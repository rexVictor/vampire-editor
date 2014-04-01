package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vampire.editor.fileformat.vmpcs.domain.ProtoCategory;
import vampire.editor.fileformat.vmpcs.domain.ProtoSheet;

@SuppressWarnings("nls")
public class ProtoCategoryImportTest {

	private List<ProtoCategory> actual;
	
	private List<ProtoCategory> expected = new ArrayList<>();
	
	@Before
	public void setup() throws Throwable{
		Path path = Paths.get("testcases", "protosheet", "protocats", "test1.json");
		ProtoSheet protoSheet = ModelImporter.loadSheet(path);
		actual = protoSheet.getTraits();
		
		expected.add(new ProtoCategory(11, "attributes"));
		expected.add(new ProtoCategory(12, "abilities"));
		expected.add(new ProtoCategory(13, "virtues"));
		
	}

	@Test
	public void testProtoCategoryImport() {
		assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++){
			assertEquals(expected.get(i), actual.get(i));
		}
	}

}
