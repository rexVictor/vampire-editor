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

import java.awt.Font;

import org.junit.Test;

import vampire.editor.plugin.api.domain.sheet.view.BloodPoolViewAttributes;

@SuppressWarnings("static-method")
public class BloodPoolViewAttsTest {
	
	private static Font FONT = new Font(Font.SANS_SERIF, 0, 10);
	
	private static Font FONT2 = new Font(Font.SANS_SERIF, 0, 13);
	
	@Test
	public void testClone1() {
		MBloodPoolViewAttributes viewAtts = new MBloodPoolViewAttributes(FONT, 5);
		BloodPoolViewAttributes clone = viewAtts.clone();
		assertEquals(viewAtts, clone);
		assertNotSame(viewAtts, clone);
	}
	
	@Test
	public void testClone2() {
		MBloodPoolViewAttributes viewAtts = new MBloodPoolViewAttributes(FONT2, 5);
		BloodPoolViewAttributes clone = viewAtts.clone();
		assertEquals(viewAtts, clone);
		assertNotSame(viewAtts, clone);
	}
	
	@Test
	public void testClone3() {
		BloodPoolViewAttributes viewAtts = new MBloodPoolViewAttributes(FONT, 9);
		BloodPoolViewAttributes clone = viewAtts.clone();
		assertEquals(viewAtts, clone);
		assertNotSame(viewAtts, clone);
	}
	
	@Test
	public void testEqualsObject1() {
		MBloodPoolViewAttributes viewAtts1 = new MBloodPoolViewAttributes(FONT, 5);
		MBloodPoolViewAttributes viewAtts2 = new MBloodPoolViewAttributes(FONT, 5);
		assertEquals(viewAtts1, viewAtts2);
	}
	
	@Test
	public void testEqualsObject2() {
		MBloodPoolViewAttributes viewAtts1 = new MBloodPoolViewAttributes(FONT2, 5);
		MBloodPoolViewAttributes viewAtts2 = new MBloodPoolViewAttributes(FONT2, 5);
		assertEquals(viewAtts1, viewAtts2);
	}
	
	@Test
	public void testEqualsObject3() {
		MBloodPoolViewAttributes viewAtts1 = new MBloodPoolViewAttributes(FONT2, 9);
		MBloodPoolViewAttributes viewAtts2 = new MBloodPoolViewAttributes(FONT2, 9);
		assertEquals(viewAtts1, viewAtts2);
	}
	
	@Test
	public void testNotEqualsObject1(){
		MBloodPoolViewAttributes viewAtts1 = new MBloodPoolViewAttributes(FONT, 5);
		MBloodPoolViewAttributes viewAtts2 = new MBloodPoolViewAttributes(FONT2, 5);
		assertFalse(viewAtts1.equals(viewAtts2));
	}
	
	@Test
	public void testNotEqualsObject2(){
		MBloodPoolViewAttributes viewAtts1 = new MBloodPoolViewAttributes(FONT, 5);
		MBloodPoolViewAttributes viewAtts2 = new MBloodPoolViewAttributes(FONT, 9);
		assertFalse(viewAtts1.equals(viewAtts2));
	}
	
	@Test
	public void testNotEqualsObject3(){
		MBloodPoolViewAttributes viewAtts1 = new MBloodPoolViewAttributes(FONT, 5);
		MBloodPoolViewAttributes viewAtts2 = new MBloodPoolViewAttributes(FONT2, 8);
		assertFalse(viewAtts1.equals(viewAtts2));
	}


}
