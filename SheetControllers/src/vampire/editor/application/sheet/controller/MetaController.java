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

import java.util.HashMap;
import java.util.Map;

import vampire.editor.application.sheet.events.MetaEvent;
import vampire.editor.plugin.api.application.sheet.controller.ControllerVisitor;
import vampire.editor.plugin.api.application.sheet.controller.MetaControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MetaEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MetaEventAPI;
import vampire.editor.plugin.api.application.sheet.events.MetaListener;
import vampire.editor.plugin.api.domain.sheet.Meta;
import vampire.editor.plugin.api.domain.sheet.MetaEntry;
import vampire.editor.plugin.api.domain.sheet.MetaEntryAPI;
import vampire.editor.plugin.api.view.sheet.MetaEntryView;
import vampire.editor.plugin.api.view.sheet.MetaView;

public class MetaController extends AbstractNonLeafController<Meta, MetaView, MetaListener,
										MetaEntryControllerAPI, MetaEntryAPI, MetaEntry,
										MetaEntryView, MetaEventAPI>
						implements MetaControllerAPI{
	
	private final Map<String, MetaEntryControllerAPI> metaEntryControllerMap = new HashMap<>();
	
	public MetaController(Meta meta, MetaView metaView) {
		super(meta, metaView);
	}
	
	@Override
	public void add(MetaEntryControllerAPI controller) {
		super.add(controller);
		addToMap(controller);
	}
	
	@Override
	void add0(MetaEntryControllerAPI controller){
		super.add0(controller);
		addToMap(controller);
		
	}
	
	private void addToMap(MetaEntryControllerAPI controller){
		String title = controller.getModel().getName();
		metaEntryControllerMap.put(title, controller);
	}
	
	@Override
	public MetaEntryControllerAPI getMetaEntryController(String key) {
		return metaEntryControllerMap.get(key);
	}

	@Override
	protected MetaEventAPI generateEvent(MetaEntryControllerAPI reason,
			int index) {
		return new MetaEvent(this, reason, index);
	}

	@Override
	public void accept(ControllerVisitor visitor) {
		visitor.visit(this);
	}

}
