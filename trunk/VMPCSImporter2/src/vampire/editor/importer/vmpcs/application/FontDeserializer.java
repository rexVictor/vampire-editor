/*******************************************************************************
 * An importer for the vampire editor handling vmpcs format.
 * Copyright (C) 2013 Matthias Reimchen
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
 * For further information see https://code.google.com/p/vampire-editor/ or write a
 * mail to development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.importer.vmpcs.application;

import java.awt.Font;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class FontDeserializer extends StdDeserializer<Font>{
	
	public FontDeserializer(){
		super(Font.class);
	}

	@Override
	public Font deserialize(JsonParser arg0, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = arg0.readValueAs(Map.class);
		String key = (String) map.get("key");
		int style = (int) map.get("style");
		int size = (int) map.get("size");
		Font font = new Font(key, style, size);
		return font;
	}

}
