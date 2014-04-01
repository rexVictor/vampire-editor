/*******************************************************************************
 * Vampire Editor Plugin: Dynamic SubCategory Trait Manager.
 * Copyright (C) 2013  Matthias Johannes Reimchen
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Further information can be obtained at
 * https://code.google.com/p/vampire-editor/ or via mail:
 * Matthias Johannes Reimchen
 * development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.plugin.dynamicsubcategorymanager.application;

import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryEventAPI;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryListener;
import vampire.editor.plugin.api.application.sheet.events.TraitEventAPI;
import vampire.editor.plugin.api.application.sheet.events.TraitListener;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.api.domain.sheet.ValueAPI;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributesAPI;
import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributesAPI;

public class TraitAdder implements TraitListener, SubCategoryListener{
	
	private final ModelToViewModelMapperAPI mapper;
	
	private final SubCategoryControllerAPI subCategoryControllerAPI;

	public TraitAdder(ModelToViewModelMapperAPI mapper,
			SubCategoryControllerAPI subCategoryControllerAPI) {
		super();
		this.mapper = mapper;
		this.subCategoryControllerAPI = subCategoryControllerAPI;
		subCategoryControllerAPI.addListener(this);
		for (TraitControllerAPI trait : subCategoryControllerAPI){
			trait.addListener(this);
		}
	}

	@Override
	public void traitNameChanged(TraitEventAPI event) {
		TraitControllerAPI traitController = event.getSource();
		int lastIndex = subCategoryControllerAPI.size()-1;
		TraitControllerAPI first = subCategoryControllerAPI.getTraitController(0);
		int positionOccured = subCategoryControllerAPI.indexOf(traitController);
		if (positionOccured == lastIndex){
			if (!event.getNewName().trim().isEmpty()){
					TraitControllerAPI clone = first.clone();
					clone.setTraitName(""); //$NON-NLS-1$
					ValueControllerAPI valueController = clone.getValueController();
					valueController.setTempValue(-1);
					valueController.setValue(0);
					TraitAPI trait = clone.getTrait();
					TraitViewAttributesAPI traitViewAtts = clone.getTraitView().getViewAttributes();
					ValueAPI value = valueController.getValue();
					ValueViewAttributesAPI valueViewAtts = valueController.getView().getViewAttributes();
					mapper.putView(trait, traitViewAtts);
					mapper.putView(value, valueViewAtts);
					subCategoryControllerAPI.addTrait(clone);
			}
			else if (lastIndex != 0){
					subCategoryControllerAPI.removeTrait(traitController);
					TraitAPI trait = traitController.getTrait();
					ValueAPI value = traitController.getValueController().getValue();
					mapper.removeView(trait);
					mapper.removeView(value);
			}
		}
	}

	@Override
	public void traitAdded(SubCategoryEventAPI event) {
		event.getAdded().addListener(this);
	}

	@Override
	public void traitRemoved(SubCategoryEventAPI event) {
		event.getRemoved().removeListener(this);
	}
	
	
	
}
