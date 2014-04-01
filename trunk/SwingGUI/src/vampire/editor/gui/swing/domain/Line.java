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
package vampire.editor.gui.swing.domain;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Line {
	
	private final Image image;
	
	
	
	public Line(String path){
		Image temp = null;
		try {
			temp = ImageIO.read(new File(path));
		} catch (IOException e1) {
			e1.printStackTrace();
			
		}
		image = temp;
	}
	
	public Image getImage(){
		return image;
	}
	
	public Image getImage(int width){
		return image.getScaledInstance(width, -1, Image.SCALE_SMOOTH);
	}
	
	public static Line getDefault(){
		Line line = new Line("lines/default.gif"); //$NON-NLS-1$
		return line;
	}
	
	

}
