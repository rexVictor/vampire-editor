package vampire.editor.plugin.freebiecalculator.application.events;

public class FreebieChangeEvent {
	
	private final int formerFreebies;
	
	private final int newFreebies;
	
	public FreebieChangeEvent(int formerFreebies, int newFreebies) {
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
