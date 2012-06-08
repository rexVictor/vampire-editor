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

import vampire.editor.domain.sheet.view.TraitViewAttributes;

public class TraitViewAttributesHolder {
	
	private final Map<Integer, TraitViewAttributes> traitViewAtts = new HashMap<>();
	
	private final Path path;
	
	private final FontHolder holder;

	public TraitViewAttributesHolder(Path path,
			FontHolder holder) throws JsonParseException, JsonMappingException, IOException {
		super();
		this.path = path;
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
			TraitViewAttributes viewAtt = mapper.convertValue(viewAttMap, TraitViewAttributes.class);
			viewAtt.setFont(holder.getFontById(fontID));
			traitViewAtts.put(id, viewAtt);
		}
	}
	
	@Override
	public String toString(){
		return traitViewAtts.toString();
	}
	
	public TraitViewAttributes getTraitViewAttributesByID(int id){
		return traitViewAtts.get(id).clone();
	}
	
	
	
	
	
	

}
