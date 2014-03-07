package vampire.editor.plugin.sheetsummary.application;

public class SheetPointsController {
	
	private CategoryPointsController attributes;
	
	private CategoryPointsController abilities;
	
	private SubCategoryPointsController virtues;
	
	private SubCategoryPointsController disciplines;
	
	private SubCategoryPointsController backgrounds;

	public SheetPointsController(CategoryPointsController attributes,
			CategoryPointsController abilities,
			SubCategoryPointsController virtues,
			SubCategoryPointsController disciplines,
			SubCategoryPointsController backgrounds) {
		super();
		this.attributes = attributes;
		this.abilities = abilities;
		this.virtues = virtues;
		this.disciplines = disciplines;
		this.backgrounds = backgrounds;
	}
	
	public void update(){
		attributes.update();
		abilities.update();
		virtues.update();
		disciplines.update();
		backgrounds.update();
	}

}
