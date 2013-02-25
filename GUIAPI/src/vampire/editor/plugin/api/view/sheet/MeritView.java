package vampire.editor.plugin.api.view.sheet;

import java.util.List;

public interface MeritView {
	
	public void addMeritEntryView(MeritEntryView view);
	
	public List<MeritEntryView> getEntries();

}
