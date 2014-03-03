package vampire.editor.exporter.vmpcs.application;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vampire.editor.fileformat.vmpcs.domain.IntegerWrap;
import vampire.editor.fileformat.vmpcs.domain.ProtoTrait;
import vampire.editor.fileformat.vmpcs.domain.ProtoValue;
import vampire.editor.plugin.api.domain.sheet.Value;

public class ProtoValueBuilder {
	
	public Map<Value, Integer> buildProtoValues(Map<Integer, Object> protoIdMap, IntegerWrap integerWrap){
		IntegerWrap idWrap = new IntegerWrap();
		Map<Value, Integer> valueToId = new HashMap<>();
		List<ProtoTrait> protoTraits = getProtoTraits(protoIdMap);
		for (ProtoTrait protoTrait : protoTraits){
			Value value = protoTrait.toRealModel().getValue();
			Integer id = null;
			if (!valueToId.containsKey(value)){
				id = idWrap.getInt();
				valueToId.put(value, id);
			}
			else{
				id = valueToId.get(value);
			}
			Integer mapid = integerWrap.getInt();
			ProtoValue protoValue = new ProtoValue(mapid, id);
			protoValue.setValue(value);
			protoIdMap.put(mapid, protoValue);
			protoTrait.setValue(protoValue);
		}
		return valueToId;
	}
	
	private List<ProtoTrait> getProtoTraits(Map<Integer, Object> map){
		List<ProtoTrait> traits = new LinkedList<>();
		Set<Integer> keys = map.keySet();
		for (Integer i : keys){
			Object current = map.get(i);
			if (current instanceof ProtoTrait){
				traits.add((ProtoTrait) current);
			}
		}
		return traits;
	}

}
