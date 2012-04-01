package domain.sheet;

import plugin.api.domain.sheet.view.DataViewAttributes;

public class Category extends Data<SubCategory>{
	
	public Category(DataViewAttributes attributes) {
		super(attributes);
		// TODO Auto-generated constructor stub
	}

	public Category clone(){
		return (Category) super.clone();
	}

}
