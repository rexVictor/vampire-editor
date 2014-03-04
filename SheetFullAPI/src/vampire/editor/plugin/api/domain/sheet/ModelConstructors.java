/*******************************************************************************
 * Vampire Editor Model API with write methods.
 * Copyright (C) 2014  Matthias Johannes Reimchen
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
package vampire.editor.plugin.api.domain.sheet;

import java.nio.file.Path;

@VersionModelSpecification(compatibleVersions = "1.0.0.0", version = "1.0.0.0")
public interface ModelConstructors {
	
	public Value createValue(Integer minValue, Integer maxValue);
	
	public Trait createTrait();
	
	public SubCategory createSubCategory();
	
	public Category createCategory();
	
	public HealthEntry createHealthEntry();
	
	public Health createHealth();
	
	public Merit createMerit();
	
	public Merits createMerits();
	
	public BloodPool createBloodPool();
	
	public ModelToViewModelMapper createModelToViewModelMapper();
	
	public MetaEntry createMetaEntry();
	
	public Meta createMeta();
	
	public Sheet createSheet();
	
	public Categories createCategories();
	
	public VampireDocument createVampireDocument(Sheet sheet, ModelToViewModelMapper mapper, Path pluginPath);
	

}
