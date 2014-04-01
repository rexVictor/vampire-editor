package vampire.editor.exporter.vmpcs.application;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapper;

public class IdCalculator{
	
	@SuppressWarnings("static-method")
	public Map<Object, Integer> generateViewAttsMap(ModelToViewModelMapper mapper){
		Map<Object, Integer> toReturn = new HashMap<>();
		Set<Object> keys = mapper.keySet();
		Integer i = 0;
		for (Object object : keys){
			Object viewAtt = mapper.getViewAttributes(object);
			if (!toReturn.containsKey(viewAtt)){
				toReturn.put(viewAtt, i++);
			}
		}
		return toReturn;
	}

}
