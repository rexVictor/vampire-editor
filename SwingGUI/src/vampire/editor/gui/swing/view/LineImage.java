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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class LineImage extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8146983659540433271L;
	
	private final Image image;
	
	private final String title;
	
	private final Font font;
	
	private int yImage;
	
	private int xText;
	
	private Rectangle2D rectangle;
	
	private TextLayout layout;
	
	private int height;

	public LineImage(Image image, Font font) {
		this(image, "", font);
		
	}
	
	public LineImage(Image image, String title, Font font){
		super();
		this.title = title;
		this.image = image;
		this.font = font;
		setBackground(Color.WHITE);
		calculate();
	}
	
	private void calculate(){
		AffineTransform af = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(af, true, true);
		layout = new TextLayout(title, font, frc);
		rectangle = layout.getBounds();
		float descent = layout.getDescent();
		height = (int) (rectangle.getHeight() + descent);
		GlyphVector xGlyph = font.createGlyphVector(frc, "x");
		double xHeight = xGlyph.getGlyphMetrics(0).getBounds2D().getMaxX();
		yImage = (int) (rectangle.getHeight()-(xHeight+image.getHeight(null))/2);
		xText = (int) ((getWidth()-rectangle.getWidth())/2);
	}
	
	@Override
	public int getHeight(){
		return Math.max(height, image.getHeight(this));
	}
	
	@Override
	public int getWidth(){
		return image.getWidth(this);
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(image.getWidth(this), height);
	}
	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.setFont(font);
		g.drawImage(image, 0, yImage, this);
		g.setColor(Color.WHITE);
		g.fillRect(xText-5, 0, (int) rectangle.getWidth()+10, (int) rectangle.getHeight());
		g.setColor(Color.BLACK);
		layout.draw((Graphics2D) g, xText, (float) rectangle.getHeight());
	}
	
}
