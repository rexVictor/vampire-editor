package vampire.editor.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.events.NonLeafListener;

public abstract class AbstractNonLeafThread<E,L extends NonLeafListener<E>> extends Thread{
	
	protected final E e;
	
	protected final L l;

	public AbstractNonLeafThread(E e, L l) {
		super();
		this.e = e;
		this.l = l;
	}
	
	@Override
	public abstract void run();
	
}
