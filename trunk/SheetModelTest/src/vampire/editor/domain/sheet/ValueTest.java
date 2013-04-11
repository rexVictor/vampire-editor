/*******************************************************************************
 * The tests for the sheet model implementation.
 * Copyright (C) 2013 Matthias Reimchen
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *    
 * For further information see https://code.google.com/p/vampire-editor/ or write a
 * mail to development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.domain.sheet;

import static org.junit.Assert.*;

import org.junit.Test;

import vampire.editor.domain.sheet.IllegalValueException;
import vampire.editor.domain.sheet.MValue;

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
		MValue toTest = new MValue(minValue, minValue, maxValue);
		toTest.setValue(value);
	}
	
	@Test
	public void testClone0() {
		testCloneWith(0, 1, 2, 4);		
	}
	
	@Test
	public void testClone1(){
		testCloneWith(4, 5, 10, 8);
	}
	
	@Test
	public void testClone2(){
		testCloneWith(0,2,2,10);
	}
	
	@Test
	public void testClone3(){
		testCloneWith(0, 0, 0, 0);
	}
	
	@Test
	public void testClone4(){
		testCloneWith(0, 4, 3, 10);
	}
	
	private void testCloneWith(int minValue, int value, int tempValue, int maxValue){
		MValue test = new MValue(value, minValue, maxValue);
		test.setTempValue(tempValue);
		MValue clone = test.clone();
		assertEquals(test.getMaxValue(), clone.getMaxValue());
		assertEquals(test.getMinValue(), clone.getMinValue());
		assertEquals(test.getValue(), clone.getValue());
		assertEquals(test.getTempValue(), clone.getTempValue());
		assertNotSame(test, clone);
	}
	
	@Test
	public void testEqualsTrueExpected0() {
		testEqualsTrueExpectedWith(0, 2, 5, 10);
	}
	
	@Test
	public void testEqualsTrueExpected1(){
		testEqualsTrueExpectedWith(0, 3, 5, 7);
	}
	
	@Test
	public void testEqualsTrueExpected2(){
		testEqualsTrueExpectedWith(5, 6, 8, 6);
	}
	
	@Test
	public void testEqualsFalseExpected0(){
		testEqualsFalseExpectedWith(0, 5, 4, 10, 0, 5, 4, 11);
	}
	
	@Test
	public void testEqualsFalseExpected1(){
		testEqualsFalseExpectedWith(0, 5, 4, 10, 0, 5, 6, 10);
	}
	
	@Test
	public void testEqualsFalseExpected2(){
		testEqualsFalseExpectedWith(0, 5, 4, 10, 0, 7, 4, 10);
	}
	
	@Test
	public void testEqualsFalseExpected3(){
		testEqualsFalseExpectedWith(0, 5, 4, 10, 1, 5, 4, 10);
	}
	
	
	
	private void testEqualsTrueExpectedWith(int minValue, int value, int tempValue, int maxValue){
		MValue toTest = new MValue(value, minValue, maxValue);
		toTest.setTempValue(tempValue);
		MValue another = new MValue(value, minValue, maxValue);
		another.setTempValue(tempValue);
		assertEquals(toTest, another);
	}
	
	private void testEqualsFalseExpectedWith(int minValue1, int value1, 
			int tempValue1, int maxValue1,
			int minValue2, int value2, int tempValue2, int maxValue2){
		
		MValue first = new MValue(value1, minValue1, maxValue1);
		first.setTempValue(tempValue1);
		
		MValue second = new MValue(value2, minValue2, maxValue2);
		second.setTempValue(tempValue2);
		
		assertFalse(first.equals(second));
		
		
	}
	
	

}
