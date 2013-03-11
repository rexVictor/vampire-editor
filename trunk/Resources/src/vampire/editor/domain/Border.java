/*******************************************************************************
 * Vampire Editor Resources Management Implementation.
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
package vampire.editor.domain;

import java.awt.Image;

import vampire.editor.plugin.api.domain.BorderAPI;

/**
 * The class providing the border object. <br>
 * It stores an {@link Image} and the sizes in milli meters.
 * @author rex_victor
 *
 */
public class Border implements BorderAPI {
	
	private final Image image;
	
	private final int left;
	
	private final int right;
	
	private final int up;
	
	private final int down;
	
	private final int sheetWidth;
	
	private final int sheetHeight;

	public Border(Image image, int left, int right, int up, int down,
			int sheetWidth, int sheetHeight) {
		super();
		this.image = image;
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
		this.sheetWidth = sheetWidth;
		this.sheetHeight = sheetHeight;
	}

	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public int getLeftInset() {
		return left;
	}

	@Override
	public int getRightInset() {
		return right;
	}

	@Override
	public int getTopInset() {
		return up;
	}

	@Override
	public int getBottomInset() {
		return down;
	}

	@Override
	public int getSheetWidth() {
		return sheetWidth;
	}

	@Override
	public int getSheetHeight() {
		return sheetHeight;
	}

}
