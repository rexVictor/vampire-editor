package vampire.editor.gui.swing.view;

import static org.junit.Assert.*;


import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;


import java.awt.Point;
import java.awt.Robot;

import java.awt.event.InputEvent;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.BeforeClass;
import org.junit.Test;

public class SValueViewTest {
	
	private static List<JLabel> labels = new ArrayList<>();
	
	private static JFrame frame;
	
	private static SValueView view;
	
	
	@BeforeClass
	public static void initialize() throws Exception{
		Thread.sleep(100);
		frame = new JFrame();
		view = new SValueView();
		JPanel panel = view.getPanel();
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
		frame.pack();
		frame.setAlwaysOnTop(true);
		Component[] components = panel.getComponents();
		for (Component c : components){
			if (c instanceof JLabel){
				labels.add((JLabel) c);
			}
		}
		
		
	}
	
	

	@Test(timeout = 3000)
	public void testMouseListener() throws AWTException, InterruptedException {
		testLeftClick(2);
		testLeftClick(4);
		testRightClick(3);
		testLeftClick(5);
		testRightClick(5);
		testRightClick(1);
		testLeftClick(7);
		testRightClick(0);
		testLeftClick(0);
		testRightClick(9);
			
	}
	
	private void testLeftClick(int which) throws AWTException, InterruptedException{
		view.setValue(0);
		final JLabel label = labels.get(which);
		Point p = label.getLocationOnScreen();
		int x = p.x + label.getWidth()/2;
		int y = p.y + label.getHeight()/2;
		Robot robot = new Robot();
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		while (label.getText().equals(SValueView.CIRCLE_WHITE)) {
			Thread.sleep(1);
		}
		
		testCorrect(which+1, 0, 0, view.getCircleCount()-which-1);
		
		
	}
	
	private void testRightClick(int which) throws AWTException, InterruptedException{
		view.setValue(view.getCircleCount());
		final JLabel label = labels.get(which);
		Point p = label.getLocationOnScreen();
		int x = p.x + label.getWidth()/2;
		int y = p.y + label.getHeight()/2;
		Robot robot = new Robot();
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		while (label.getText().equals(SValueView.CIRCLE_BLACK)) {
			Thread.sleep(10);
			
		}
		
		testCorrect(which, 0, 0, 10-which);
		
		
	}
	
	private void testCorrect(final int black, final int gray, 
			final int blue, final int white){
		/*
		 * Preconditions to ensure the test can work.
		 */
		assertTrue(gray == 0 || blue == 0);
		assertTrue(black >= 0 && gray >= 0 && blue >= 0 && white >= 0);
		assertTrue(black + gray + blue + white == view.getCircleCount());
		
		testConcrete(0, black, Color.BLACK, SValueView.CIRCLE_BLACK);
		testConcrete(black, gray, Color.GRAY, SValueView.CIRCLE_BLACK);
		testConcrete(black, blue, Color.BLUE, SValueView.CIRCLE_BLACK);
		testConcrete(black+blue+gray, white, Color.BLACK, SValueView.CIRCLE_WHITE);		
	}
	
	private void testConcrete(final int start, final int count, 
			final Color color, final String text){
		for (int i = 0; i < count; i++){
			final JLabel label = labels.get(i+start);
			assertEquals(text, label.getText());
			assertEquals(color, label.getForeground());
		}
	}
	
	@Test
	public void testSetValue(){
		testSetValueWith(5);
		testSetValueWith(7);
		
	}
	
	private void testSetValueWith(final int value){
		view.setValue(value);
		testCorrect(value, 0, 0, view.getCircleCount()-value);
	}

}
