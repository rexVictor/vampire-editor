package vampire.editor.application.sheet.controller;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import vampire.editor.application.sheet.events.MetaEntryEvent;
import vampire.editor.domain.sheet.MetaEntry;
import vampire.editor.plugin.api.application.sheet.controller.MetaEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MetaEntryListener;
import vampire.editor.plugin.api.view.events.MetaEntryViewEvent;
import vampire.editor.plugin.api.view.events.MetaEntryViewListener;
import vampire.editor.plugin.api.view.sheet.MetaEntryView;

public class MetaEntryController implements MetaEntryViewListener, MetaEntryControllerAPI{
	
	private final MetaEntry metaEntry;
	
	private final MetaEntryView metaEntryView;
	
	private final List<MetaEntryListener> listeners = new LinkedList<>();
	
	private final Lock lock = new ReentrantLock();
	
	

	public MetaEntryController(MetaEntry metaEntry, MetaEntryView metaEntryView) {
		super();
		this.metaEntry = metaEntry;
		this.metaEntryView = metaEntryView;
		metaEntryView.addListener(this);
	}

	@Override
	public MetaEntry getMetaEntry() {
		return metaEntry;
	}

	@Override
	public MetaEntryView getMetaEntryView() {
		return metaEntryView;
	}

	@Override
	public void addMetaEntryListener(MetaEntryListener listener){
		lock.lock();
		try{
			listeners.add(listener);
		}
		finally{
			lock.unlock();
		}
	}
	
	@Override
	public void removeMetaEntryListener(MetaEntryListener listener){
		lock.lock();
		try{
			listeners.remove(listener);
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void titleChanged(MetaEntryViewEvent event) {
		String formerValue = metaEntry.getValue();
		String formerKey = metaEntry.getName();
		String newKey = event.getContent();
		String newValue = event.getTitle();
		MetaEntryEvent e = new MetaEntryEvent(formerValue, newValue, formerKey, newKey, this);
		lock.lock();
		try{
			metaEntry.setName(newKey);
			for(MetaEntryListener l : listeners){
				l.keyChanged(e);
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void contentChanged(MetaEntryViewEvent event) {
		String formerValue = metaEntry.getValue();
		String formerKey = metaEntry.getName();
		String newKey = event.getTitle();
		String newValue = event.getContent();
		MetaEntryEvent e = new MetaEntryEvent(formerValue, newValue, formerKey, newKey, this);
		lock.lock();
		try{
			metaEntry.setValue(newValue);
			for(MetaEntryListener l : listeners){
				l.valueChanged(e);
			}
		}
		finally{
			lock.unlock();
		}
	}
	
	

}
