package vampire.editor.gui.swing.mainframe.view;

import java.awt.Dimension;

import javax.swing.JDesktopPane;

import vampire.editor.gui.swing.application.SizeConverter;

public class PluginPanel extends JDesktopPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7327914698664192789L;
	
	private final Dimension preferredSize = new Dimension(
			SizeConverter.millimetersToPixel(100), SizeConverter.millimetersToPixel(200));
	
	
	@Override
	public Dimension getPreferredSize(){
		return preferredSize;
	}

}
