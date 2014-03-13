package vampire.editor.plugin.freebiecalculator.domain;

import java.util.LinkedList;
import java.util.List;

public class FreePoints {
	
	private final List<Integer> attributes = new LinkedList<>();
	
	private final List<Integer> abilities = new LinkedList<>();

	public List<Integer> getAttributes() {
		return attributes;
	}

	public List<Integer> getAbilities() {
		return abilities;
	}

}
