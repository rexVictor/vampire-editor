package vampire.editor.copyright.application;

import java.nio.file.Path;
import java.util.List;

import vampire.editor.copyright.domain.Project;
import vampire.editor.copyright.persistency.CopyrightLoader;
import vampire.editor.copyright.view.CopyrightView;

public class CopyrightViewFactory {
	
	public static CopyrightView buildView(Path path){
		CopyrightLoader loader = new CopyrightLoader();
		List<Project> projects = loader.loadCopyright(path);
		CopyrightView view = new CopyrightView(projects);
		return view;
	}

}
