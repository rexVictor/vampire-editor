package vampire.editor.importer.vcs.persistency;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Loader {
	
	public List<Byte> load(Path path) throws IOException{
		path = path.toRealPath();  //throws IOException
		InputStream reader = Files.newInputStream(path);
		int sign = 0;
		List<Byte> chars = new ArrayList<>(100);
		while ((sign = reader.read()) != -1){
			chars.add((byte) sign);			
		}
		reader.close();
		return chars;
		
	}

}
