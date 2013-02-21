package vampire.editor.gui.swing.view;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.Test;

import vampire.editor.domain.sheet.view.BloodPoolViewAttributes;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.sheetloader.application.importer.ResourcesHolderTestImplementation;

public class SBloodPoolViewTest {

	@Test
	public void test() throws InterruptedException {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		BloodPoolViewAttributes viewAtts = new BloodPoolViewAttributes();
		ResourcesHolderAPI holder = new ResourcesHolderTestImplementation();
		viewAtts.setFont(holder.getFont("cas_antn").deriveFont(20f));
		SBloodPoolView view = new SBloodPoolView(viewAtts, holder.getDictionary("sheet"));
		frame.setContentPane(view.getView());
		frame.pack();
		frame.setVisible(true);
		view.setMaxValue(100);
		while (frame.isVisible()){
			Thread.sleep(10);
		}
		fail("Not yet implemented");
	}

}
