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

import vampire.editor.plugin.api.domain.sheet.view.MeritViewAttributes;

public class MeritViewAttsTest {

	private static Font FONT = new Font(Font.SANS_SERIF, 0, 10);
	
	private static Font FONT2 = new Font(Font.SANS_SERIF, 0, 13);
	
	@Test
	public void testClone1() {
		MMeritViewAttributes viewAtts = new MMeritViewAttributes(FONT);
		MeritViewAttributes clone = viewAtts.clone();
		assertEquals(viewAtts, clone);
		assertNotSame(viewAtts, clone);
	}
	
	@Test
	public void testClone2() {
		MMeritViewAttributes viewAtts = new MMeritViewAttributes(FONT2);
		MeritViewAttributes clone = viewAtts.clone();
		assertEquals(viewAtts, clone);
		assertNotSame(viewAtts, clone);
	}
	
	@Test
	public void testEqualsObject1() {
		MeritViewAttributes viewAtts1 = new MMeritViewAttributes(FONT);
		MeritViewAttributes viewAtts2 = new MMeritViewAttributes(FONT);
		assertEquals(viewAtts1, viewAtts2);
	}
	
	@Test
	public void testEqualsObject2() {
		MeritViewAttributes viewAtts1 = new MMeritViewAttributes(FONT2);
		MeritViewAttributes viewAtts2 = new MMeritViewAttributes(FONT2);
		assertEquals(viewAtts1, viewAtts2);
	}
	
	@Test
	public void testNotEqualsObject1(){
		MeritViewAttributes viewAtts1 = new MMeritViewAttributes(FONT);
		MeritViewAttributes viewAtts2 = new MMeritViewAttributes(FONT2);
		assertFalse(viewAtts1.equals(viewAtts2));
	}
}
