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

import vampire.editor.plugin.api.domain.sheet.Merit;

@SuppressWarnings("static-method")
public class MeritTest {

	@Test
	public void testClone1() {
		Merit merit = new MMerit();
		merit.setCost(5);
		merit.setName("merit1"); //$NON-NLS-1$
		Merit clone = merit.clone();
		assertEquals(merit.getName(), clone.getName());
		assertEquals(merit.getCost(), clone.getCost());
		assertNotSame(merit, clone);
	}
	
	@Test
	public void testClone2() {
		Merit merit = new MMerit();
		merit.setCost(8);
		merit.setName("merit2"); //$NON-NLS-1$
		Merit clone = merit.clone();
		assertEquals(merit.getName(), clone.getName());
		assertEquals(merit.getCost(), clone.getCost());
		assertNotSame(merit, clone);
	}
	
	@Test
	public void testClone3() {
		Merit merit = new MMerit();
		merit.setCost(-4);
		merit.setName("merit3"); //$NON-NLS-1$
		Merit clone = merit.clone();
		assertEquals(merit.getName(), clone.getName());
		assertEquals(merit.getCost(), clone.getCost());
		assertNotSame(merit, clone);
	}

}
