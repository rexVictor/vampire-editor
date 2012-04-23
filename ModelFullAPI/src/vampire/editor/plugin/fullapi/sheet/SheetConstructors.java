package vampire.editor.plugin.fullapi.sheet;

import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;

public interface SheetConstructors {
	
	public ISheet getNewInstance();
		
	public IValue newValue(ValueViewAttributes atts);
	
	public IValue newValue(int value, ValueViewAttributes atts);
	
	public IValue newValue(int value, int maxValue, ValueViewAttributes atts);
	
	public IValue newValue(int value, int minValue, int maxValue, ValueViewAttributes atts);
	
	public ITrait newTrait(String name, IValue value, TraitViewAttributes atts);
	
	
	

}
