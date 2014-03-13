package vampire.editor.copyright.persistency;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import vampire.editor.copyright.domain.Project;

public class CopyrightLoaderTest {

	@Test
	public void test() {
		Path path = Paths.get("copyright.json");
		CopyrightLoader loader = new CopyrightLoader();
		List<Project> projects = loader.loadCopyright(path);
		System.out.println(projects);
		fail("Not yet implemented");
	}

}
