package vampire.editor.domain.sheet;

import java.nio.file.Path;

import vampire.editor.plugin.api.domain.sheet.BloodPool;
import vampire.editor.plugin.api.domain.sheet.Categories;
import vampire.editor.plugin.api.domain.sheet.Category;
import vampire.editor.plugin.api.domain.sheet.Health;
import vampire.editor.plugin.api.domain.sheet.HealthEntry;
import vampire.editor.plugin.api.domain.sheet.Merit;
import vampire.editor.plugin.api.domain.sheet.Merits;
import vampire.editor.plugin.api.domain.sheet.Meta;
import vampire.editor.plugin.api.domain.sheet.MetaEntry;
import vampire.editor.plugin.api.domain.sheet.ModelConstructors;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapper;
import vampire.editor.plugin.api.domain.sheet.Sheet;
import vampire.editor.plugin.api.domain.sheet.SubCategory;
import vampire.editor.plugin.api.domain.sheet.Trait;
import vampire.editor.plugin.api.domain.sheet.Value;
import vampire.editor.plugin.api.domain.sheet.VampireDocument;

class MModelConstructors implements ModelConstructors{

	@Override
	public Value createValue(Integer minValue, Integer maxValue) {
		return new MValue(minValue, minValue, maxValue);
	}

	@Override
	public Trait createTrait() {
		return new MTrait();
	}

	@Override
	public SubCategory createSubCategory() {
		return new MSubCategory();
	}

	@Override
	public Category createCategory() {
		return new MCategory();
	}

	@Override
	public HealthEntry createHealthEntry() {
		return new MHealthEntry();
	}

	@Override
	public Health createHealth() {
		return new MHealth();
	}

	@Override
	public Merit createMerit() {
		return new MMerit();
	}

	@Override
	public Merits createMerits() {
		return new MMerits();
	}

	@Override
	public BloodPool createBloodPool() {
		return new MBloodPool();
	}

	@Override
	public ModelToViewModelMapper createModelToViewModelMapper() {
		return new MModelToViewModelMapper();
	}

	@Override
	public MetaEntry createMetaEntry() {
		return new MMetaEntry();
	}

	@Override
	public Meta createMeta() {
		return new MMeta();
	}

	@Override
	public Sheet createSheet() {
		return new MSheet();
	}

	@Override
	public Categories createCategories() {
		return new MCategories();
	}

	@Override
	public VampireDocument createVampireDocument(Sheet sheet,
			ModelToViewModelMapper mapper, Path pluginPath) {
		return new MVampireDocument(sheet, mapper, pluginPath);
	}

}
