package vampire.editor.domain.sheet;

import java.util.LinkedList;
import java.util.List;

import vampire.editor.plugin.fullapi.sheet.ISpecialty;
import vampire.editor.plugin.fullapi.sheet.ITrait;
import vampire.editor.plugin.fullapi.sheet.view.ITraitViewAttributes;

public class Trait implements ITrait{
	
	private String name;
	
	private Value value;
	
	private final List<ISpecialty> specialties = new LinkedList<>();
	
	private ITraitViewAttributes traitViewAttributes;
	
	public Trait(){
		
	}

	public Trait(String name, Value value,
			ITraitViewAttributes viewAtts) {
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
	
	@Override
	public void setViewAtts(ITraitViewAttributes attributes) {
		if (this.traitViewAttributes == null)
			this.traitViewAttributes = attributes;
	}
	
	@Override
	public void addSpecialty(ISpecialty specialty){
		specialties.add(specialty);
	}
	
	@Override
	public void removeSpecialty(ISpecialty specialty){
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
	
	
	
	
	
	

}
