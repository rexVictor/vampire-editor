/*******************************************************************************
 * Vampire Editor View Attributes Implementation.
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
package vampire.editor.domain.sheet.view;

import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;

/**
 * Provides the attributes for a value view.
 * @author rex_victor
 *
 */
class MValueViewAttributes implements ValueViewAttributes{
	
	private boolean showSpace;
	
	private boolean dynamic;
	
	private int circles;
	
	private boolean tempSquared = false;
	
	private int size;
	
	public MValueViewAttributes(){
	}
	
	public MValueViewAttributes(boolean showSpace, boolean dynamic, int circles,
			boolean tempSquared, int size) {
		super();
		this.showSpace = showSpace;
		this.dynamic = dynamic;
		this.circles = circles;
		this.tempSquared = tempSquared;
		this.size = size;
	}

	@Override
	public boolean isShowSpace() {
		return showSpace;
	}

	public void setShowSpace(boolean showSpace) {
		this.showSpace = showSpace;
	}

	@Override
	public boolean isDynamic() {
		return dynamic;
	}

	public void setDynamic(boolean dynamic) {
		this.dynamic = dynamic;
	}

	@Override
	public int getCircles() {
		return circles;
	}

	public void setCircles(int circles) {
		this.circles = circles;
	}
	
	
	@Override
	public boolean isTempSquared() {
		return tempSquared;
	}

	public void setTempSquared(boolean tempSquared) {
		this.tempSquared = tempSquared;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("dynamic: ");
		builder.append(dynamic);
		builder.append(", showspace: ");
		builder.append(showSpace);
		builder.append(", tempSquared: ");
		builder.append(tempSquared);
		builder.append(", circles: ");
		builder.append(circles);
		builder.append(", size: ");
		builder.append(size);
		builder.append("\n");
		return builder.toString();
	}

	@Override
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	@Override
	public ValueViewAttributes clone(){
		MValueViewAttributes clone = new MValueViewAttributes();
		clone.circles = circles;
		clone.dynamic = dynamic;
		clone.showSpace = showSpace;
		clone.size = size;
		clone.tempSquared = tempSquared;
		return clone;
	}
	
	/**
	 * Returns if dynamics, showSpaces, circles, sizes and tempSquareds are equal.
	 * @param that
	 * @return if this equals that
	 */
	@Override
	public boolean equals(Object that){
		if (that instanceof MValueViewAttributes){
			MValueViewAttributes toCompare = (MValueViewAttributes) that;
			return toCompare.dynamic == dynamic && toCompare.showSpace == showSpace 
					&& toCompare.circles == circles	&& toCompare.size == size 
					&& toCompare.tempSquared == tempSquared;
		}
		return false;
	}
	
	
	@Override
	public int hashCode(){
		return System.identityHashCode(dynamic)+2*System.identityHashCode(showSpace)
				+4*circles+8*size+16* System.identityHashCode(tempSquared);
	}

}
