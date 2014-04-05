package vampire.editor.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.events.NonLeafListener;

public class AddedThread<E, L extends NonLeafListener<E>> extends AbstractNonLeafThread<E, L>{

	public AddedThread(E e, L l) {
		super(e, l);
	}
	
	protected AddedThread(){
		super();
	}

	@Override
	public void run() {
		l.added(e);
	}

	@SuppressWarnings("hiding")
	@Override
	protected <E, L extends NonLeafListener<E>> AbstractNonLeafThread<E, L> newInstance(
			E e, L l) {
		return new AddedThread<>(e, l);
	}
	
}
