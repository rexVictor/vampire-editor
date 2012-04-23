package vampire.editor.importer.vcs.domain;

import java.util.ArrayList;
import java.util.List;

public class Strings {
	
	private final List<String> strings = new ArrayList<>();
	
	public void add(String string){
		strings.add(string);
	}
	
	public String get(int i){
		if (i<strings.size()) return strings.get(i);
		return null;
	}
	
	public String get(StringTypes type){
		return get(type.ordinal());
	}
	
	public List<String> getRituals(){
		return strings.subList(0xf, 0x15);
	}
	
	public List<String> getMerits(){
		return strings.subList(0x15, 0x1b);
	}
	
	public List<String> getFlaws(){
		return strings.subList(0x1b, 0x20);
	}
	
	public List<String> getOthers(){
		return strings.subList(0x20, 0x2b);		
	}
	
	public List<String> getBackgrounds(){
		return strings.subList(0x2b, 0x30);
	}
	
	public List<String> getDisciplines(){
		return strings.subList(0x30, 0x36);
	}
	
	@Override
	public String toString(){
		return strings.toString();
	}

}
