package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Paths;
import java.nio.file.Path;

import org.junit.Test;

import vampire.editor.fileformat.vmpcs.domain.ModelToViewMap;

@SuppressWarnings({"nls", "static-method"})
public class ModelToViewMapImportTest {

	@Test
	public void testModelToViewMapImport() throws Throwable {
		Path path = Paths.get("testcases","modeltoviewmap");
		ModelToViewMap modelToViewMap = ModelImporter.buildProtoIdMap(path);
		assertEquals((Integer) 1, modelToViewMap.getViewAttIdOf(1));
		assertEquals((Integer) 3, modelToViewMap.getViewAttIdOf(15));
		assertEquals((Integer) 3, modelToViewMap.getViewAttIdOf(16));
		assertEquals((Integer) 20, modelToViewMap.getViewAttIdOf(212));
		assertEquals((Integer) 50, modelToViewMap.getViewAttIdOf(500));
		assertEquals((Integer) 20, modelToViewMap.getViewAttIdOf(231));
	}

}
