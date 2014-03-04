package vampire.editor.plugin.freebiecalculator.application;

import java.util.LinkedList;
import java.util.List;

import vampire.editor.plugin.api.application.sheet.controller.CategoriesControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.CategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MeritsControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritEntryControllerAPI;
import vampire.editor.plugin.freebiecalculator.application.controllers.FCatController;
import vampire.editor.plugin.freebiecalculator.application.controllers.FDependentController;
import vampire.editor.plugin.freebiecalculator.application.controllers.FMeritController;
import vampire.editor.plugin.freebiecalculator.application.controllers.FSheetController;
import vampire.editor.plugin.freebiecalculator.application.controllers.FSingleSubCatController;
import vampire.editor.plugin.freebiecalculator.application.controllers.FSubCatController;
import vampire.editor.plugin.freebiecalculator.domain.DependentTraitConfig;
import vampire.editor.plugin.freebiecalculator.domain.FreebieCategoryConfig;
import vampire.editor.plugin.freebiecalculator.domain.FreebieConfig;
import vampire.editor.plugin.freebiecalculator.domain.FreebieSubCategoryConfig;

public class ControllerFactory {
	
	private List<ValueControllerAPI> getValuesIn(SubCategoryControllerAPI subCategoryController){
		List<ValueControllerAPI> valueControllers = new LinkedList<>();
		for (TraitControllerAPI traitController : subCategoryController){
			ValueControllerAPI valueController = traitController.getValueController();
			valueControllers.add(valueController);
		}
		return valueControllers;
	}
	
	public FSubCatController buildSubCatController(SubCategoryControllerAPI subCategory){
		FSubCatController controller = new FSubCatController(getValuesIn(subCategory));
		return controller;
	}
	
	public FCatController buildCatController(CategoryControllerAPI controller, FreebieCategoryConfig config){
		List<FSubCatController> freebieSubCatControllers = new LinkedList<>();
		for (SubCategoryControllerAPI subCategoryController : controller){
			freebieSubCatControllers.add(buildSubCatController(subCategoryController));
		}
		FCatController freebieController = new FCatController(freebieSubCatControllers, config);
		return freebieController;
	}
	
	public FSingleSubCatController buidlSingleSubCatController(SubCategoryControllerAPI subCatController,
			FreebieSubCategoryConfig config){
		FSingleSubCatController controller = new FSingleSubCatController(getValuesIn(subCatController), config);
		subCatController.addListener(controller);
		return controller;
	}
	
	public FDependentController buildPathController(CategoryControllerAPI virtues, DependentTraitConfig config){
		SubCategoryControllerAPI path = virtues.get(0);
		SubCategoryControllerAPI realVirtues = virtues.get(2);
		TraitControllerAPI conscience = realVirtues.getTraitController(0);
		TraitControllerAPI selfControl = realVirtues.getTraitController(1);
		FDependentController controller = 
				new FDependentController(config, path.getTraitController(0).getValueController(),
						conscience.getValueController(), selfControl.getValueController());
		return controller;
	}
	
	public FDependentController buildWillpowerController(CategoryControllerAPI virtues, DependentTraitConfig config){
		SubCategoryControllerAPI willpower = virtues.get(1);
		SubCategoryControllerAPI realVirtues = virtues.get(2);
		TraitControllerAPI courage = realVirtues.getTraitController(2);
		FDependentController controller = 
				new FDependentController(config, willpower.getTraitController(0).getValueController(),
						courage.getValueController());
		return controller;
	}
	
	public FMeritController buildMeritController(MeritsControllerAPI meritsController){
		List<MeritEntryControllerAPI> entryControllers = new LinkedList<>();
		for (MeritEntryControllerAPI meritController : meritsController){
			entryControllers.add(meritController);
		}
		FMeritController meritController = new FMeritController(entryControllers);
		return meritController;
	}
	
	public FSheetController buildSheetController(SheetControllerAPI sheet, FreebieConfig config){
		CategoriesControllerAPI categories = sheet.getCategoriesController();
		CategoryControllerAPI attributes = categories.get(0);
		CategoryControllerAPI abilities = categories.get(1);
		CategoryControllerAPI virtues = categories.get(2);
		CategoryControllerAPI advantages = categories.get(3);
		
		SubCategoryControllerAPI disciplines = advantages.get(0);
		SubCategoryControllerAPI backgrounds = advantages.get(1);
		SubCategoryControllerAPI realVirtues = virtues.get(2);
		
		MeritsControllerAPI merits = sheet.getMiscController().getMerits();
		MeritsControllerAPI flaws = sheet.getMiscController().getFlaws();
		
		FCatController attController = buildCatController(attributes, config.getAttributes());
		FCatController abiController = buildCatController(abilities, config.getAbilities());
		
		FDependentController willpowerController = buildWillpowerController(virtues, config.getWillpower());
		FDependentController pathController = buildPathController(virtues, config.getPath());
		
		FSingleSubCatController discController = buidlSingleSubCatController(disciplines, config.getDisciplines());
		FSingleSubCatController backController = buidlSingleSubCatController(backgrounds, config.getBackgrounds());
		FSingleSubCatController realVirtuesController = buidlSingleSubCatController(realVirtues, config.getVirtues());
		
		FMeritController meritController = buildMeritController(merits);
		FMeritController flawController = buildMeritController(flaws);
		
		FSheetController sheetController = new FSheetController(attController, abiController, discController,
				backController, realVirtuesController, pathController, willpowerController,
				meritController, flawController, config);
		return sheetController;
	}
	
	

}
