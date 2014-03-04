package vampire.editor.plugin.freebiecalculator.domain;

public class FreebieConfig {
	
	private FreebieCategoryConfig attributes;
	
	private FreebieCategoryConfig abilities;
	
	private FreebieSubCategoryConfig disciplines;
	
	private FreebieSubCategoryConfig backgrounds;
	
	private FreebieSubCategoryConfig virtues;
	
	private int freebies;
	
	private DependentTraitConfig path;
	
	private DependentTraitConfig willpower;

	public FreebieCategoryConfig getAttributes() {
		return attributes;
	}

	public void setAttributes(FreebieCategoryConfig attributes) {
		this.attributes = attributes;
	}

	public FreebieCategoryConfig getAbilities() {
		return abilities;
	}

	public void setAbilities(FreebieCategoryConfig abilities) {
		this.abilities = abilities;
	}

	public FreebieSubCategoryConfig getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(FreebieSubCategoryConfig disciplines) {
		this.disciplines = disciplines;
	}

	public FreebieSubCategoryConfig getBackgrounds() {
		return backgrounds;
	}

	public void setBackgrounds(FreebieSubCategoryConfig backgrounds) {
		this.backgrounds = backgrounds;
	}

	public FreebieSubCategoryConfig getVirtues() {
		return virtues;
	}

	public void setVirtues(FreebieSubCategoryConfig virtues) {
		this.virtues = virtues;
	}

	public int getFreebies() {
		return freebies;
	}

	public void setFreebies(int freebies) {
		this.freebies = freebies;
	}

	public DependentTraitConfig getPath() {
		return path;
	}

	public void setPath(DependentTraitConfig path) {
		this.path = path;
	}

	public DependentTraitConfig getWillpower() {
		return willpower;
	}

	public void setWillpower(DependentTraitConfig willpower) {
		this.willpower = willpower;
	}
	
	
	
}
