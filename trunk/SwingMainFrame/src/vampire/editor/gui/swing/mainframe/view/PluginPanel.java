package vampire.editor.gui.swing.mainframe.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import vampire.editor.gui.swing.application.SizeConverter;

public class PluginPanel extends JDesktopPane{
	
	private int totalHeight = 0;

	/**
	 * 
	 */
	private static final long serialVersionUID = -7327914698664192789L;
	
	private final Dimension preferredSize = new Dimension(
			SizeConverter.millimetersToPixel(100), SizeConverter.millimetersToPixel(200));
	
	public Component add(Container container){
		JInternalFrame internalFrame = new JInternalFrame("test", true, false, false, false); //$NON-NLS-1$
		internalFrame.putClientProperty("JInternalFrame.isPalette",Boolean.TRUE); //$NON-NLS-1$
		internalFrame.setContentPane(container);
		internalFrame.pack();
		add((Component) internalFrame);
		internalFrame.setLocation(0, totalHeight);
		internalFrame.setVisible(true);
		totalHeight += internalFrame.getHeight();
		return internalFrame;
	}
	
	
	@Override
	public Dimension getPreferredSize(){
		return preferredSize;
	}

}
