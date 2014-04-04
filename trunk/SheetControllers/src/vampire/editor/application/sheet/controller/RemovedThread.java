package vampire.editor.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.events.NonLeafListener;

public class RemovedThread<E, L extends NonLeafListener<E>> extends AbstractNonLeafThread<E, L>{
	
	public RemovedThread(E e, L l) {
		super(e, l);
	}

	@Override
	public void run(){
		l.removed(e);
	}

}
