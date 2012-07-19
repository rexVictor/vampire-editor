package vampire.editor.application.startup;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class ConfigCreatorTest {

	@Test
	public void test() throws IllegalArgumentException, IllegalAccessException {
		Path path = Paths.get("resources", "coreconfig.xml");
		ConfigCreator creator = new ConfigCreator(path);
		@SuppressWarnings("unchecked")
		Class<ConfigCreator> clazz = (Class<ConfigCreator>) creator.getClass();
		List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
		for (Field f : fields){
			f.setAccessible(true);
			System.out.println(f.getName());
			System.out.println(f.get(creator));
		}
		fail("Not yet implemented");
	}

}
