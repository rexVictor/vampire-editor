package vampire.editor.exporter.vmpcs.application;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import vampire.editor.fileformat.vmpcs.domain.ProtoValue;
import vampire.editor.fileformat.vmpcs.domain.ToRealModelTransformable;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapper;

public class ModelToViewMapGenerator {
	
	public void generateModelToViewMap(Map<Integer, Object> mapidMap, Map<Object, Integer> viewAttIdMap,
			ModelToViewModelMapper mapper, Path path) throws IOException{
		Properties properties = new Properties();
		Set<Integer> mapIds = mapidMap.keySet();
		for (Integer mapid : mapIds){
			Object protoModel = mapidMap.get(mapid);
			Object model = null;
			if (protoModel instanceof ProtoValue){
				model = ((ProtoValue) protoModel).getValue();
			}
			else if (protoModel instanceof ToRealModelTransformable<?>){
				model = ((ToRealModelTransformable<?>) protoModel).toRealModel();
			}
			Object viewAtt = mapper.getViewAttributes(model);
			Integer id = viewAttIdMap.get(viewAtt);
			properties.setProperty(mapid+"", id+"");
		}
		Path target = path.resolve("modeltoviewmap.properties");
		System.out.println(target.toAbsolutePath().toUri());
		OutputStream stream = Files.newOutputStream(target);
		properties.store(stream, null);
		stream.close();
	}

}
