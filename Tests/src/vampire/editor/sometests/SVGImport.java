package vampire.editor.sometests;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.junit.Test;

public class SVGImport {
	
	 public static BufferedImage loadImage(File svgFile, float width, float height) throws IOException, TranscoderException
	  {
	    BufferedImageTranscoder imageTranscoder = new BufferedImageTranscoder();
	 
	    imageTranscoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, width);
	    imageTranscoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, height);
	 
	    TranscoderInput input = new TranscoderInput(Files.newInputStream(svgFile.toPath()));
	    
	    imageTranscoder.transcode(input, null);
	 
	    return imageTranscoder.getBufferedImage();
	  }
	
	static class BufferedImageTranscoder extends ImageTranscoder
	{
	  @Override
	  public BufferedImage createImage(int w, int h)
	  {
	    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    return bi;
	  }
	 
	  @Override
	  public void writeImage(BufferedImage img, TranscoderOutput output)
	  {
	    this.img = img;
	  }
	 
	  public BufferedImage getBufferedImage()
	  {
	    return img;
	  }
	  private BufferedImage img = null;
	}

	@Test
	public void test() throws IOException, Throwable {
		System.out.println(Arrays.toString(ImageIO.getReaderFileSuffixes()));
		Path path = Paths.get("..", "SwingGUI","images","red_striped_circle.svg");
		final BufferedImage image = loadImage(path.toFile(), 12, 12);
		@SuppressWarnings("serial")
		JPanel panel = new JPanel(){
			@Override
			public void paint(Graphics g){
				g.drawImage(image, 0, 0, null);
			}
		};
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, 200, 200);
		frame.setContentPane(panel);
		frame.setVisible(true);
		
		while (frame.isVisible()){
			Thread.sleep(10);
		}
	}

}
