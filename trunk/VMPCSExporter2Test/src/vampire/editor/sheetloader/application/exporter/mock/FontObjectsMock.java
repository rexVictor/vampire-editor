package vampire.editor.sheetloader.application.exporter.mock;

import java.awt.Font;
import java.nio.file.Path;
import java.nio.file.Paths;

import vampire.editor.importer.vmpcs.application.Objects;


public class FontObjectsMock extends Objects<Font>{
	
	private static final Path fonts = Paths.get("mock", "fonts.json");
	
	public static final Font CASANT$0$20 = new Font("CasablancaAntique", 0, 20);
	
	private static FontObjectsMock FONT_OBJECTS;
	
	private FontObjectsMock() throws Throwable{
		super(Font.class, fonts, new ResourcesHolderMock(), null, null);
	}
	
	public static FontObjectsMock getInstance() throws Throwable{
		if (FONT_OBJECTS == null){
			FONT_OBJECTS = new FontObjectsMock();
		}
		return FONT_OBJECTS;
	}

}
