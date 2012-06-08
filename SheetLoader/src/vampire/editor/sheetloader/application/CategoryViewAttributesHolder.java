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

import vampire.editor.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.ICategoryViewAttributes;

public class CategoryViewAttributesHolder {
	
	private final Map<Integer, CategoryViewAttributes> viewAtts = new HashMap<>();
	
	private final Path path;

	public CategoryViewAttributesHolder(
			Path path) throws JsonParseException, JsonMappingException, IOException {
		super();
		this.path = path;
		load();
	}
	
	private void load() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		Map<?, ?> map = mapper.readValue(path.toFile(), Map.class);
		@SuppressWarnings("unchecked")
		List<Map<String, ?>> viewAtts = (List<Map<String, ?>>) map.get("categoryviewatts");
		for (Map<String, ?> viewAttMap : viewAtts){
			int id = (Integer) viewAttMap.remove("id");
//			int fontID = (Integer) viewAttMap.remove("font");
			CategoryViewAttributes viewAtt = mapper.convertValue(viewAttMap, CategoryViewAttributes.class);
			//viewAtt.setFont(fontHolder.getFontById(fontID));
			this.viewAtts.put(id, viewAtt);
		}
	}
	
	@Override
	public String toString(){
		return viewAtts.toString();
	}
	
	public ICategoryViewAttributes getCategoryViewAttributesByID(int id){
		return viewAtts.get(id).clone();
	}
	
	

}
