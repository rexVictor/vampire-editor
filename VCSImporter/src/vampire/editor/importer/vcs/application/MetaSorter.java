package vampire.editor.importer.vcs.application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MetaSorter implements Comparator<String>{
	
	private final List<String> order;
	
	public MetaSorter(){
		order = new ArrayList<>();
		loadOrder();
	}
	
	public MetaSorter(List<String> order){
		this.order = order;
	}
	
	private void loadOrder(){
		order.add("name");
		order.add("player");
		order.add("creator");
		
		order.add("chronicle");
		order.add("haven");
		order.add("clan");
		
		order.add("sect");
		order.add("nature");
		order.add("demeanor");
		
		order.add("concept");
		order.add("generation");
		System.out.println(order);
	}
	
	

	@Override
	public int compare(String o1, String o2) {
		int pos1 = order.indexOf(o1);
		int pos2 = order.indexOf(o2);
		if (pos1 == pos2) {
			return 0;
		}
		if (pos1 > pos2) {
			return 1;
		}
		else {
			return -1;
		}
		
	}

}
