package domain.sheet;

import java.util.LinkedList;
import java.util.List;

import plugin.api.domain.sheet.Nameable;
import plugin.api.domain.sheet.TraitAPI;
import plugin.api.domain.sheet.view.TraitViewAttributes;

public class Trait implements TraitAPI, Nameable{
	
	private String name;
	
	private Value value;
	
	private final List<Specialty> specialties = new LinkedList<>();
	
	private final TraitViewAttributes traitViewAttributes;

	public Trait(String name, Value value,
			TraitViewAttributes traitViewAttributes) {
		super();
		this.name = name;
		this.value = value;
		this.traitViewAttributes = traitViewAttributes;	
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
	
	@Override
	public TraitViewAttributes getTraitViewAttributes() {
		return traitViewAttributes;
	}
	
	public void addSpecialty(Specialty specialty){
		specialties.add(specialty);
	}
	
	public void removeSpecialty(Specialty specialty){
		specialties.remove(specialty);
	}
	
	public Trait clone(){
		return new Trait(name, value.clone(), traitViewAttributes);
	}
	
	
	
	
	
	

}
