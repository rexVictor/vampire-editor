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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.jgoodies.forms.layout.FormLayout;

public class LineImage {
	
	private class LinePanel extends JPanel implements AncestorListener{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -6896289050615847338L;

		private Image image;
		
		private String title;
		
		private Font font;
		
		private JLabel label = new JLabel();
		
		private int yImage;
		
		private int xText;
		
		private TextLayout layout;
		
		private Rectangle2D rectangle;
		
		private boolean paintable = false;
		
		private LinePanel(Image image) {
			super();
			this.setLayout(new FormLayout());
			this.image = image;	
			addAncestorListener(this);
			label.setOpaque(true);
			label.setBackground(Color.WHITE);
			//this.add(label);
			this.setBackground(Color.WHITE);			
		}
		
		public void setTitle(String title){
			this.title = title;
			label.setText(title);
			refreshTextLayout();
			invalidate();
		}
		
		public String getTitle(){
			return title;
		}
		
		
		
		public Image getImage() {
			return image;
		}

		public void setImage(Image image) {
			this.image = image;
			invalidate();
		}
		
		public void setTitleFont(Font font){
			this.font = font;
			label.setFont(font);
			refreshTextLayout();
			invalidate();
		}
		
		private void refreshTextLayout(){
			if (getGraphics() != null) {
				FontRenderContext context = getGraphics().getFontMetrics().getFontRenderContext();
				layout = new TextLayout(title, font, context);
				rectangle = layout.getBounds();
				GlyphVector xGlyph = font.createGlyphVector(context, "x");
				double xHeight = xGlyph.getGlyphMetrics(0).getBounds2D().getMaxX();
				yImage = (int) (rectangle.getHeight()-(xHeight+image.getHeight(null))/2);
				xText = (int) ((getWidth()-rectangle.getWidth())/2);
				if (getParent() != null) paintable = true;
			}
		}
		
		@Override
		public void paint(Graphics g){
			super.paint(g);
			if (paintable){
				ancestorAdded(null);
				refreshTextLayout();
			g.setFont(font);
			g.drawImage(image, 0, yImage, this);
			g.setColor(Color.WHITE);
			if(rectangle != null){
				g.fillRect(xText-5, 0, (int) rectangle.getWidth()+10, (int) rectangle.getHeight());
				g.setColor(Color.BLACK);
				layout.draw((Graphics2D) g, xText, (float) rectangle.getHeight());
			}
			}
		}

		@Override
		public void ancestorAdded(AncestorEvent event) {
			if (getWidth()!=0)
				image = image.getScaledInstance(getWidth(), 10, Image.SCALE_SMOOTH);
			refreshTextLayout();
			if (getGraphics() != null) paintable = true;
		}

		@Override
		public void ancestorRemoved(AncestorEvent event) {
		}

		@Override
		public void ancestorMoved(AncestorEvent event) {
		}
		
		@Override
		public int getHeight(){
			return (int) (font.getSize()*1.2);
		}
		
		
	}
	
	private final LinePanel line;

	public LineImage(Image image) {
		this(image, "");
		
	}
	
	public LineImage(Image image, String title){
		super();
		this.line = new LinePanel(image);
		line.setTitle(title);
	}
	
	public void setTitle(String title){
		line.setTitle(title);
	}
	
	public String getTitle(){
		return line.getTitle();
	}
	
	public JPanel getPanel(){
		return line;
	}
	
	public void setImage(Image image){
		line.setImage(image);		
	}
	
	public Image getImage(){
		return line.getImage();
	}
	
	public void setTitleFont(Font font){
		line.setTitleFont(font);
	}
	
}
