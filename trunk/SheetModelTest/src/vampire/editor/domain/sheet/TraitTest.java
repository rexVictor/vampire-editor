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

import vampire.editor.plugin.api.domain.sheet.Trait;
import vampire.editor.plugin.api.domain.sheet.Value;

@SuppressWarnings("static-method")
public class TraitTest {
	
	
	@Test
	public void testClone1() {
		Value value = new MValue(2, 1, 7);
		value.setTempValue(6);
		Trait trait = new MTrait("name1", value); //$NON-NLS-1$
		Trait clone = trait.clone();
		Value clonedValue = clone.getValue();
		assertEquals(value, clonedValue);
		assertNotSame(value, clonedValue);
		assertEquals(trait.getName(), clone.getName());
		assertNotSame(trait, clone);
	}
	
	@Test
	public void testClone2() {
		MValue value = new MValue(4, 3, 9);
		value.setTempValue(9);
		Trait trait = new MTrait("name2", value); //$NON-NLS-1$
		Trait clone = trait.clone();
		Value clonedValue = clone.getValue();
		assertEquals(value, clonedValue);
		assertNotSame(value, clonedValue);
		assertEquals(trait.getName(), clone.getName());
		assertNotSame(trait, clone);
	}
	
	@Test
	public void testClone3() {
		MValue value = new MValue(0, 0, 10);
		value.setTempValue(9);
		Trait trait = new MTrait("name3", value); //$NON-NLS-1$
		Trait clone = trait.clone();
		Value clonedValue = clone.getValue();
		assertEquals(value, clonedValue);
		assertNotSame(value, clonedValue);
		assertEquals(trait.getName(), clone.getName());
		assertNotSame(trait, clone);
	}

}
