package vampire.editor.application.sheet.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vampire.editor.application.sheet.events.CategoryEvent;
import vampire.editor.domain.sheet.Category;
import vampire.editor.plugin.api.application.sheet.events.CategoryListener;
import vampire.editor.plugin.api.view.sheet.CategoryView;
import vampire.editor.plugin.fullapi.sheet.ICategory;

public class CategoryController {
	
	private final Category category;
	
	private final CategoryView view;
	
	private final List<SubCategoryController> controllers 
					= new ArrayList<>();
					
	private final List<CategoryListener> listeners = new LinkedList<>();
						
	private final Lock lock = new ReentrantLock(true);

	public CategoryController(Category category, CategoryView view) {
		super();
		this.category = category;
		this.view = view;
	}
	
	public void add(SubCategoryController controller){
		insert(controllers.size(), controller);
	}
	
	public void remove(SubCategoryController controller){
		CategoryEvent event = new CategoryEvent(this, controller, controllers.indexOf(controller));
		lock.lock();
		try{
			category.remove(controller.getSubCategory());
			view.remove(controller.getView());
			controllers.remove(controller);
			for (CategoryListener l : listeners){
				l.subCategoryRemoved(event);
			}
		}
		finally{
			lock.unlock();
		}
	}
	
	public void insert(int index, SubCategoryController controller){
		CategoryEvent event = new CategoryEvent(this, controller, index);
		lock.lock();
		try{
			category.add(controller.getSubCategory());
			view.add(controller.getView());
			controllers.add(index, controller);
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
		try {
			listeners.add(listener);
		}
		finally {
			lock.unlock();
		}
	}
	
	public void removeListener(CategoryListener listener){
		lock.lock();
		try {
			listeners.remove(listener);
		}
		finally {
			lock.unlock();
		}
	}

	public ICategory getCategory() {
		return category;
	}

	public CategoryView getView() {
		return view;
	}
	
	
	
	

}
