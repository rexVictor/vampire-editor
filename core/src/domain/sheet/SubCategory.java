package domain.sheet;

import plugin.api.domain.sheet.view.DataViewAttributes;

public class SubCategory extends Data<Trait>{
	
	public SubCategory(DataViewAttributes attributes) {
		super(attributes);
		// TODO Auto-generated constructor stub
	}

	public SubCategory clone(){
		return (SubCategory) super.clone();
	}

}
