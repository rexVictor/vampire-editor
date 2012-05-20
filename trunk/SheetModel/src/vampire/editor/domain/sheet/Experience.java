package vampire.editor.domain.sheet;

import java.util.ArrayList;
import java.util.List;

public class Experience {
	
	private int total;
	
	private int spent;
	
	private final List<String> description = new ArrayList<>();

	public Experience() {
		super();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSpent() {
		return spent;
	}

	public void setSpent(int spent) {
		this.spent = spent;
	}

	public List<String> getDescription() {
		return description;
	}	
	

}
