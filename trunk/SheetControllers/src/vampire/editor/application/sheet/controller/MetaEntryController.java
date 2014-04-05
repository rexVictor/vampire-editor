/*******************************************************************************
 * Vampire Editor SheetControllers.
 * Copyright (C) 2014  Matthias Johannes Reimchen
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Further information can be obtained at
 * https://code.google.com/p/vampire-editor/ or via mail:
 * Matthias Johannes Reimchen
 * development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.application.sheet.controller;

import vampire.editor.application.sheet.events.MetaEntryEvent;
import vampire.editor.plugin.api.application.sheet.controller.ControllerVisitor;
import vampire.editor.plugin.api.application.sheet.controller.MetaEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MetaEntryListener;
import vampire.editor.plugin.api.domain.sheet.MetaEntry;
import vampire.editor.plugin.api.view.events.MetaEntryViewEvent;
import vampire.editor.plugin.api.view.events.MetaEntryViewListener;
import vampire.editor.plugin.api.view.sheet.MetaEntryView;

public class MetaEntryController extends AbstractController<MetaEntry, MetaEntryView, MetaEntryListener>
									implements MetaEntryViewListener, MetaEntryControllerAPI{
	
	public MetaEntryController(MetaEntry metaEntry, MetaEntryView metaEntryView) {
		super(metaEntry, metaEntryView);
		metaEntryView.addListener(this);
	}

	@Override
	public void titleChanged(MetaEntryViewEvent event) {
		String formerValue = model.getValue();
		String formerKey = model.getName();
		String newKey = event.getContent();
		String newValue = event.getTitle();
		MetaEntryEvent e = new MetaEntryEvent(formerValue, newValue, formerKey, newKey, this);
		lock.lock();
		try{
			model.setName(newKey);
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
		String formerValue = model.getValue();
		String formerKey = model.getName();
		String newKey = event.getTitle();
		String newValue = event.getContent();
		final MetaEntryEvent e = new MetaEntryEvent(formerValue, newValue, formerKey, newKey, this);
		lock.lock();
		try{
			model.setValue(newValue);
			for(MetaEntryListener l : listeners){
				final MetaEntryListener listener = l;
				new Thread(){
					@Override
					public void run(){
						listener.valueChanged(e);
					}
				}.start();
				l.valueChanged(e);
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void accept(ControllerVisitor visitor) {
		visitor.visit(this);
	}
	
}
