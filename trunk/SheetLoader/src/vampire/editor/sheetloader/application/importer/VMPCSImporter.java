package vampire.editor.sheetloader.application.importer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import vampire.editor.domain.sheet.Value;
import vampire.editor.domain.sheet.view.TraitViewAttributes;
import vampire.editor.domain.sheet.view.ValueViewAttributes;
import vampire.editor.plugin.api.plugin.ResourcesHolderAPI;
import vampire.editor.plugin.fullapi.sheet.view.ISubCategoryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.ITraitViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.IValueViewAttributes;

public class VMPCSImporter {
	
	public static final Map<Class<?>, Path> pathes;
	
	static
	{
		pathes = null;
	}

	private final ResourcesHolderAPI resources;

	public VMPCSImporter(ResourcesHolderAPI resources) {
		super();
		this.resources = resources;
	}



	public void load(Path path) throws JsonParseException, JsonMappingException, IOException {
		Class<? extends ValueViewAttributes> valueViewAttsClass = ValueViewAttributes.class;
		Path valueViewAttPath = path.resolve("valueviewatts.json");
		Objects<? extends IValueViewAttributes> valueViewAtts = new Objects<>(valueViewAttPath, valueViewAttsClass, resources);
		
		Class<? extends TraitViewAttributes> traitViewAttsClass = TraitViewAttributes.class;
		Path traitViewAttPath = path.resolve("tratviewatts.json");
		Objects<? extends ITraitViewAttributes> traitViewAtts = new Objects<>(traitViewAttPath, traitViewAttsClass, resources);		
		
		
		Class<? extends Value> valueClass = Value.class;
		Path valuesPath = path.resolve("values.json");
		Objects<? extends Value> values = new Objects<>(valuesPath, valueClass, resources);
		
		
		
	}

}
