package vampire.editor.gui.swing.view;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.BeforeClass;
import org.junit.Test;

import vampire.editor.plugin.fullapi.sheet.view.IValueViewAttributes;

public class SValueViewTest {
	
	private static final Color EMPTY_CIRCLE_COLOR = Color.BLACK;
	
	private static final Color FILLED_TEMP_CIRCLE_COLOR_GREATER_VALUE = Color.BLUE;
	
	private static final Color FILLED_TEMP_CIRCLE_COLOR_SMALLER_VALUE = Color.GRAY;
	
	private static final Color FILLED_CIRCLE = Color.BLACK;
	
	private static Robot robot;
	
	private static JFrame frame = new JFrame();
	
	private static SValueView view;
	
	private static List<JLabel> circles = new ArrayList<>();
	
	@BeforeClass
	public static void initialize() throws AWTException{
		robot = new Robot();
	}
	
	private static void setupValueView(IValueViewAttributes atts) throws InterruptedException{
		view = new SValueView(atts);
		JPanel panel = view.getView();
		Component[] components = panel.getComponents();
		for (Component component : components){
			if (component instanceof JLabel) {
				JLabel label = (JLabel) component;
				String text = label.getText();
				if (text.equals(SValueView.CIRCLE_BLACK) || text.equals(SValueView.CIRCLE_WHITE)){
					circles.add(label);
				}
			}
		}
		frame = new JFrame();
		frame.add(view.getView());
		frame.setSize(200, 300);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.setLocation(200, 300);
		Thread.sleep(100);
	}

	@Test
	public void test() throws InterruptedException {
		testSetValueWith(2, 3);
	}
	
	
	public void testSetValueWith(int value, int tempValue) throws InterruptedException{
		setupValueView(new ValueViewAttributesTestImplementation());
		JLabel clickValue = circles.get(value-1);
		JLabel clickTempValue = circles.get(tempValue-1);
		Point point = clickValue.getLocationOnScreen();
		Dimension dimension = clickValue.getSize();
		point.x = point.x+dimension.width/2;
		point.y = point.y+dimension.height/2;
		robot.mouseMove(point.x, point.y);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		point = clickTempValue.getLocationOnScreen();
		dimension = clickTempValue.getSize();
		point.x = point.x+dimension.width/2;
		point.y = point.y+dimension.height/2;
		robot.mouseMove(point.x, point.y);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(100);
		if (value<tempValue){
			for (int i = 0; i < value; i++) {
				JLabel current = circles.get(i);
				assertEquals(current.getForeground(), FILLED_CIRCLE);
				assertEquals(current.getText(), SValueView.CIRCLE_BLACK);
			}
			for (int i = value; i < tempValue; i++) {
				JLabel current = circles.get(i);
				assertEquals(current.getForeground(), FILLED_TEMP_CIRCLE_COLOR_GREATER_VALUE);
				assertEquals(current.getText(), SValueView.CIRCLE_BLACK);
			}
			for (int i = tempValue; i < circles.size(); i++){
				JLabel current = circles.get(i);
				assertEquals(current.getForeground(), EMPTY_CIRCLE_COLOR);
				assertEquals(current.getText(), SValueView.CIRCLE_WHITE);
			}
		}
		
		
	}

}
