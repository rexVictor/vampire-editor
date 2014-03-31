/*******************************************************************************
 * The Sheetview of the vampire editor implemented using Swing.
 * Copyright (C) 2013 Matthias Reimchen
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
 * For further information see https://code.google.com/p/vampire-editor/ or write a
 * mail to development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.gui.swing.view.valueviews;

import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;

class StaticSpacedValueView extends SpacedValueView{

	/**
	 * The ValueView for static, showSpace and no Squares
	 * @param viewAtts
	 */
	StaticSpacedValueView(ValueViewAttributes viewAtts) {
		super(viewAtts);
	}
	
	StaticSpacedValueView() {
	}
	
	@Override
	public void addCircle(){}
	
	@Override
	public void removeCircle(){}

	public StaticSpacedValueView clone(){
		ValueViewAttributes clone = viewAtts.clone();
		return new StaticSpacedValueView(clone);
	}
	
	@Override
	public AbstractValueView createNew(ValueViewAttributes viewAtts) {
		return new StaticSpacedValueView(viewAtts);
	}

}
