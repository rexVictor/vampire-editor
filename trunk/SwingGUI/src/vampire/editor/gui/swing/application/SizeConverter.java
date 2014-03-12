package vampire.editor.gui.swing.application;

import java.awt.Toolkit;

public class SizeConverter {
	
	public static final double INCH_IN_MILLIMETERS = 25.4;
	
	public static int millimetersToPixel(double millimeters){
		double inches = millimeters / INCH_IN_MILLIMETERS;
		return inchesToPixel(inches);
	}
	
	public static int inchesToPixel(double inches){
		int dotsPerInch = Toolkit.getDefaultToolkit().getScreenResolution();
		return (int) (inches * dotsPerInch);
	}
	
	public static double pixelToInches(int pixels){
		int dotsPerInch = Toolkit.getDefaultToolkit().getScreenResolution();
		return pixels / dotsPerInch;
	}
	
	public static double pixelToMillimeters(int pixels){
		return pixelToInches(pixels) * INCH_IN_MILLIMETERS;
	}

}
