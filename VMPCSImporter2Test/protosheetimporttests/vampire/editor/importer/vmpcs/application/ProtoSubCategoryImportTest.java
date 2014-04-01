package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vampire.editor.fileformat.vmpcs.domain.ProtoSheet;
import vampire.editor.fileformat.vmpcs.domain.ProtoSubCategory;

@SuppressWarnings({"nls"})
public class ProtoSubCategoryImportTest {
	
	private List<ProtoSubCategory> actual;
	
	private List<ProtoSubCategory> expected = new ArrayList<>();
	
	@Before
	public void setup() throws Throwable{
		Path path = Paths.get("testcases", "protosheet", "protosubcats", "test1.json");
		ProtoSheet protoSheet = ModelImporter.loadSheet(path);
		actual = protoSheet.getTraits().get(0).getSubCats();
		
		expected.add(new ProtoSubCategory(15, "physical"));
		expected.add(new ProtoSubCategory(16, "social"));
		expected.add(new ProtoSubCategory(17, "mental"));
		
	}

	@Test
	public void testProtoSubCategoryImport() {
		assertEquals(expected.size(), actual.size());
		for (int i = 0; i < expected.size(); i++){
			assertEquals(expected.get(i), actual.get(i));
		}
	}

}
