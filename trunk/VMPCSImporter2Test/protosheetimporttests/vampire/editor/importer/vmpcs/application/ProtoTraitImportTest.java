package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vampire.editor.fileformat.vmpcs.domain.ProtoSheet;
import vampire.editor.fileformat.vmpcs.domain.ProtoTrait;

@SuppressWarnings({"nls"})
public class ProtoTraitImportTest {

	private List<ProtoTrait> protoTraits;
	
	@Before
	public void setup() throws Throwable{
		Path path = Paths.get("testcases", "protosheet", "prototraits", "test1.json");
		ProtoSheet protoSheet = ModelImporter.loadSheet(path);
		protoTraits = protoSheet.getTraits().get(0).getSubCats().get(0).getTraits();
	}
	
	@Test
	public void testProtoTraitImport1(){
		ProtoTrait expected = new ProtoTrait(100, "strength");
		assertEquals(expected, protoTraits.get(0));
	}
	
	@Test
	public void testProtoTraitImport2(){
		ProtoTrait expected = new ProtoTrait(101,"stamina");
		assertEquals(expected, protoTraits.get(1));
	}

}
