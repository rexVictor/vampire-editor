package vampire.editor.importer.vcs.application;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import vampire.editor.domain.sheet.MetaEntry;
import vampire.editor.domain.sheet.Sheet;
import vampire.editor.importer.vcs.persistency.Loader;
import vampire.editor.plugin.fullapi.sheet.IData;
import vampire.editor.plugin.fullapi.sheet.IMetaEntry;

public class ParserTest {

	@Test
	public void testParse() throws IOException {
		Path path = Paths.get("test.vcs");
		Loader loader = new Loader();
		List<Byte> list = loader.load(path);
		Sheet sheet = new Sheet();
		IData<IMetaEntry> meta = sheet.getMeta();
		IMetaEntry metaEntry = null;
		
		(metaEntry = new MetaEntry()).setName("name");
		
		meta.add(metaEntry);
		(metaEntry = new MetaEntry()).setName("player");
		meta.add(metaEntry);
		(metaEntry = new MetaEntry()).setName("creator");
		meta.add(metaEntry);
		
		(metaEntry = new MetaEntry()).setName("haven");
		meta.add(metaEntry);
		(metaEntry = new MetaEntry()).setName("clan");
		meta.add(metaEntry);
		(metaEntry = new MetaEntry()).setName("sect");
		meta.add(metaEntry);
		
		(metaEntry = new MetaEntry()).setName("generation");
		meta.add(metaEntry);
		(metaEntry = new MetaEntry()).setName("demeanor");
		meta.add(metaEntry);
		(metaEntry = new MetaEntry()).setName("nature");
		meta.add(metaEntry);
		
		(metaEntry = new MetaEntry()).setName("concept");
		meta.add(metaEntry);
		(metaEntry = new MetaEntry()).setName("chronicle");
		meta.add(metaEntry);
		
		System.out.println(meta);
		
		Parser parser = new Parser(list, sheet, null);
		parser.parse();
		
		System.out.println(meta);
	}

}
