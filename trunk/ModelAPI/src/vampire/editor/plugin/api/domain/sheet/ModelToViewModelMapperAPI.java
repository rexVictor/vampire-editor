/*******************************************************************************
 * Vampire Editor Model API.
 *     Copyright (C) 2013  Matthias Johannes Reimchen
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *     
 *     Further information can be obtained at
 *     https://code.google.com/p/vampire-editor/ or via mail:
 *     Matthias Johannes Reimchen
 *     development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.plugin.api.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.view.*;

/**
 * This interface maps all model objects to the according ViewAttributes object.
 * @author rex_victor
 *
 */
public interface ModelToViewModelMapperAPI {

	public CategoryViewAttributesAPI getViewAttributes(CategoryAPI category);
	
	public SubCategoryViewAttributesAPI getViewAttributes(SubCategoryAPI subcategory);
	
	public TraitViewAttributesAPI getViewAttributes(TraitAPI trait);
	
	public ValueViewAttributesAPI getViewAttributes(ValueAPI value);
	
	public MetaEntryViewAttributesAPI getViewAttributes(MetaEntryAPI metaEntry);
	
	public HealthEntryViewAttributesAPI getViewAttributes(HealthEntryAPI healthEntry);
	
	public HealthViewAttibutesAPI getViewAttributes(HealthAPI health);
	
	public BloodPoolViewAttributesAPI getViewAttributes(BloodPoolAPI bloodPool);
	
	public MeritEntryViewAttibutesAPI getViewAttributes(MeritAPI merit);
	
	public MeritViewAttributesAPI getViewAttributes(MeritsAPI merits);
	
	public void putView(ValueAPI value, ValueViewAttributesAPI viewAtts);
	
	public void putView(TraitAPI trait, TraitViewAttributesAPI viewAtts);
	
	public void putView(MeritAPI merit, MeritEntryViewAttibutesAPI viewAtts);
	
	public void removeView(ValueAPI value);
	
	public void removeView(TraitAPI trait);
	
	public void removeView(MeritAPI merit);
	
}
