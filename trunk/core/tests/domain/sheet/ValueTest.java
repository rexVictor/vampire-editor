package domain.sheet;

import static org.junit.Assert.*;

import org.junit.Test;

import vampire.editor.domain.sheet.IllegalValueException;
import vampire.editor.domain.sheet.Value;

public class ValueTest {

	@Test(expected = IllegalValueException.class)
	public void testSetValueExceptionThrown() {
		setValueWith(12, 0, 4);
		setValueWith(6,0,5);
		setValueWith(-5,5,10);
	}
	
	@Test
	public void testSetValueExceptionNotThrown() {
		setValueWith(5, 0, 10);
		setValueWith(0,0,5);
		setValueWith(10,5,10);
	}
	
	private void setValueWith(int value, int minValue, int maxValue){
		assertTrue(minValue<=maxValue);
		Value toTest = new Value(minValue, minValue, maxValue, null);
		toTest.setValue(value);
	}

}
