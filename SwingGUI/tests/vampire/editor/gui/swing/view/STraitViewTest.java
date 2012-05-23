package vampire.editor.gui.swing.view;

import static org.junit.Assert.*;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.junit.Test;

import vampire.editor.gui.swing.application.PopupMenuBuilder;

public class STraitViewTest {

	@Test
	public void test() throws InterruptedException {
		SValueView valueView = new SValueView(new ValueViewAttributesTestImplementation());
		STraitView traitView = new STraitView(valueView, new DictionaryTestImplementation(), new TraitViewAttributesTestImplementation());
		STraitView traitView2 = new STraitView(valueView, new DictionaryTestImplementation(), new TraitViewAttributesTestImplementation());
		
		PopupMenuBuilder builder = new PopupMenuBuilder(new DictionaryTestImplementation());
		JPopupMenu menu = builder.createJPopupMenu(new PopupEntryTestImplementation());
		traitView.getField().setComponentPopupMenu(menu);
		traitView2.getField().setComponentPopupMenu(menu);
				
		JPopupMenu testMenu = new JPopupMenu();
		testMenu.add(new JMenuItem("Kuckuck"));
		//traitView.getField().setComponentPopupMenu(testMenu);
		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout(0, 1));
		frame.add(traitView.getPanel());
		frame.add(traitView2.getPanel());
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.add("Hinzugefügt");
		Thread.sleep(15000);
	}

}
