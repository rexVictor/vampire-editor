package vampire.editor.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.events.NonLeafListener;

public class RemovedThread<E, L extends NonLeafListener<E>> extends AbstractNonLeafThread<E, L>{
	
	public RemovedThread(E e, L l) {
		super(e, l);
	}
	
	protected RemovedThread(){
		super();
	}

	@Override
	public void run(){
		l.removed(e);
	}

	@SuppressWarnings("hiding")
	@Override
	protected <E, L extends NonLeafListener<E>> AbstractNonLeafThread<E, L> newInstance(
			E e, L l) {
		return new RemovedThread<>(e, l);
	}

}
