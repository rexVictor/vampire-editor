package vampire.editor.sheetloader.application.exporter;

import java.util.HashMap;
import java.util.Map;

public class IdCalculator<V> {
	
	private final Map<V, Integer> objects = new HashMap<>();
	
	public int getId(V object){
		if (!objects.containsKey(object)){
			objects.put(object, objects.size());
		}
		return objects.get(object);
	}

}
