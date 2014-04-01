/*******************************************************************************
 * Vampire Editor Model Implementation.
 * Copyright (C) 2013  Matthias Johannes Reimchen
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
 * Further information can be obtained at
 * https://code.google.com/p/vampire-editor/ or via mail:
 * Matthias Johannes Reimchen
 * development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.domain.sheet;

import java.util.LinkedList;
import java.util.List;

import vampire.editor.plugin.api.domain.sheet.Trait;
import vampire.editor.plugin.api.domain.sheet.Value;

/**
 * A trait is a object containing the name of a trait and its Value Object. <br>
 * Examples for traits are: Strength, Intelligence.
 * @author rex_victor
 *
 */
public class MTrait implements Trait{
	
	private String name;
	
	private Value value;
	
	private final List<Specialty> specialties;
	
	public MTrait(){
		specialties = new LinkedList<>();
	}

	public MTrait(String name, Value value) {
		this(name,value, new LinkedList<Specialty>());
	}
	

	private MTrait(String name, Value value, List<Specialty> specialties) {
		super();
		this.name = name;
		this.value = value;
		this.specialties = specialties;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Value getValue() {
		return value;
	}
	
	public void addSpecialty(Specialty specialty){
		specialties.add(specialty);
	}
	
	public void removeSpecialty(Specialty specialty){
		specialties.remove(specialty);
	}
	
	@Override
	public Trait clone(){
		return new MTrait(name, value.clone(), new LinkedList<>(specialties));
	}
	
	@Override
	public String toString(){
		String toReturn = ""; //$NON-NLS-1$
		toReturn = toReturn+name+" : "+value.toString(); //$NON-NLS-1$
		return toReturn;
	}

	@Override
	public void setValue(Value value) {
		this.value = value;
	}


}
