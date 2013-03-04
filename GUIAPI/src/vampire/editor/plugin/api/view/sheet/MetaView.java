package vampire.editor.plugin.api.view.sheet;

import java.util.List;

public interface MetaView extends DataView<MetaEntryView>{
	
	public List<? extends MetaEntryView> getEntries();

}
