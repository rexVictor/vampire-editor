package vampire.editor.domain.sheet;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import rex.tests.helperclasses.FlatEqualTester;

import vampire.editor.domain.sheet.IllegalValueException;
import vampire.editor.domain.sheet.Value;
import vampire.editor.domain.sheet.view.ValueViewAttributes;

public class ValueTest {

	@Test(expected=IllegalValueException.class)
	public void testSetValueExceptionThrown0() {
		testSetValueWith(12, 0, 4);		
	}
	
	@Test(expected=IllegalValueException.class)
	public void testSetValueExceptionThrown1() {
		testSetValueWith(6,0,5);		
	}
	
	@Test(expected=IllegalValueException.class)
	public void testSetValueExceptionThrown2() {
		testSetValueWith(-5,5,10);
	}	
	
	@Test
	public void testSetValueExceptionNotThrown0() {
		testSetValueWith(5, 0, 10);		
	}
	
	@Test
	public void testSetValueExceptionNotThrown1() {
		testSetValueWith(0,0,5);		
	}
	
	@Test
	public void testSetValueExceptionNotThrown2() {
		testSetValueWith(10,5,10);
	}
	
	private void testSetValueWith(int value, int minValue, int maxValue){
		Value toTest = new Value(minValue, minValue, maxValue, null);
		toTest.setValue(value);
	}
	
	@Test
	public void testClone0() {
		testCloneWith(0, 1, 2, 4, new ValueViewAttributes());		
	}
	
	@Test
	public void testClone1(){
		testCloneWith(4, 5, 10, 8, new ValueViewAttributes());
	}
	
	@Test
	public void testClone2(){
		testCloneWith(0,2,2,10, new ValueViewAttributes());
	}
	
	@Test
	public void testClone3(){
		testCloneWith(0, 0, 0, 0, new ValueViewAttributes());
	}
	
	@Test
	public void testClone4(){
		testCloneWith(0, 4, 3, 10, new ValueViewAttributes());
	}
	
	@Test
	public void testCloneReflected0(){
		testCloneReflectedWith(0,0,0,10,null);
	}
	
	@Test
	public void testCloneReflected1(){
		testCloneReflectedWith(0,3,4,7,new ValueViewAttributes());
	}
	
	@Test
	public void testCloneReflected2(){
		testCloneReflectedWith(3,8,4,9,null);
	}
	
	@Test
	public void testCloneReflected3(){
		testCloneReflectedWith(4, 4, 4, 4, null);
	}
	
	
	
	private void testCloneReflectedWith(int minValue, int value, int tempValue, int maxValue, ValueViewAttributes viewAtts){
		Value test = new Value(value, minValue, maxValue, viewAtts);
		test.setTempValue(tempValue);
		List<Field> fields = Arrays.asList(test.getClass().getDeclaredFields());
		assertEquals(5, fields.size()-3); //3 Statische Felder
		Value clone = test.clone();
		assertTrue(FlatEqualTester.equalsFlats(test, clone));
		
	}
	
	private void testCloneWith(int minValue, int value, int tempValue, int maxValue, ValueViewAttributes viewAtts){
		Value test = new Value(value, minValue, maxValue, viewAtts);
		test.setTempValue(tempValue);
		Value clone = test.clone();
		assertEquals(test.getMaxValue(), clone.getMaxValue());
		assertEquals(test.getMinValue(), clone.getMinValue());
		assertEquals(test.getValue(), clone.getValue());
		assertEquals(test.getTempValue(), clone.getTempValue());
		assertEquals(test.getViewAtts(), clone.getViewAtts());
		assertNotSame(test.getViewAtts(), clone.getViewAtts());
		assertNotSame(test, clone);
	}
	
	@Test
	public void testEqualsTrueExpected0() {
		testEqualsTrueExpectedWith(0, 2, 5, 10, new ValueViewAttributes());		
	}
	
	@Test
	public void testEqualsTrueExpected1(){
		testEqualsTrueExpectedWith(0, 3, 5, 7, new ValueViewAttributes());
	}
	
	@Test
	public void testEqualsTrueExpected2(){
		testEqualsTrueExpectedWith(5, 6, 8, 6, new ValueViewAttributes());
	}
	
	@Test
	public void testEqualsFalseExpected0(){
		testEqualsFalseExpectedWith(0, 5, 4, 10, new ValueViewAttributes(), 
				0, 5, 4, 11, new ValueViewAttributes());
	}
	
	@Test
	public void testEqualsFalseExpected1(){
		testEqualsFalseExpectedWith(0, 5, 4, 10, new ValueViewAttributes(), 
				0, 5, 6, 10, new ValueViewAttributes());
	}
	
	@Test
	public void testEqualsFalseExpected2(){
		testEqualsFalseExpectedWith(0, 5, 4, 10, new ValueViewAttributes(), 
				0, 7, 4, 10, new ValueViewAttributes());
	}
	
	@Test
	public void testEqualsFalseExpected3(){
		testEqualsFalseExpectedWith(0, 5, 4, 10, new ValueViewAttributes(), 
				1, 5, 4, 10, new ValueViewAttributes());
	}
	
	
	
	private void testEqualsTrueExpectedWith(int minValue, int value, int tempValue, int maxValue, ValueViewAttributes viewAtts){
		Value toTest = new Value(value, minValue, maxValue, viewAtts);
		toTest.setTempValue(tempValue);
		Value another = new Value(value, minValue, maxValue, viewAtts);
		another.setTempValue(tempValue);
		assertEquals(toTest, another);
	}
	
	private void testEqualsFalseExpectedWith(int minValue1, int value1, 
			int tempValue1, int maxValue1, ValueViewAttributes viewAtts1,
			int minValue2, int value2, int tempValue2, int maxValue2, ValueViewAttributes viewAtts2){
		
		Value first = new Value(value1, minValue1, maxValue1, viewAtts1);
		first.setTempValue(tempValue1);
		
		Value second = new Value(value2, minValue2, maxValue2, viewAtts2);
		second.setTempValue(tempValue2);
		
		assertFalse(first.equals(second));
		
		
	}
	
	

}
