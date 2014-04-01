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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vampire.editor.application.sheet.events.SubCategoryEvent;
import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryListener;
import vampire.editor.plugin.api.domain.sheet.SubCategory;
import vampire.editor.plugin.api.domain.sheet.Trait;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;

public class SubCategoryController implements SubCategoryControllerAPI {
	
	private final SubCategory subCategory;
	
	private final SubCategoryView view;
	
	private final List<TraitControllerAPI> traitControllers = new ArrayList<>();
	
	private final List<SubCategoryListener> listeners = new LinkedList<>();
	
	private final Lock lock = new ReentrantLock(true);

	public SubCategoryController(SubCategory subCategory, SubCategoryView view) {
		super();
		this.subCategory = subCategory;
		this.view = view;
	}
	
	@Override
	public void addTrait(TraitControllerAPI traitController){
		TraitControllerAPI controller = traitController;
		int index = traitControllers.size();
		SubCategoryEvent event = 
				new SubCategoryEvent(this, 
					controller, null, index);
		lock.lock();
		try{
			subCategory.add((Trait) controller.getTrait());
			view.add(controller.getTraitView());
			traitControllers.add(index, controller);
			for (SubCategoryListener l : listeners){
				l.traitAdded(event);
			}
		}
		finally{
			lock.unlock();
		}
	}
	
	void addTrait0(TraitControllerAPI traitController){
		TraitControllerAPI controller = traitController;
		int index = traitControllers.size();
		SubCategoryEvent event = 
				new SubCategoryEvent(this, 
					controller, null, index);
		lock.lock();
		try{
			traitControllers.add(index, controller);
			for (SubCategoryListener l : listeners){
				l.traitAdded(event);
			}
		}
		finally{
			lock.unlock();
		}
	}
	
	@Override
	public void removeTrait(TraitControllerAPI traitController){
		SubCategoryEvent event = 
				new SubCategoryEvent(this, 
					null, traitController, traitControllers.indexOf(traitController));
		lock.lock();
		try{
			subCategory.remove((Trait) traitController.getTrait());
		    view.remove(traitController.getTraitView());
			traitControllers.remove(traitController);
			for (SubCategoryListener l : listeners){
				l.traitRemoved(event);
			}
		}
		finally{
			lock.unlock();
		}
		
		
	}
	
	@Override
	public void insertTrait(int index, TraitControllerAPI controller){
		SubCategoryEvent event = 
				new SubCategoryEvent(this, 
					controller, null, index);
		lock.lock();
		try{
			subCategory.insert(index, (Trait) controller.getTrait());
			view.insert(index, controller.getTraitView());
			traitControllers.add(index, controller);
			for (SubCategoryListener l : listeners){
				l.traitAdded(event);
			}
		}
		finally{
			lock.unlock();
		}
			
	}
	
	@Override
	public void addListener(SubCategoryListener listener){
		lock.lock();
		try{
			listeners.add(listener);
		}
		finally{
			lock.unlock();
		}
	}
	
	@Override
	public void removeListener(SubCategoryListener listener){
		lock.lock();
		try{
			listeners.remove(listener);
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public SubCategory getSubCategory() {
		return subCategory;
	}

	@Override
	public SubCategoryView getView() {
		return view;
	}

	@Override
	public Iterator<TraitControllerAPI> iterator() {
		return traitControllers.iterator();
	}

	@Override
	public int indexOf(TraitControllerAPI traitController) {
		return traitControllers.indexOf(traitController);
	}
	
	@Override
	public int size(){
		return traitControllers.size();
	}
	
	@Override
	public TraitControllerAPI getTraitController(int i){
		return traitControllers.get(i);
	}
	
	
	
	
	
	

}
