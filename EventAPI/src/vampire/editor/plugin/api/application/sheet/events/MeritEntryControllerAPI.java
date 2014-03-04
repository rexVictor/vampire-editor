/*******************************************************************************
 * Vampire Editor Event API.
 * Copyright (C) 2013  Matthias Johannes Reimchen
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
package vampire.editor.plugin.api.application.sheet.events;

import vampire.editor.plugin.api.domain.sheet.MeritAPI;
import vampire.editor.plugin.api.view.sheet.MeritEntryView;

public interface MeritEntryControllerAPI {
	
	public MeritAPI getMerit();
	
	public MeritEntryView getView();
	
	public void setCost(int cost);
	
	public void setName(String name);
	
	public void addListener(MeritEntryListener l);
	
	public void removeListener(MeritEntryListener l);
	
	public MeritEntryControllerAPI clone();
	

}