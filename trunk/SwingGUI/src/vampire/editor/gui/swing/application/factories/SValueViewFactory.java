package vampire.editor.gui.swing.application.factories;

import vampire.editor.gui.swing.view.valueviews.AbstractValueView;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.ValueAPI;
import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;
import vampire.editor.plugin.api.plugin.view.factories.ValueViewFactory;

public class SValueViewFactory implements ValueViewFactory{
	
	public AbstractValueView build(ValueAPI value, ModelToViewModelMapperAPI mapper){
		AbstractValueView view = 
				AbstractValueView.getValueView((
						(ValueViewAttributes) mapper.getViewAttributes(value)));
		view.setValue(value.getValue());
		view.setTempValue(value.getTempValue());
		return view;
	}
	
	

}
