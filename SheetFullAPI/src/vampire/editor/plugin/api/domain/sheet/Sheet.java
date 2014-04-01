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

public interface Sheet extends SheetAPI{

	public void setBorderKey(String borderKey);

	@Override
	public Sheet clone();
	
	@Override
	public Meta getMeta();
	
	@Override
	public Categories getCategories();
	
	@Override
	public Health getHealth();
	
	@Override
	public Merits getMerits();
	
	@Override
	public Merits getFlaws();
	
	@Override
	public BloodPool getBloodPool();

	public void setCategories(Categories categories);

	public void setMeta(Meta meta);

	public void setBloodPool(BloodPool bloodPool);

	public void setHealth(Health health);

	public void setMerits(Merits merits);

	public void setFlaws(Merits merits);

}
