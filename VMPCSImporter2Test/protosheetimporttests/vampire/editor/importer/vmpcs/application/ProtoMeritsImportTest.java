package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vampire.editor.fileformat.vmpcs.domain.ProtoMerit;
import vampire.editor.fileformat.vmpcs.domain.ProtoMerits;
import vampire.editor.fileformat.vmpcs.domain.ProtoSheet;

@SuppressWarnings({"nls"})
public class ProtoMeritsImportTest {
	
	private final List<ProtoMerit> protoMeritEntries = new ArrayList<>();
	
	private final List<ProtoMerit> protoFlawEntries = new ArrayList<>();
	
	private final ProtoMerits protoMerits = new ProtoMerits();
	
	private final ProtoMerits protoFlaws = new ProtoMerits();
	
	@Before
	public void setup(){
		protoMeritEntries.add(new ProtoMerit(400, 3, "merit"));
		protoMeritEntries.add(new ProtoMerit(403, 7, "other merit"));
		protoFlawEntries.add(new ProtoMerit(401, 1, "other flaw"));
		protoFlawEntries.add(new ProtoMerit(404, 2, "flaw"));
		
		protoMerits.setMapid(300);
		protoFlaws.setMapid(301);
	}

	@Test
	public void testMeritEntriesImport() throws Throwable{
		Path path = Paths.get("testcases", "protosheet","protomerits","test1.json");
		ProtoSheet sheet = ModelImporter.loadSheet(path);
		ProtoMerits protoMerits = sheet.getMerits();
		List<ProtoMerit> meritEntries = protoMerits.getEntries();
		assertEquals(protoMeritEntries.size(), meritEntries.size());
		for (int i = 0; i < meritEntries.size(); i++){
			assertEquals(protoMeritEntries.get(i), meritEntries.get(i));
		}
	}
	
	@Test
	public void testFlawEntriesImport() throws Throwable{
		Path path = Paths.get("testcases", "protosheet","protomerits","test1.json");
		ProtoSheet sheet = ModelImporter.loadSheet(path);
		ProtoMerits protoFlaws = sheet.getFlaws();
		List<ProtoMerit> flawEntries = protoFlaws.getEntries();
		assertEquals(protoFlawEntries.size(), flawEntries.size());
		for (int i = 0; i < flawEntries.size(); i++){
			assertEquals(protoFlawEntries.get(i), flawEntries.get(i));
		}
	}
	
	@Test
	public void testFlawsImport() throws Throwable{
		Path path = Paths.get("testcases", "protosheet","protomerits","test1.json");
		ProtoSheet sheet = ModelImporter.loadSheet(path);
		ProtoMerits protoFlaws = sheet.getFlaws();
		assertEquals(this.protoFlaws.getMapid(), protoFlaws.getMapid());
	}
	
	@Test
	public void testMeritsImport() throws Throwable{
		Path path = Paths.get("testcases", "protosheet","protomerits","test1.json");
		ProtoSheet sheet = ModelImporter.loadSheet(path);
		ProtoMerits protoMerits = sheet.getMerits();
		assertEquals(this.protoMerits.getMapid(), protoMerits.getMapid());
	}

}
