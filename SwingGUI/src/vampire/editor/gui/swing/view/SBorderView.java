/*******************************************************************************
 * The Sheetview of the vampire editor implemended using Swing.
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
package vampire.editor.gui.swing.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.JPanel;
import javax.swing.RepaintManager;

import vampire.editor.plugin.api.domain.BorderAPI;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;

public class SBorderView extends JPanel implements Printable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6969397550306856891L;

	private Image border;
	
	private final FormLayout layout = new FormLayout();
	
	private final SSheetView sheetView;
	
	private boolean scaled = false;
	
	private final int width;
	
	public SBorderView(BorderAPI border, SSheetView sheetView){
		setBackground(Color.WHITE);
		this.setLayout(layout);
		layout.appendRow(RowSpec.decode(border.getTopInset()+"mm:none"));
		layout.appendRow(RowSpec.decode(border.getSheetHeight()+"mm:none"));
		layout.appendRow(RowSpec.decode(border.getBottomInset()+"mm:none"));
		layout.appendColumn(ColumnSpec.decode(border.getLeftInset()+"mm:none"));
		layout.appendColumn(ColumnSpec.decode(border.getSheetWidth()+"mm:none"));
		layout.appendColumn(ColumnSpec.decode(border.getRightInset()+"mm:none"));
		width = border.getSheetWidth()+border.getLeftInset()+border.getRightInset();
		Image scaled = border.getImage();
		this.border = scaled;
		
		this.sheetView = sheetView;
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridX 		= 	2;
		constraints.gridY		=	2;
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.vAlign		=	CellConstraints.TOP;
		constraints.hAlign		=	CellConstraints.FILL;
		add(sheetView.getPanel(), constraints);
	}

	public Image getImage() {
		return border;
	}

	public SSheetView getSheetView() {
		return sheetView;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		if (!scaled){
			border = border.getScaledInstance(Sizes.millimeterAsPixel(width, this), -1, Image.SCALE_SMOOTH);
			scaled = true;
		}
		g.drawImage(border, 0, 0, this);
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		if (pageIndex > 0){
			return NO_SUCH_PAGE;
		}
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		double x = pageFormat.getImageableX();
		double y = pageFormat.getImageableY();
		g.translate(x, y);
		double width = pageFormat.getImageableWidth();
		double scaleFactor = width/getWidth();
		g.scale(scaleFactor, scaleFactor);
		RepaintManager currentManager =  RepaintManager.currentManager(this);
		currentManager.setDoubleBufferingEnabled(false);
		printAll(g);
		currentManager.setDoubleBufferingEnabled(true);
		
		return PAGE_EXISTS;
	}
	
	
	
}
