/*******************************************************************************
 * The GUI of the vampire editor implemented using Swing.
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
package vampire.editor.gui.swing.mainframe.application;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import vampire.editor.gui.swing.domain.Images;
import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.ManagerAPI;

public class GuiActivator implements Activator{

	@Override
	public void setManager(ManagerAPI manager) {
		ToolTipManager.sharedInstance().setDismissDelay(120000);
		try{
			Toolkit xToolkit = Toolkit.getDefaultToolkit();
			Field awtAppClassNameField = xToolkit.getClass().getDeclaredField("awtAppClassName"); //$NON-NLS-1$
			awtAppClassNameField.setAccessible(true);
			awtAppClassNameField.set(xToolkit, "Vampire Editor"); //$NON-NLS-1$
		}
		catch (Throwable e){
			e.printStackTrace();
		}
		try {
			Class.forName(Images.class.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Path config = Paths.get("resources", "guiconfig", "config.properties"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		try (InputStream inputStream = Files.newInputStream(config)){
			Properties properties = new Properties();
			properties.load(inputStream);
			String laf = properties.getProperty("laf"); //$NON-NLS-1$
			if (laf != null){
				if ("auto".equals(laf)){ //$NON-NLS-1$
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}
				else {
					UIManager.setLookAndFeel(laf);
				}
			}
			String tooltipFont = properties.getProperty("ToolTip.font"); //$NON-NLS-1$
			UIManager.put("ToolTip.font", new Font(tooltipFont, 0, 18)); //$NON-NLS-1$
			UIManager.put("ToolTip.background", Color.BLACK); //$NON-NLS-1$
			UIManager.put("ToolTip.foreground", Color.WHITE); //$NON-NLS-1$
		} catch (IOException e){
			e.printStackTrace();
		} catch ( ClassNotFoundException | InstantiationException |		
				IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		GuiFacade facade = new GuiFacade(manager);
		manager.setGUIPlugin(facade);
	}

}
