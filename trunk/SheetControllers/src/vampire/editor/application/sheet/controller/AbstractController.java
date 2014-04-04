package vampire.editor.application.sheet.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AbstractController<M, V, L>{
	
	protected final M model;
	
	protected final V view;
	
	protected final List<L> listeners = new LinkedList<>();
	
	protected final Lock lock = new ReentrantLock(true);

	public AbstractController(M model, V view) {
		super();
		this.model = model;
		this.view = view;
	}
	
	public void addListener(L l){
		lock.lock();
		try{
			listeners.add(l);
		}
		finally{
			lock.unlock();
		}
	}
	
	public void removeListener(L l){
		lock.lock();
		try{
			listeners.remove(l);
		}
		finally{
			lock.unlock();
		}
	}

	public M getModel() {
		return model;
	}

	public V getView() {
		return view;
	}
	
	
	
	
	
	
}
