package vampire.editor.gui.swing.view;

import static org.junit.Assert.*;

import java.awt.Font;

import javax.swing.JFrame;

import org.junit.Test;

import vampire.editor.domain.sheet.view.MeritEntryViewAttibutes;
import vampire.editor.domain.sheet.view.MeritViewAttributes;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.sheetloader.application.importer.ResourcesHolderTestImplementation;

public class SMeritViewTest {

	@Test
	public void test() throws InterruptedException {
		JFrame frame = new JFrame();
		ResourcesHolderAPI resources = new ResourcesHolderTestImplementation();
		Font font = resources.getFont("cas_antn").deriveFont(24f);
		MeritViewAttributes viewAtts = new MeritViewAttributes();
		MeritEntryViewAttibutes viewAtts2 = new MeritEntryViewAttibutes();
		viewAtts2.setFont(font);
		viewAtts.setFont(font);
		SMeritView view = new SMeritView("title", resources.getDictionary("sheet"), viewAtts);
		
		for (int i = 0; i <= 5; i++){
			SMeritEntryView entryView = new SMeritEntryView(resources.getDictionary(""), viewAtts2);
			entryView.setCost(i);
			entryView.setText("text"+i);
			view.addMeritEntryView(entryView);
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
