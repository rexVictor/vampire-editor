package vampire.editor.domain.sheet;

import java.util.LinkedList;
import java.util.List;

import vampire.editor.plugin.api.domain.sheet.TraitAPI;

public class Trait implements TraitAPI{
	
	private String name;
	
	private Value value;
	
	private final List<Specialty> specialties;
	
	public Trait(){
		specialties = new LinkedList<>();
	}

	public Trait(String name, Value value) {
		this(name,value, new LinkedList<Specialty>());			
	}
	
	
	
	

	private Trait(String name, Value value, List<Specialty> specialties) {
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
		return new Trait(name, value.clone(), new LinkedList<>(specialties));
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
