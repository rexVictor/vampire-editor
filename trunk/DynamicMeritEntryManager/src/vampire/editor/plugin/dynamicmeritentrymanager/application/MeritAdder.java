/*******************************************************************************
 * Vampire Editor Plugin: Dynamic Merit Entry Manager.
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
package vampire.editor.plugin.dynamicmeritentrymanager.application;

import vampire.editor.plugin.api.application.sheet.controller.MeritEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MeritsControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritEntryEventAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritEntryListener;
import vampire.editor.plugin.api.application.sheet.events.MeritsEventAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritsListener;
import vampire.editor.plugin.api.domain.sheet.MeritAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.view.MeritEntryViewAttibutesAPI;

public class MeritAdder implements MeritEntryListener, MeritsListener{
	
	private final MeritsControllerAPI meritsController;
	
	private final ModelToViewModelMapperAPI mapper;

	public MeritAdder(MeritsControllerAPI meritsController, ModelToViewModelMapperAPI mapper) {
		super();
		this.meritsController = meritsController;
		this.mapper = mapper;
		meritsController.addListener(this);
		for (MeritEntryControllerAPI controller : meritsController){
			controller.addListener(this);
		}
	}

	@Override
	public void added(MeritsEventAPI e) {
		e.getReason().addListener(this);
		
	}

	@Override
	public void removed(MeritsEventAPI e) {
		e.getReason().removeListener(this);
	}

	@Override
	public void costChanged(MeritEntryEventAPI e) {/***/}

	@Override
	public void nameChanged(MeritEntryEventAPI e) {
		MeritEntryControllerAPI meritController = e.getSource();
		int lastIndex = meritsController.size()-1;
		MeritEntryControllerAPI first = meritsController.get(0);
		int positionOccured = meritsController.indexOf(meritController);
		if (positionOccured == lastIndex){
			if (!e.getNewName().trim().isEmpty()){
					MeritEntryControllerAPI clone = first.clone();
					clone.setName(""); //$NON-NLS-1$
					clone.setCost(0);
					MeritAPI trait = clone.getModel();
					MeritEntryViewAttibutesAPI traitViewAtts = clone.getView().getViewAttributes();
					mapper.putView(trait, traitViewAtts);
					meritsController.add(clone);
			}
			else if (lastIndex != 0){
					meritsController.remove(meritController);
					MeritAPI merit = meritController.getModel();
					mapper.removeView(merit);
			}
		}
		
	}
	

}
