package vampire.editor.application.sheet.controller;

import java.util.HashMap;
import java.util.Map;

import vampire.editor.plugin.api.application.sheet.events.NonLeafListener;

public abstract class AbstractNonLeafThread<E,L extends NonLeafListener<E>> extends Thread{
	
	private static Map<Class<?>, AbstractNonLeafThread<?, ?>> map = new HashMap<>();
	
	static{
		map.put(AddedThread.class, new AddedThread<>());
		map.put(RemovedThread.class, new RemovedThread<>());
	}
	
	public static <E, L extends NonLeafListener<E>> void startThread
				(E e, L l, Class<?> clazz){
		AbstractNonLeafThread<?, ?> protoType = map.get(clazz);
		AbstractNonLeafThread<E, L> actual = protoType.newInstance(e, l);
		actual.start();
	}
	
	protected final E e;
	
	protected final L l;

	protected AbstractNonLeafThread(E e, L l) {
		super();
		this.e = e;
		this.l = l;
	}
	
	protected AbstractNonLeafThread(){
		this(null, null);
		
	}
	
	@Override
	public abstract void run();
	
	@SuppressWarnings("hiding")
	protected abstract <E,L extends NonLeafListener<E>> AbstractNonLeafThread<E, L> newInstance(E e, L l);
	
}
