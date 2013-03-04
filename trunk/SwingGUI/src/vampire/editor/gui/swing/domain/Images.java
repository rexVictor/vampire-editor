package vampire.editor.gui.swing.domain;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;

public class Images {
	
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
	
	 private static final Map<String, Document> svgs = new HashMap<>();
		
	 private static final Map<String, Map<Dimension, Image>> images = new HashMap<>();
	 
	 public static final String CIRCLE_EMPTY = "circle_empty";
	 
	 public static final String CIRCLE_FILLED = "circle_filled";
	 
	 public static final String CIRCLE_TEMP_GREATER = "circle_temp_greater";
	 
	 public static final String CIRCLE_TEMP_LOWER = "circle_temp_lower";
	 
	 public static final String SQUARE_EMPTY = "square_empty";
	 
	 public static final String SQUARE_SLASHED = "square_slashed";
	 
	 public static final String SQUARE_CROSSED = "square_crossed";
	 
	 static{
		 System.out.println("Images initialized");
		 Map<String, Path> svgFiles = new HashMap<>();
		 Path p = Paths.get("..", "SwingGUI", "images");
		 svgFiles.put(CIRCLE_EMPTY, p.resolve("empty_circle.svg"));
		 svgFiles.put(CIRCLE_FILLED, p.resolve("filled_circle.svg"));
		 svgFiles.put(CIRCLE_TEMP_GREATER, p.resolve( "temp_greater_circle.svg"));
		 svgFiles.put(CIRCLE_TEMP_LOWER, p.resolve("temp_lower_circle.svg"));
		 svgFiles.put("circle_green_striped", p.resolve( "green_striped_circle.svg"));
		 svgFiles.put(SQUARE_EMPTY, p.resolve("empty_square.svg"));
		 svgFiles.put(SQUARE_CROSSED, p.resolve("crossed_square.svg"));
		 svgFiles.put(SQUARE_SLASHED, p.resolve("slashed_square.svg"));
		 for (String s : svgFiles.keySet()){
			 makeDocument(s, svgFiles.get(s));
			 images.put(s, new HashMap<Dimension, Image>());
		 }
	 }
	 
	 private static void makeDocument(String key, Path path){
		 try {
			    String parser = XMLResourceDescriptor.getXMLParserClassName();
			    SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
			    Document doc = f.createSVGDocument(null,Files.newInputStream(path));
			    svgs.put(key, doc);
			} catch (IOException e) {
			    e.printStackTrace();
			}
	 }
	
	private static BufferedImage loadImage(Document svg, float width, float height) throws IOException, TranscoderException
	  {
	    BufferedImageTranscoder imageTranscoder = new BufferedImageTranscoder();
	 
	    imageTranscoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, width);
	    imageTranscoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, height);
	 
	    TranscoderInput input = new TranscoderInput(svg);
	    
	    imageTranscoder.transcode(input, null);
	 
	    return imageTranscoder.getBufferedImage();
	  }
	
	public static Image getImage(String key, int width, int height){
		Map<Dimension, Image> map = images.get(key);
		Dimension d = new Dimension(width, height);
		Image image = map.get(d);
		if (image == null){
			Document doc = svgs.get(key);
			try {
				image = loadImage(doc, width, height);
			} catch (IOException | TranscoderException e) {
				e.printStackTrace();
			}
			map.put(d, image);
		}
		return image;
	}
	
}
