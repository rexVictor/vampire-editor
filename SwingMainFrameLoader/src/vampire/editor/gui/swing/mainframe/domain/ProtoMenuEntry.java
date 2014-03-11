package vampire.editor.gui.swing.mainframe.domain;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProtoMenuEntry {
	
	private String name;
	
	private List<ProtoMenuEntry> entries = new LinkedList<>();
	
	private String icon;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProtoMenuEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<ProtoMenuEntry> entries) {
		this.entries = entries;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "ProtoMenuEntry [name=" + name + ", entries=" + entries
				+ ", icon=" + icon + "]";
	}
	
	@JsonIgnore
	public List<List<String>> toStringArray(){
		
		List<List<String>> list = new LinkedList<>();
		if (entries.size() == 0){
			list.add(new LinkedList<String>());
		}
		for (int i = 0; i < entries.size(); i++){
			ProtoMenuEntry entry = entries.get(i);
			List<List<String>> subList = entry.toStringArray();
			for (List<String> path : subList){
				list.add(path);
			}
		}
		for (List<String> subList : list){
			subList.add(0, name);
		}
		return list;
	}
	
}
