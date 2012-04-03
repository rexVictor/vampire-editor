package vampire.editor.gui.swing.view;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import vampire.editor.plugin.api.view.events.TraitViewEvent;
import vampire.editor.plugin.api.view.events.TraitViewListener;

public class STraitViewTest implements TraitViewListener{
	
		
	private static STraitView view;
	
	private static JFrame frame;
	
	private String text;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SValueView valueView = new SValueView();
		view = new STraitView(valueView);
		frame = new JFrame();
		frame.setContentPane(view.getPanel());
		frame.setVisible(true);
		frame.pack();
		frame.setAlwaysOnTop(true);
		
	}

	@Test
	public void testSetText() throws Throwable {
		testSetTextWith("Hallo");
		testSetTextWith("Kuckuck");
		testSetTextWith("");
	}
	
	private void testSetTextWith(String text) throws Throwable{
		view.setName(text);
		Thread.sleep(100);
		assertEquals(text, view.getTextField().getText());
	}
	
	@Test
	public void testEventSender() throws Exception{
		view.addListener(this);
		testEventSenderWith("Kuckuck");
	}
	
		
	private void testEventSenderWith(String text) throws Exception{
		this.text = text;
		Point p = view.getTextField().getLocationOnScreen();
		int x = p.x + view.getTextField().getWidth()/2;
		int y = p.y + view.getTextField().getHeight()/2;
		
		Robot robot = new Robot();
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		
		writeText(robot, text);
		Thread.sleep(100);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);	
			
		assertEquals(text, view.getTextField().getText());	
	
	}
	
	private void writeText(Robot robot, String text) throws InterruptedException{
		char[] chars = text.toCharArray();
		for (Character c : chars) {
			if (Character.isUpperCase(c))
				robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(Character.toUpperCase(c));
			robot.keyRelease(Character.toUpperCase(c));
			if (Character.isUpperCase(c))
				robot.keyRelease(KeyEvent.VK_SHIFT);
			
		}
	}

	@Override
	public void traitNameChanged(TraitViewEvent event) {
		assertEquals(text, event.getName());		
	}
	
	@AfterClass
	public static void clear(){
		frame.setVisible(false);
		frame.dispose();
	}

	

}
