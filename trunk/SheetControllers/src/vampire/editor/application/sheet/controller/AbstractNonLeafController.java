package vampire.editor.application.sheet.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import vampire.editor.plugin.api.application.sheet.controller.AbstractControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.ControllerVisitor;
import vampire.editor.plugin.api.application.sheet.events.NonLeafListener;
import vampire.editor.plugin.api.domain.sheet.AbstractNonLeafModel;
import vampire.editor.plugin.api.view.sheet.Addable;

public abstract class AbstractNonLeafController<M extends AbstractNonLeafModel<SM>, V extends Addable<SV>, L extends NonLeafListener<E>,
									SC extends AbstractControllerAPI<SMAPI, SV, ?>, SMAPI, SM extends SMAPI, SV, E>
						extends AbstractController<M, V, L>{
	
	protected final List<SC> subControllers = new ArrayList<>();
						
	
	public AbstractNonLeafController(M m, V v) {
		super(m,v);
	}
	
	public Iterator<SC> iterator() {
		return subControllers.iterator();
	}

	public int size() {
		return subControllers.size();
	}

	public SC get(int i) {
		return subControllers.get(i);
	}

	public int indexOf(SC subController) {
		return subControllers.indexOf(subController);
	}
	
	protected void callListenersAdded(final E e){
		callListeners(e, AddedThread.class);
	}
	
	protected void callListenersRemoved(final E e){
		callListeners(e, RemovedThread.class);
	}
	
	private void callListeners(E e, Class<?> clazz){
		for (L l : listeners){
			AbstractNonLeafThread.startThread(e, l, clazz);
		}
	}
	
	public void add(SC subController){
		int index = subControllers.size();
		E e = generateEvent(subController, index);
		lock.lock();
		try{
			@SuppressWarnings("unchecked")
			SM subModel = (SM) subController.getModel();
			model.add(subModel);
			view.add(subController.getView());
			subControllers.add(subController);
			callListenersAdded(e);
		}
		finally{
			lock.unlock();
		}
	}
	
	void add0(SC subController){
		int index = subControllers.size();
		E e = generateEvent(subController, index);
		lock.lock();
		try{
			subControllers.add(subController);
			callListenersAdded(e);
		}
		finally{
			lock.unlock();
		}
	}
	
	public void remove(SC subController){
		E event = generateEvent(subController, subControllers.indexOf(subController));
		lock.lock();
		try{
			@SuppressWarnings("unchecked")
			SM subModel = (SM) subController.getModel();
			model.remove(subModel);
		    view.remove(subController.getView());
			subControllers.remove(subController);
			callListenersRemoved(event);
		}
		finally{
			lock.unlock();
		}
	}
	
	public void insert(int index, SC controller){
		E event = generateEvent(controller, index);
		lock.lock();
		try{
			@SuppressWarnings("unchecked")
			SM subModel = (SM) controller.getModel();
			model.insert(index, subModel);
			view.insert(index, controller.getView());
			subControllers.add(index, controller);
			callListenersAdded(event);
		}
		finally{
			lock.unlock();
		}
	}
	
	public void visitChildren(ControllerVisitor visitor){
		for (SC sc : subControllers){
			sc.accept(visitor);
		}
	}
	
	protected abstract E generateEvent(SC reason, int index);
	
}
