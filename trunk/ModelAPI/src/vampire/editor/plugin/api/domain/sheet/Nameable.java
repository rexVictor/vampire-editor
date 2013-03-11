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
/**
 * This is the interface implemented by nearly all model objects.<br>
 * It provides a public clone method and a getter for the name.<br>
 * Implementation Note:<br>
 * Due to internationalization reasons, the name of a model object is a non-trivial issue.<br>
 * The convention is as follows:<br>
 * The name saved in the model is English, written with small letters and spaces substituted with
 * underscores "_".<br>
 * For the output of this model, see the internationalization of the vampire editor.<br>
 * It needs to be secured, that only the convention stated above is stored in the model. <br>
 * If the user enters a new word, that is not part of the internationalization then there is no other
 * option, than to save it into the model, as the user typed it. If there are often inputs, not
 * stored in the internationalization, please visit  https://code.google.com/p/vampire-editor/ to
 * add the unknown word.
 * 
 * @author rex_victor
 *
 */
public interface Nameable{
	
	/**
	 * See {@link Nameable} for future information about the names used in the model.
	 * @return the name
	 */
	public String getName();
	
	public Nameable clone();

}
