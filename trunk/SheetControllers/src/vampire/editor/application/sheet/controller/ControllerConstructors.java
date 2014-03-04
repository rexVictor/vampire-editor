/*******************************************************************************
 * Vampire Editor SheetControllers.
 * Copyright (C) 2014  Matthias Johannes Reimchen
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
package vampire.editor.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.controller.*;
import vampire.editor.plugin.api.domain.sheet.*;
import vampire.editor.plugin.api.view.sheet.*;

public class ControllerConstructors implements vampire.editor.plugin.api.application.sheet.controller.ControllerConstructors{

	@Override
	public BloodPoolControllerAPI createBloodPoolController(BloodPoolAPI model,
			BloodPoolView view) {
		return null;
	}

	@Override
	public CategoryControllerAPI createCategoryController(CategoryAPI model,
			CategoryView view) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HealthControllerAPI createHealthController(HealthAPI model,
			HealthView view) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HealthEntryControllerAPI createHealthEntryControllerAPI(
			HealthEntryAPI model, HealthEntryView view) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MeritsControllerAPI createMeritsControllerAPI(MeritsAPI model,
			MeritView view) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetaControllerAPI createMetaControllerAPI(MetaAPI model,
			MetaView view) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetaEntryControllerAPI createMetaEntryControllerAPI(
			MetaEntryAPI model, MetaEntryView view) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SheetControllerAPI createSheetControllerAPI(SheetAPI model,
			SheetView view) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubCategoryControllerAPI createSubCategoryControllerAPI(
			SubCategoryAPI model, SubCategoryView view) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TraitControllerAPI createTraitControllerAPI(TraitAPI model,
			TraitView view) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValueControllerAPI createValueControllerAPI(ValueAPI model,
			ValueView view) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MiscControllerAPI createMiscController(MeritsControllerAPI merits,
			MeritsControllerAPI flaws, HealthControllerAPI health,
			BloodPoolControllerAPI bloodPool, MiscView view) {
		// TODO Auto-generated method stub
		return null;
	}

}
