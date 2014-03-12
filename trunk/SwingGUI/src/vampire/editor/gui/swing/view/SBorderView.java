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
package vampire.editor.gui.swing.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.JPanel;
import javax.swing.RepaintManager;

import vampire.editor.gui.swing.application.SizeConverter;
import vampire.editor.plugin.api.domain.BorderAPI;

public class SBorderView extends JPanel implements Printable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6969397550306856891L;

	private final Image border;
	
	private final SSheetView sheetView;
	
	private final int width;
	
	private final int height;
	
	private Dimension dimension;
	
	public SBorderView(BorderAPI border, SSheetView sheetView){
		this.sheetView = sheetView;
		
		int topInset = SizeConverter.millimetersToPixel(border.getTopInset());
		int sheetHeight = SizeConverter.millimetersToPixel(border.getSheetHeight());
		int bottomInset = SizeConverter.millimetersToPixel(border.getBottomInset());
		int leftInset = SizeConverter.millimetersToPixel(border.getLeftInset());
		int sheetWidth = SizeConverter.millimetersToPixel(border.getSheetWidth());
		int rightInset = SizeConverter.millimetersToPixel(border.getRightInset());
		
		this.width = leftInset + sheetWidth + rightInset;
		this.height = topInset + sheetHeight + bottomInset;
		this.dimension = new Dimension(width, height);
		System.out.println("Width: "+this.width);
		
		this.border = border.getImage().getScaledInstance(width, -1, Image.SCALE_SMOOTH);
		System.out.println("Image width: " +this.border.getWidth(this));
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel view = sheetView.getPanel();
		this.add(view);
		view.setBounds(leftInset, topInset, sheetWidth, sheetHeight);
		repaint();
	}
	
	@Override
	public int getWidth(){
		return width;
	}
	
	@Override
	public int getHeight(){
		return height;
	}
	
	@Override
	public Dimension getPreferredSize(){
		return dimension;
	}
	
	@Override
	public Dimension getMaximumSize(){
		return dimension;
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
