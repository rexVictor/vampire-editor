package vampire.editor.copyright.persistency;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import vampire.editor.copyright.domain.Project;

public class CopyrightLoaderTest {

	@SuppressWarnings("static-method")
	@Test
	public void test() {
		Path path = Paths.get("copyright.json"); //$NON-NLS-1$
		List<Project> projects = CopyrightLoader.loadCopyright(path);
		System.out.println(projects);
		fail("Not yet implemented"); //$NON-NLS-1$
	}

}
