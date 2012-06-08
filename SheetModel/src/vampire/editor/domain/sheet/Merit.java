package vampire.editor.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.MeritAPI;

public class Merit implements MeritAPI {
	
	private int cost;
	
	private String name;

	public Merit() {
		super();
	}

	@Override
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Merit clone(){
		Merit clone = new Merit();
		clone.cost = cost;
		clone.name = name;
		return clone;
	}
	
	
	
	

}
