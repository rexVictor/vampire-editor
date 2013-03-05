package vampire.editor.plugin.generationbloodpoolsynchronizer.application;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import vampire.editor.plugin.api.application.sheet.controller.BloodPoolControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MetaEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MetaEntryEventAPI;
import vampire.editor.plugin.api.application.sheet.events.MetaEntryListener;

public class Synchronizer implements MetaEntryListener{
	
	private static final Map<Integer, Integer> generationBloodPoolMaxValueMap;
	
	static{
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 1; i <= 4; i++){
			map.put(i, 50);
		}
		map.put(5, 40);
		map.put(6, 30);
		map.put(7, 20);
		for(int i = 8; i <= 13; i++){
			map.put(i, 15-i+8);
		}
		for(int i = 14; i <= 15; i++){
			map.put(i, 10);
		}
		generationBloodPoolMaxValueMap = Collections.unmodifiableMap(map);
	}
	
	private final BloodPoolControllerAPI bloodPoolController;
	
	public Synchronizer(BloodPoolControllerAPI bloodPoolController,
			MetaEntryControllerAPI generation) {
		super();
		this.bloodPoolController = bloodPoolController;
		generation.addMetaEntryListener(this);
	}

	@Override
	public void valueChanged(MetaEntryEventAPI event) {
		String generationString = event.getNewValue();
		try{
			int generation = Integer.parseInt(generationString);
			if (1 <= generation && generation <= 15){
				Integer newMaxValue = generationBloodPoolMaxValueMap.get(generation);
				if (newMaxValue != null){
					bloodPoolController.setMaxValue(newMaxValue);
				}
			}
		}
		catch(NumberFormatException e){}
	}

	@Override
	public void keyChanged(MetaEntryEventAPI event) {}
	
	
	

}
