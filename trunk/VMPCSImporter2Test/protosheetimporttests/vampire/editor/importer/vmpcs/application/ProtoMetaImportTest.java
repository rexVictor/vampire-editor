package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vampire.editor.fileformat.vmpcs.domain.ProtoMetaEntry;
import vampire.editor.fileformat.vmpcs.domain.ProtoSheet;


public class ProtoMetaImportTest {
	
	private final ModelImporter modelImporter = new ModelImporter();
	
	private List<ProtoMetaEntry> expected = new ArrayList<>();
	
	@Before
	public void setup(){
		expected.add(new ProtoMetaEntry(0, "name", "test"));
		expected.add(new ProtoMetaEntry(3, "chronicle", "At Night"));
		expected.add(new ProtoMetaEntry(5, "demeanor", "rebel"));
		expected.add(new ProtoMetaEntry(8, "haven", "home"));
	}
	
	@Test
	public void testProtoMetaImport() throws Throwable{
		Path path = Paths.get("testcases", "protosheet","protometa","test1.json");
		ProtoSheet protoSheet = modelImporter.loadSheet(path);
		List<ProtoMetaEntry> protoMetaEntries = new ArrayList<>(protoSheet.getMeta());
		assertEquals(expected.size(), protoMetaEntries.size());
		for (int i = 0; i < protoMetaEntries.size(); i++){
			ProtoMetaEntry metaEntry1 = expected.get(i);
			ProtoMetaEntry metaEntry2 = protoMetaEntries.get(i);
			assertEquals(metaEntry1, metaEntry2);
		}
	}
	
}
