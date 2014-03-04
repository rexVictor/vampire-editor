package vampire.editor.plugin.freebiecalculator.application.controller;

import java.util.LinkedList;
import java.util.List;

import vampire.editor.application.sheet.controller.ValueController;
import vampire.editor.domain.sheet.MModelConstructors;
import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.api.domain.sheet.ModelConstructors;
import vampire.editor.plugin.api.domain.sheet.Value;
import vampire.editor.plugin.freebiecalculator.tests.mock.TValueView;

public class Helper {
	
	private static final ModelConstructors constructors = new MModelConstructors();
	
	public static List<ValueControllerAPI> buildValueControllers(int... values){
		return buildValueControllers(buildValues(values));
	}
	
	public static List<ValueControllerAPI> buildValueControllers(List<Value> values){
		List<ValueControllerAPI> valueControllers = new LinkedList<>();
		for (Value value : values){
			ValueControllerAPI valueController = new ValueController(value, new TValueView());
			valueControllers.add(valueController);
		}
		return valueControllers;
	}
	
	public static List<Value> buildValues(int... values){
		List<Value> valueList = new LinkedList<>();
		for (int i = 0; i < values.length; i++){
			Value value = constructors.createValue(0, 10);
			value.setValue(values[i]);
			valueList.add(value);
		}
		return valueList;
	}

}
