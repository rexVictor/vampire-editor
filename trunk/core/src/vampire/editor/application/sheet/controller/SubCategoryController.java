package vampire.editor.application.sheet.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vampire.editor.application.sheet.events.SubCategoryEvent;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryListener;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.fullapi.sheet.ISubCategory;

public class SubCategoryController {
	
	private final ISubCategory subCategory;
	
	private final SubCategoryView view;
	
	private final List<TraitController> traitControllers = new ArrayList<>();
	
	private final List<SubCategoryListener> listeners = new LinkedList<>();
	
	private final Lock lock = new ReentrantLock(true);

	public SubCategoryController(ISubCategory subCategory, SubCategoryView view) {
		super();
		this.subCategory = subCategory;
		this.view = view;
	}
	
	public void addTrait(TraitController traitController){
		//insertTrait(traitControllers.size(), traitController);
		TraitController controller = traitController;
		int index = traitControllers.size();
		SubCategoryEvent event = 
				new SubCategoryEvent(this, 
					controller, index);
		lock.lock();
		try{
		//	subCategory.add(controller.getTrait());
	//		view.add(controller.getTraitView());
			traitControllers.add(index, controller);
			for (SubCategoryListener l : listeners){
				l.traitAdded(event);
			}
		}
		finally{
			lock.unlock();
		}
	}
	
	public void removeTrait(TraitController traitController){
		SubCategoryEvent event = 
				new SubCategoryEvent(this, 
					traitController, traitControllers.indexOf(traitController));
		lock.lock();
		try{
			subCategory.remove(traitController.getTrait());
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
	
	public void insertTrait(int index, TraitController controller){
		SubCategoryEvent event = 
				new SubCategoryEvent(this, 
					controller, index);
		lock.lock();
		try{
			subCategory.insert(index, controller.getTrait());
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
	
	public void addListener(SubCategoryListener listener){
		lock.lock();
		try{
			listeners.add(listener);
		}
		finally{
			lock.unlock();
		}
	}
	
	public void removeListener(SubCategoryListener listener){
		lock.lock();
		try{
			listeners.remove(listener);
		}
		finally{
			lock.unlock();
		}
	}

	public ISubCategory getSubCategory() {
		return subCategory;
	}

	public SubCategoryView getView() {
		return view;
	}
	
	
	
	
	
	

}
