/*******************************************************************************
 * Vampire Editor Plugin: Generation Bloodpool Synchronizer.
 * Copyright (C) 2013  Matthias Johannes Reimchen
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Further information can be obtained at
 * https://code.google.com/p/vampire-editor/ or via mail:
 * Matthias Johannes Reimchen
 * development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.plugin.generationbloodpoolsynchronizer.application;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import vampire.editor.plugin.api.application.sheet.controller.BloodPoolControllerAPI;
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
	
	public Synchronizer(BloodPoolControllerAPI bloodPoolController) {
		super();
		this.bloodPoolController = bloodPoolController;
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
