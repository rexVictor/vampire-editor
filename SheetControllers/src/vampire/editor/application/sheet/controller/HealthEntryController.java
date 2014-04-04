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

import vampire.editor.application.sheet.events.HealthEntryEvent;
import vampire.editor.plugin.api.application.sheet.controller.HealthEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.HealthEntryListener;
import vampire.editor.plugin.api.domain.sheet.DamageType;
import vampire.editor.plugin.api.domain.sheet.HealthEntry;
import vampire.editor.plugin.api.view.events.HealthEntryViewListener;
import vampire.editor.plugin.api.view.events.HealthViewEntryEvent;
import vampire.editor.plugin.api.view.sheet.HealthEntryView;

public class HealthEntryController extends AbstractController<HealthEntry, HealthEntryView, HealthEntryListener>
									implements HealthEntryControllerAPI, HealthEntryViewListener{
	
	public HealthEntryController(HealthEntry healthEntry, HealthEntryView view) {
		super(healthEntry, view);
		view.addListener(this);
	}

	@Override
	public void setDamageType(DamageType damageType) {
		int formerPenalty = model.getPenalty();
		DamageType formerDamageType = model.getDamageType();
		String formerText = model.getName();
		HealthEntryEvent e = new HealthEntryEvent(this, formerPenalty, formerPenalty,
				formerDamageType, damageType, formerText, formerText);
		lock.lock();
		try{
			model.setDamageType(damageType);
			view.setDamageType(damageType);
			for (HealthEntryListener l : listeners){
				l.damageTypeChanged(e);
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void setPenalty(int penalty) {
		int formerPenalty = model.getPenalty();
		DamageType formerDamageType = model.getDamageType();
		String formerText = model.getName();
		HealthEntryEvent e = new HealthEntryEvent(this, formerPenalty, penalty,
				formerDamageType, formerDamageType, formerText, formerText);
		lock.lock();
		try{
			model.setPenalty(penalty);
			view.setPenalty(penalty);
			for (HealthEntryListener l : listeners){
				l.penaltyChanged(e);
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void setText(String text) {
		int formerPenalty = model.getPenalty();
		DamageType formerDamageType = model.getDamageType();
		String formerText = model.getName();
		HealthEntryEvent e = new HealthEntryEvent(this, formerPenalty, formerPenalty,
				formerDamageType, formerDamageType, formerText, text);
		lock.lock();
		try{
			model.setName(text);
			view.setDescription(text);
			for (HealthEntryListener l : listeners){
				l.textChanged(e);
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void damageTypeChanged(HealthViewEntryEvent event) {
		setDamageType(event.getDamageType());
	}

	@Override
	public void penaltyChanged(HealthViewEntryEvent event) {
		setPenalty(event.getPenalty());
	}

	@Override
	public void descriptionChanged(HealthViewEntryEvent event) {
		setText(event.getDescription());
	}

}
