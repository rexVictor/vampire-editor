package vampire.editor.domain.sheet;

import vampire.editor.plugin.fullapi.sheet.IMerit;

public class Merit implements IMerit {
	
	private int cost;
	
	private String name;

	public Merit() {
		super();
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public void setCost(int cost) {
		this.cost = cost;
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
	public Merit clone(){
		Merit clone = new Merit();
		clone.cost = cost;
		clone.name = name;
		return clone;
	}
	
	
	
	

}
