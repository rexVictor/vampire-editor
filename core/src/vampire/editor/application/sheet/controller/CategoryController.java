package vampire.editor.application.sheet.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vampire.editor.application.sheet.events.CategoryEvent;
import vampire.editor.domain.sheet.Category;
import vampire.editor.domain.sheet.SubCategory;
import vampire.editor.plugin.api.application.sheet.controller.CategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.CategoryListener;
import vampire.editor.plugin.api.view.sheet.CategoryView;

public class CategoryController implements CategoryControllerAPI {
	
	private final Category subCategory;
	
	private final CategoryView view;
	
	private final List<SubCategoryControllerAPI> traitControllers = new ArrayList<>();
	
	private final List<CategoryListener> listeners = new LinkedList<>();
	
	private final Lock lock = new ReentrantLock(true);

	public CategoryController(Category subCategory, CategoryView view) {
		super();
		this.subCategory = subCategory;
		this.view = view;
	}
	
	@Override
	public void addSubCategory(SubCategoryControllerAPI traitController){
		//insertTrait(traitControllers.size(), traitController);
		SubCategoryControllerAPI controller = traitController;
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
	
	@Override
	public void removeTrait(SubCategoryControllerAPI traitController){
		CategoryEvent event = 
				new CategoryEvent(this, 
					traitController, traitControllers.indexOf(traitController));
		lock.lock();
		try{
			subCategory.remove((SubCategory) traitController.getSubCategory());
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
	
	@Override
	public void insertTrait(int index, SubCategoryControllerAPI controller){
		CategoryEvent event = 
				new CategoryEvent(this, 
					controller, index);
		lock.lock();
		try{
			subCategory.insert(index, (SubCategory) controller.getSubCategory());
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
	
	@Override
	public void addListener(CategoryListener listener){
		lock.lock();
		try{
			listeners.add(listener);
		}
		finally{
			lock.unlock();
		}
	}
	
	@Override
	public void removeListener(CategoryListener listener){
		lock.lock();
		try{
			listeners.remove(listener);
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public Category getSubCategory() {
		return subCategory;
	}

	@Override
	public CategoryView getView() {
		return view;
	}

}
