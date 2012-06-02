package vampire.editor.gui.swing.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;


public class LineImage {
	
	private class LinePanel extends JPanel implements AncestorListener{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -6896289050615847338L;

		private Image image;
		
		private String title;
		
		private Font font;
		
		private LinePanel(Image image) {
			super();
			this.image = image;	
			addAncestorListener(this);
		}
		
		public void setTitle(String title){
			this.title = title;
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
		}

		@Override
		public void paint(Graphics g){
			super.paint(g);			
			g.drawImage(image, 0, 0, this);
			g.setFont(font);
			FontRenderContext frc = g.getFontMetrics().getFontRenderContext();
			Rectangle2D rectangle = g.getFont().getStringBounds(title, frc);
			int x = (int) ((image.getWidth(this)-rectangle.getWidth())/2);			
			int y = image.getHeight(this);
			g.setColor(Color.WHITE);			
			g.fillRect(x, 0, (int) rectangle.getWidth(), image.getHeight(this));
			g.setColor(Color.BLACK);
			g.drawString(title, x, y);
			
			
		}

		@Override
		public void ancestorAdded(AncestorEvent event) {
			image = image.getScaledInstance(getWidth(), -1, Image.SCALE_SMOOTH);
		}

		@Override
		public void ancestorRemoved(AncestorEvent event) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void ancestorMoved(AncestorEvent event) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public int getHeight(){
			return Math.max(super.getHeight(), image.getHeight(this));
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
