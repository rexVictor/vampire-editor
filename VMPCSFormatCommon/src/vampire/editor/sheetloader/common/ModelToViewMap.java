/*******************************************************************************
 * Vampire Editor Importer: VMPCS: Commons.
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
package vampire.editor.sheetloader.common;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vampire.editor.domain.sheet.*;

public class ModelToViewMap {

	private final Map<Integer, Integer> metaMap = new HashMap<>();
	
	private final Map<Integer, Integer> categoryMap = new HashMap<>();
	
	private final Map<Integer, Integer> subcategoryMap = new HashMap<>();
	
	private final Map<Integer, Integer> traitMap = new HashMap<>();
	
	private final Map<Integer, Integer> valueMap = new HashMap<>();
	
	private final Map<Integer, Integer> bloodPoolMap = new HashMap<>();
	
	private final Map<Integer, Integer> healthEntryMap = new HashMap<>();
	
	private final Map<Integer, Integer> healthMap = new HashMap<>();
	
	private final Map<Integer, Integer> meritEntryMap = new HashMap<>();
	
	private final Map<Integer, Integer> meritMap = new HashMap<>();
	
	private final Map<Class<?>, Map<Integer, Integer>> allMaps = new HashMap<>();
	
	public ModelToViewMap(){
		allMaps.put(MetaEntry.class, metaMap);
		allMaps.put(Category.class, categoryMap);
		allMaps.put(SubCategory.class, subcategoryMap);
		allMaps.put(Trait.class, traitMap);
		allMaps.put(Value.class, valueMap);
		allMaps.put(BloodPool.class, bloodPoolMap);
		allMaps.put(HealthEntry.class, healthEntryMap);
		allMaps.put(Health.class, healthMap);
		allMaps.put(Merit.class, meritEntryMap);
		allMaps.put(Merits.class, meritMap);
	}
	
	public <V> int getView(Class<V> clazz, int model){
		return allMaps.get(clazz).get(model);
	}
	
	public void putHealthPair(int model, int view){
		healthMap.put(model, view);
	}
	
	public void putMeritEntryPair(int model, int view){
		meritEntryMap.put(model, view);
	}
	
	public void putMeritPair(int model, int view){
		meritMap.put(model, view);
	}
	
	public void putHealthEntryPair(int model, int view){
		healthEntryMap.put(model, view);
	}
	
	
	public void putMetaPair(int model, int view){
		metaMap.put(model, view);
	}
	
	public int getMetaView(int model){
		return metaMap.get(model);
	}
	
	public void putCategoryPair(int model, int view){
		categoryMap.put(model, view);
	}
	
	public int getHealthEntryView(int model){
		return healthEntryMap.get(model);
	}
	
	public int getHealthView(int model){
		return healthMap.get(model);
	}
	
	public int getMeritsView(int model){
		return meritMap.get(model);
	}
	
	public int getMeritEntryView(int model){
		return meritEntryMap.get(model);
	}
	
	public int getCategoryView(int model){
		return categoryMap.get(model);
	}
	
	public void putSubcategoryPair(int model, int view){
		subcategoryMap.put(model, view);
	}
	
	public int getSubcategoryView(int model){
		return subcategoryMap.get(model);
	}
	
	public void putTraitPair(int model, int view){
		traitMap.put(model, view);
	}
	
	public int getTraitView(int model){
		return traitMap.get(model);
	}
	
	public void putValuePair(int model, int view){
		valueMap.put(model, view);
	}
	
	public void putBloodPoolPair(int model, int view){
		bloodPoolMap.put(model, view);
	}
	
	public int getBloodPoolView(int model){
		return bloodPoolMap.get(model);
	}
	
	public int getValueView(int model){
		return valueMap.get(model);
	}
	
	public void putPair(Class<?> clazz, int model, int view){
		allMaps.get(clazz).put(model, view);
	}
	
	@Override
	public String toString(){
		return metaMap.toString();
	}
	
	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<>();
		map.put("meta", makeMap(metaMap));
		map.put("subcategories", makeMap(subcategoryMap));
		map.put("categories", makeMap(categoryMap));
		map.put("traits", makeMap(traitMap));
		map.put("values", makeMap(valueMap));
		map.put("bloodpool", makeMap(bloodPoolMap).get(0));
		map.put("healthentries", makeMap(healthEntryMap));
		map.put("health", makeMap(healthMap).get(0));
		map.put("meritentries", makeMap(meritEntryMap));
		map.put("merits", makeMap(meritMap));
		return map;
	}
	
	private List<Map<String, Object>> makeMap(Map<Integer, Integer> map){
		List<Map<String, Object>> list = new LinkedList<>();
		Set<Integer> models = map.keySet();
		for (int i : models){
			Map<String, Object> pair = new HashMap<>();
			pair.put("model", i);
			pair.put("viewatts", map.get(i));
			list.add(pair);
		}
		return list;
	}
	
}