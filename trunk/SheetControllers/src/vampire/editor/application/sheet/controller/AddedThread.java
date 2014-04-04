package vampire.editor.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.events.NonLeafListener;

public class AddedThread<E, L extends NonLeafListener<E>> extends AbstractNonLeafThread<E, L>{

	public AddedThread(E e, L l) {
		super(e, l);
	}

	@Override
	public void run() {
		l.added(e);
	}

}
