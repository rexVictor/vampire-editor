package vampire.editor.sheetloader.application.exporter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import vampire.editor.domain.sheet.view.TraitViewAttributes;

public class IdCalculator<V> {
	
	private final Map<V, Integer> objects = new HashMap<>();
	
	public int getId(V object){
		if (object.getClass() == TraitViewAttributes.class){
			System.out.println();
		}
		if (!objects.containsKey(object)){
			objects.put(object, objects.size());
		}
		return objects.get(object);
	}
	
	public Set<V> getRegisteredObjects(){
		return objects.keySet();
	}
	
}
