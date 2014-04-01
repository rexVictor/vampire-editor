package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vampire.editor.fileformat.vmpcs.domain.ProtoSheet;
import vampire.editor.fileformat.vmpcs.domain.ProtoTrait;
import vampire.editor.fileformat.vmpcs.domain.ProtoValue;

@SuppressWarnings({"nls"})
public class ProtoValueImportTest {
	
	private List<ProtoTrait> protoTraits;
	
	@Before
	public void setup() throws Throwable{
		Path path = Paths.get("testcases", "protosheet", "protovalues", "test1.json");
		ProtoSheet protoSheet = ModelImporter.loadSheet(path);
		protoTraits = protoSheet.getTraits().get(0).getSubCats().get(0).getTraits();
	}
	
	@Test
	public void testProtoValueImport1(){
		ProtoValue expected = new ProtoValue(200,30);
		assertEquals(expected, protoTraits.get(0).getValue());
	}
	
	@Test
	public void testProtoValueImport2(){
		ProtoValue expected = new ProtoValue(201,30);
		assertEquals(expected, protoTraits.get(1).getValue());
	}

}
