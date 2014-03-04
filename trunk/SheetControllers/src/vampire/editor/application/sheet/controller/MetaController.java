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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vampire.editor.plugin.api.application.sheet.controller.MetaControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MetaEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MetaListener;
import vampire.editor.plugin.api.domain.sheet.Meta;
import vampire.editor.plugin.api.domain.sheet.MetaAPI;
import vampire.editor.plugin.api.view.sheet.MetaView;

public class MetaController implements MetaControllerAPI{
	
	private final Meta meta;
	
	private final MetaView metaView;
	
	private final List<MetaEntryControllerAPI> metaEntryControllers = new ArrayList<>();
	
	private final Map<String, MetaEntryControllerAPI> metaEntryControllerMap = new HashMap<>();

	public MetaController(Meta meta, MetaView metaView) {
		super();
		this.meta = meta;
		this.metaView = metaView;
	}
	
	@Override
	public void addMetaEntry(MetaEntryControllerAPI controller) {
		
	}
	
	void addMetaEntry0(MetaEntryControllerAPI controller){
		metaEntryControllers.add(controller);
		String title = controller.getMetaEntry().getName();
		metaEntryControllerMap.put(title, controller);
	}
	
	@Override
	public MetaEntryControllerAPI getMetaEntryController(String key) {
		return metaEntryControllerMap.get(key);
	}

	@Override
	public MetaAPI getMeta() {
		return meta;
	}

	@Override
	public MetaView getView() {
		return metaView;
	}

	@Override
	public void addListener(MetaListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(MetaListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMetaEntry(MetaEntryControllerAPI metaEntry) {
		// TODO Auto-generated method stub
		
	}
	
	

}
