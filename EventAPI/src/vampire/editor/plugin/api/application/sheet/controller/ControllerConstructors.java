package vampire.editor.plugin.api.application.sheet.controller;

import vampire.editor.plugin.api.domain.sheet.*;
import vampire.editor.plugin.api.view.sheet.*;

public interface ControllerConstructors {
	
	public BloodPoolControllerAPI createBloodPoolController(BloodPoolAPI model, BloodPoolView view);
	
	public CategoryControllerAPI createCategoryController(CategoryAPI model, CategoryView view);
	
	public HealthControllerAPI createHealthController(HealthAPI model, HealthView view);
	
	public HealthEntryControllerAPI createHealthEntryControllerAPI(HealthEntryAPI model, HealthEntryView view);
	
	public MeritsControllerAPI createMeritsControllerAPI(MeritsAPI model, MeritView view);
	
	public MetaControllerAPI createMetaControllerAPI(MetaAPI model, MetaView view);
	
	public MetaEntryControllerAPI createMetaEntryControllerAPI(MetaEntryAPI model, MetaEntryView view);
	
	public SheetControllerAPI createSheetControllerAPI(SheetAPI model, SheetView view);
	
	public SubCategoryControllerAPI createSubCategoryControllerAPI(SubCategoryAPI model, SubCategoryView view);
	
	public TraitControllerAPI createTraitControllerAPI(TraitAPI model, TraitView view);
	
	public ValueControllerAPI createValueControllerAPI(ValueAPI model, ValueView view);
	
	public MiscControllerAPI createMiscController(MeritsControllerAPI merits, MeritsControllerAPI flaws,
			HealthControllerAPI health, BloodPoolControllerAPI bloodPool, MiscView view);
}
