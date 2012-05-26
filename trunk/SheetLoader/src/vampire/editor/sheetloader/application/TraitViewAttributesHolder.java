package vampire.editor.sheetloader.application;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.editor.plugin.fullapi.sheet.view.ITraitViewAttributes;

public class TraitViewAttributesHolder {
	
	private final Map<Integer, ITraitViewAttributes> traitViewAtts = new HashMap<>();
	
	private final Path path;
	
	private final Class<? extends ITraitViewAttributes> clazz;
	
	private final FontHolder holder;

	public TraitViewAttributesHolder(Path path,
			Class<? extends ITraitViewAttributes> clazz,
			FontHolder holder) throws JsonParseException, JsonMappingException, IOException {
		super();
		this.path = path;
		this.clazz = clazz;
		this.holder = holder;
		load();
	}
	
	private void load() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		Map<?, ?> map = mapper.readValue(path.toFile(), Map.class);
		@SuppressWarnings("unchecked")
		List<Map<String, ?>> viewAtts = (List<Map<String, ?>>) map.get("traitviewatts");
		for (Map<String, ?> viewAttMap : viewAtts){
			int id = (Integer) viewAttMap.remove("id");
			int fontID = (Integer) viewAttMap.remove("font");
			ITraitViewAttributes viewAtt = mapper.convertValue(viewAttMap, clazz);
			viewAtt.setFont(holder.getFontById(fontID));
			traitViewAtts.put(id, viewAtt);
		}
	}
	
	@Override
	public String toString(){
		return traitViewAtts.toString();
	}
	
	public ITraitViewAttributes getTraitViewAttributesByID(int id){
		return traitViewAtts.get(id).clone();
	}
	
	
	
	
	
	

}
