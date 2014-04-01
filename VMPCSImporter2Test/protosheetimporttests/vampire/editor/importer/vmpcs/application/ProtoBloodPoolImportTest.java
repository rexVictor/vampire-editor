package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import vampire.editor.fileformat.vmpcs.domain.ProtoBloodPool;
import vampire.editor.fileformat.vmpcs.domain.ProtoSheet;

@SuppressWarnings("nls")
public class ProtoBloodPoolImportTest {
	
	private final ProtoBloodPool protoBloodPool = new ProtoBloodPool(700, 0, 20);

	@Test
	public void testBloodPoolImport() throws Throwable{
		Path path = Paths.get("testcases", "protosheet", "protobloodpool", "test1.json");
		ProtoSheet protoSheet = ModelImporter.loadSheet(path);
		ProtoBloodPool bloodPool = protoSheet.getBloodpool();
		assertEquals(protoBloodPool, bloodPool);
	}

}
