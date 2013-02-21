package vampire.editor.sheetloader.application.exporter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ProtoExport {
	
	private final List<Map<String, Object>> protoFile = new LinkedList<>();
	
	private final Map<Integer, Boolean> alreadyInserted = new HashMap<>();
	
	private int id = -1;
	
	private final boolean mapid;
	
	public ProtoExport(boolean mapid){
		this.mapid = mapid;
		if(mapid) id = 0;
	}
	
	public ProtoExport(){
		this(false);
	}
	
	public int add(Map<String, Object> mapEntry){
		if (mapid){
			mapEntry.put("mapid", id);
			id++;
		}
		Integer ownId = (Integer) mapEntry.get("id");
		if (ownId != null){
			boolean alreadyIn = alreadyInserted.containsKey(ownId);
			if (alreadyIn)
				return id;
			else alreadyInserted.put(ownId, true);
		}
		protoFile.add(mapEntry);
		return id-1;
	}
	
	public List<Map<String, Object>> getList(){
		return protoFile;
	}

}
