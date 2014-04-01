package vampire.editor.gui.swing.mainframe.persistence;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import vampire.editor.gui.swing.mainframe.domain.ProtoMenuEntry;

public class ProtoMenuEntryLoaderTest {

	@SuppressWarnings("static-method")
	@Test
	public void test() throws Throwable {
		Path path = Paths.get("menubar.json"); //$NON-NLS-1$
		List<ProtoMenuEntry> entries = ProtoMenuEntryLoader.load(path);
		System.out.println(entries);
		for (ProtoMenuEntry entry : entries){
			System.out.println(entry.toStringArray());
		}
		
		fail("Not yet implemented"); //$NON-NLS-1$
	}

}
