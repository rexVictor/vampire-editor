package vampire.editor.plugin.api.domain.sheet;

import java.nio.file.Path;

public interface ModelConstructors {
	
	public Value createValue(Integer minValue, Integer maxValue);
	
	public Trait createTrait();
	
	public SubCategory createSubCategory();
	
	public Category createCategory();
	
	public HealthEntry createHealthEntry();
	
	public Health createHealth();
	
	public Merit createMerit();
	
	public Merits createMerits();
	
	public BloodPool createBloodPool();
	
	public ModelToViewModelMapper createModelToViewModelMapper();
	
	public MetaEntry createMetaEntry();
	
	public Meta createMeta();
	
	public Sheet createSheet();
	
	public Categories createCategories();
	
	public VampireDocument createVampireDocument(Sheet sheet, ModelToViewModelMapper mapper, Path pluginPath);
	

}
