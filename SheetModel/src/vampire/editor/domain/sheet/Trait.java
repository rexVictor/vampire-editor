package vampire.editor.domain.sheet;

import java.util.LinkedList;
import java.util.List;

import vampire.editor.domain.sheet.view.TraitViewAttributes;
import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.fullapi.sheet.view.ITraitViewAttributes;

public class Trait implements TraitAPI{
	
	private String name;
	
	private Value value;
	
	private final List<Specialty> specialties = new LinkedList<>();
	
	private TraitViewAttributes traitViewAttributes;
	
	public Trait(){
		
	}

	public Trait(String name, Value value,
			TraitViewAttributes viewAtts) {
		super();
		this.name = name;
		this.value = value;
		this.traitViewAttributes = viewAtts;	
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
	public ITraitViewAttributes getViewAtts() {
		return traitViewAttributes;
	}
	
	public void setViewAtts(TraitViewAttributes attributes) {
		if (this.traitViewAttributes == null)
			this.traitViewAttributes = attributes;
	}
	
	public void addSpecialty(Specialty specialty){
		specialties.add(specialty);
	}
	
	public void removeSpecialty(Specialty specialty){
		specialties.remove(specialty);
	}
	
	@Override
	public Trait clone(){
		return new Trait(name, value.clone(), traitViewAttributes);
	}
	
	@Override
	public String toString(){
		String toReturn = "";
		toReturn = toReturn+name+" : "+value.toString();
		return toReturn;
	}

	public void setValue(Value value) {
		this.value = value;
	}
	
	
	
	
	
	

}
