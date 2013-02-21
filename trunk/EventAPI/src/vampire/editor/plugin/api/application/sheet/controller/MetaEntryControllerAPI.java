package vampire.editor.plugin.api.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.events.MetaEntryListener;
import vampire.editor.plugin.api.domain.sheet.MetaEntryAPI;
import vampire.editor.plugin.api.view.sheet.MetaEntryView;

public interface MetaEntryControllerAPI {

	public MetaEntryAPI getMetaEntry();

	public MetaEntryView getMetaEntryView();

	public void addMetaEntryListener(MetaEntryListener listener);

	public void removeMetaEntryListener(MetaEntryListener listener);

}