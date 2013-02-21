package vampire.editor.gui.swing.view;

import static org.junit.Assert.*;

import java.awt.Font;

import javax.swing.JFrame;

import org.junit.Test;

import vampire.editor.domain.sheet.view.HealthEntryViewAttributes;
import vampire.editor.domain.sheet.view.HealthViewAttributes;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.sheetloader.application.importer.ResourcesHolderTestImplementation;

public class SHeathViewTest {

	@Test
	public void test() throws InterruptedException {
		JFrame frame = new JFrame();
		ResourcesHolderAPI resources = new ResourcesHolderTestImplementation();
		Font font = resources.getFont("cas_antn").deriveFont(24f);
		HealthViewAttributes viewAtts = new HealthViewAttributes();
		HealthEntryViewAttributes viewAtts2 = new HealthEntryViewAttributes();
		viewAtts2.setFont(font);
		viewAtts.setFont(font);
		SHealthView view = new SHealthView(resources.getDictionary("sheet"), viewAtts);
		
		for (int i = 0; i <= 5; i++){
			SHealthEntryView entryView = new SHealthEntryView(resources.getDictionary(""), viewAtts2);
			entryView.setDescription("text"+i);
			entryView.setPenalty(i);
			entryView.setDamageType(null);
			view.addHealthEntryView(entryView);
		}
		frame.setContentPane(view.getPanel());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.pack();
		
		while(frame.isVisible()){
			Thread.sleep(10);
		}
		
		fail("Not yet implemented");
	}

}
