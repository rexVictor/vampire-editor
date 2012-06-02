package vampire.editor.application.sheet.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vampire.editor.application.sheet.events.CategoryEvent;
import vampire.editor.plugin.api.application.sheet.events.CategoryListener;
import vampire.editor.plugin.api.view.sheet.CategoryView;
import vampire.editor.plugin.fullapi.sheet.ICategory;

public class CategoryController {
	
	private final ICategory subCategory;
	
	private final CategoryView view;
	
	private final List<SubCategoryController> traitControllers = new ArrayList<>();
	
	private final List<CategoryListener> listeners = new LinkedList<>();
	
	private final Lock lock = new ReentrantLock(true);

	public CategoryController(ICategory subCategory, CategoryView view) {
		super();
		this.subCategory = subCategory;
		this.view = view;
	}
	
	public void addSubCategory(SubCategoryController traitController){
		//insertTrait(traitControllers.size(), traitController);
		SubCategoryController controller = traitController;
		int index = traitControllers.size();
		CategoryEvent event =
				new CategoryEvent(this, 
					controller, index);
		lock.lock();
		try{
		//	subCategory.add(controller.getTrait());
	//		view.add(controller.getTraitView());
			traitControllers.add(index, controller);
			for (CategoryListener l : listeners){
				l.subCategoryAdded(event);
			}
		}
		finally{
			lock.unlock();
		}
	}
	
	public void removeTrait(SubCategoryController traitController){
		CategoryEvent event = 
				new CategoryEvent(this, 
					traitController, traitControllers.indexOf(traitController));
		lock.lock();
		try{
			subCategory.remove(traitController.getSubCategory());
		    view.remove(traitController.getView());
			traitControllers.remove(traitController);
			for (CategoryListener l : listeners){
				l.subCategoryRemoved(event);
			}
		}
		finally{
			lock.unlock();
		}
		
		
	}
	
	public void insertTrait(int index, SubCategoryController controller){
		CategoryEvent event = 
				new CategoryEvent(this, 
					controller, index);
		lock.lock();
		try{
			subCategory.insert(index, controller.getSubCategory());
			view.insert(index, controller.getView());
			traitControllers.add(index, controller);
			for (CategoryListener l : listeners){
				l.subCategoryAdded(event);
			}
		}
		finally{
			lock.unlock();
		}
			
	}
	
	public void addListener(CategoryListener listener){
		lock.lock();
		try{
			listeners.add(listener);
		}
		finally{
			lock.unlock();
		}
	}
	
	public void removeListener(CategoryListener listener){
		lock.lock();
		try{
			listeners.remove(listener);
		}
		finally{
			lock.unlock();
		}
	}

	public ICategory getSubCategory() {
		return subCategory;
	}

	public CategoryView getView() {
		return view;
	}

}
