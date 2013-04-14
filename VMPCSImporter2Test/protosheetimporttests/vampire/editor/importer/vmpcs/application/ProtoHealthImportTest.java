package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import vampire.editor.fileformat.vmpcs.domain.ProtoHealth;
import vampire.editor.fileformat.vmpcs.domain.ProtoHealthEntry;
import vampire.editor.fileformat.vmpcs.domain.ProtoSheet;
import vampire.editor.plugin.api.domain.sheet.DamageType;

public class ProtoHealthImportTest {
	
	private final ModelImporter modelImporter = new ModelImporter();

	private final List<ProtoHealthEntry> protoHealthEntries = new ArrayList<>();
	
	private final ProtoHealth protoHealth = new ProtoHealth();
	
	@Before
	public void setup(){
		protoHealthEntries.add(new ProtoHealthEntry(600, "bruised", null, 0));
		protoHealthEntries.add(new ProtoHealthEntry(601, "hurt", DamageType.AGGRAVATED, 1));
		protoHealthEntries.add(new ProtoHealthEntry(605, "crippeled", DamageType.LETHAL, 5));
		protoHealthEntries.add(new ProtoHealthEntry(606, "incapacitated", null, 0));
		
		protoHealth.setMapid(500);
		for (ProtoHealthEntry healthEntry : protoHealthEntries){
			protoHealth.addLevel(healthEntry);
		}
	}
	
	
	@Test
	public void testProtoHealthEntryImport() throws Throwable{
		Path path = Paths.get("testcases", "protosheet", "protohealth","test1.json");
		ProtoSheet protoSheet = modelImporter.loadSheet(path);
		ProtoHealth health = protoSheet.getHealth();
		List<ProtoHealthEntry> healthEntries = health.getLevels();
		assertEquals(this.protoHealthEntries.size(), healthEntries.size());
		for(int i = 0; i < healthEntries.size(); i++){
			assertEquals(protoHealthEntries.get(i), healthEntries.get(i));
		}
	}
	
	@Test
	public void testProtoHealthImport() throws Throwable{
		Path path = Paths.get("testcases", "protosheet", "protohealth","test1.json");
		ProtoSheet protoSheet = modelImporter.loadSheet(path);
		ProtoHealth health = protoSheet.getHealth();
		assertEquals((Integer) 500, health.getMapid());
	}

}
