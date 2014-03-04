package vampire.editor.plugin.freebiecalculator.application.events;

public class FreebieEvent {
	
	private final int formerFreebies;
	
	private final int newFreebies;

	public FreebieEvent(int formerFreebies, int newFreebies) {
		super();
		this.formerFreebies = formerFreebies;
		this.newFreebies = newFreebies;
	}

	public int getFormerFreebies() {
		return formerFreebies;
	}

	public int getNewFreebies() {
		return newFreebies;
	}
	
	

}
