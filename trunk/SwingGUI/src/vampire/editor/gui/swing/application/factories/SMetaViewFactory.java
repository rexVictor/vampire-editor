package vampire.editor.gui.swing.application.factories;

import vampire.editor.gui.swing.view.SMetaView;
import vampire.editor.plugin.api.domain.sheet.MetaAPI;
import vampire.editor.plugin.api.domain.sheet.MetaEntryAPI;
import vampire.editor.plugin.api.plugin.MetaEntryViewFactory;
import vampire.editor.plugin.api.plugin.MetaViewFactory;
import vampire.editor.plugin.api.plugin.MetaViewFactoryModule;
import vampire.editor.plugin.api.view.sheet.MetaEntryView;
import vampire.editor.plugin.api.view.sheet.MetaView;

public class SMetaViewFactory extends NonLeafAbstractFactory<MetaAPI, MetaEntryAPI, MetaEntryView, MetaView, MetaViewFactoryModule>
	implements MetaViewFactory{
	
	public SMetaViewFactory(MetaEntryViewFactory metaEntryViewFactory) {
		super(metaEntryViewFactory);
	}

	@Override
	protected MetaView constructView(Object viewAtts, MetaAPI m) {
		return new SMetaView();
	}

}
