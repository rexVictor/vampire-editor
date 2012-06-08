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

import vampire.editor.domain.sheet.view.MetaEntryViewAttributes;

public class MetaViewEntryAttributesHolder {
	
	private final Map<Integer, MetaEntryViewAttributes> viewAtts = new HashMap<>();
	
	private final Path path;
	
	private final FontHolder fontHolder;

	public MetaViewEntryAttributesHolder(Path path,
			FontHolder fontHolder) throws JsonParseException, JsonMappingException, IOException {
		super();
		this.path = path;
		this.fontHolder = fontHolder;
		load();
	}
	
	private void load() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		Map<?, ?> map = mapper.readValue(path.toFile(), Map.class);
		@SuppressWarnings("unchecked")
		List<Map<String, ?>> viewAtts = (List<Map<String, ?>>) map.get("metaentryviewatts");
		for (Map<String, ?> viewAttMap : viewAtts){
			int id = (Integer) viewAttMap.remove("id");
			int titleFontID = (Integer) viewAttMap.remove("titleFont");
			int contentFontID = (Integer) viewAttMap.remove("contentFont");
			System.out.println(viewAttMap);
			MetaEntryViewAttributes viewAtt = mapper.convertValue(viewAttMap, MetaEntryViewAttributes.class);
			viewAtt.setTitleFont(fontHolder.getFontById(titleFontID));
			viewAtt.setContentFont(fontHolder.getFontById(contentFontID));
			this.viewAtts.put(id, viewAtt);
		}
		
	}
	
	@Override
	public String toString(){
		return viewAtts.toString();
	}
	
	public MetaEntryViewAttributes getMetaEntryViewAttributesByID(int id){
		return viewAtts.get(id).clone();
	}
	
	

}
