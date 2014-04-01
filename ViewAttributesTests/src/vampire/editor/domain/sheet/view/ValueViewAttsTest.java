/*******************************************************************************
 * The tests for the view model implementation.
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
package vampire.editor.domain.sheet.view;

import static org.junit.Assert.*;

import org.junit.Test;

import vampire.editor.domain.sheet.view.MValueViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;

@SuppressWarnings("static-method")
public class ValueViewAttsTest {

	@Test
	public void testClone1() {
		MValueViewAttributes viewAtts = new MValueViewAttributes(false, true, 7, false, 18);
		ValueViewAttributes clone = viewAtts.clone();
		assertEquals(viewAtts, clone);
		assertNotSame(viewAtts, clone);
	}
	
	@Test
	public void testClone2() {
		MValueViewAttributes viewAtts = new MValueViewAttributes(true, true, 8, false, 9);
		ValueViewAttributes clone = viewAtts.clone();
		assertEquals(viewAtts, clone);
		assertNotSame(viewAtts, clone);
	}
	
	@Test
	public void testClone3() {
		MValueViewAttributes viewAtts = new MValueViewAttributes(false, false, 5, true, 17);
		ValueViewAttributes clone = viewAtts.clone();
		assertEquals(viewAtts, clone);
		assertNotSame(viewAtts, clone);
	}
	
	@Test
	public void testEqualsObject1() {
		ValueViewAttributes viewAtts1 = new MValueViewAttributes(false, true, 5, false, 10);
		ValueViewAttributes viewAtts2 = new MValueViewAttributes(false, true, 5, false, 10);
		assertEquals(viewAtts1, viewAtts2);
	}
	
	@Test
	public void testEqualsObject2() {
		ValueViewAttributes viewAtts1 = new MValueViewAttributes(true, true, 5, false, 10);
		ValueViewAttributes viewAtts2 = new MValueViewAttributes(true, true, 5, false, 10);
		assertEquals(viewAtts1, viewAtts2);
	}
	
	@Test
	public void testEqualsObject3() {
		ValueViewAttributes viewAtts1 = new MValueViewAttributes(true, false, 7, true, 30);
		ValueViewAttributes viewAtts2 = new MValueViewAttributes(true, false, 7, true, 30);
		assertEquals(viewAtts1, viewAtts2);
	}
	
	@Test
	public void testNotEqualsObject1(){
		ValueViewAttributes viewAtts1 = new MValueViewAttributes(false, false, 7, true, 30);
		ValueViewAttributes viewAtts2 = new MValueViewAttributes(true, false, 7, true, 30);
		assertFalse(viewAtts1.equals(viewAtts2));
	}
	
	@Test
	public void testNotEqualsObject2(){
		ValueViewAttributes viewAtts1 = new MValueViewAttributes(false, false, 7, true, 30);
		ValueViewAttributes viewAtts2 = new MValueViewAttributes(false, true, 7, true, 30);
		assertFalse(viewAtts1.equals(viewAtts2));
	}
	
	@Test
	public void testNotEqualsObject3(){
		ValueViewAttributes viewAtts1 = new MValueViewAttributes(false, false, 7, true, 30);
		ValueViewAttributes viewAtts2 = new MValueViewAttributes(false, false, 8, true, 30);
		assertFalse(viewAtts1.equals(viewAtts2));
	}
	
	@Test
	public void testNotEqualsObject4(){
		ValueViewAttributes viewAtts1 = new MValueViewAttributes(false, false, 7, true, 30);
		ValueViewAttributes viewAtts2 = new MValueViewAttributes(false, false, 7, false, 30);
		assertFalse(viewAtts1.equals(viewAtts2));
	}
	
	@Test
	public void testNotEqualsObject5(){
		ValueViewAttributes viewAtts1 = new MValueViewAttributes(false, false, 7, true, 30);
		ValueViewAttributes viewAtts2 = new MValueViewAttributes(true, false, 7, true, 20);
		assertFalse(viewAtts1.equals(viewAtts2));
	}
}
