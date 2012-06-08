package vampire.editor.importer.vcs.application;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import vampire.editor.importer.vcs.persistency.Loader;

public class Main {
	
	public static void main(String[] args) throws IOException{
		Loader loader = new Loader();
		Path path = Paths.get("test.vcs");
		List<Byte> chars = loader.load(path);
		Parser parser = new Parser(chars, null);
		parser.parse();
	}

}
