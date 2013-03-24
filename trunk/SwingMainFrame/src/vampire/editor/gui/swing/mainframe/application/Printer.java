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

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import vampire.editor.gui.swing.view.SBorderView;
import vampire.editor.gui.swing.view.SSheetView;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.plugin.Trigger;
import vampire.editor.plugin.api.view.sheet.SheetView;

public class Printer implements Trigger{

	private final ManagerAPI manager;

	public Printer(ManagerAPI manager) {
		super();
		this.manager = manager;
	}
	
	public void print(){
		PrinterJob job = PrinterJob.getPrinterJob();
		SheetView sheetView = manager.getGeneralController().getCurrentController().getView();
		if (sheetView instanceof SSheetView){
			SBorderView borderView = ((SSheetView) sheetView).getBorderView();
			job.setPrintable(borderView);
			boolean doPrint = job.printDialog();
			if (doPrint){
				try {
					job.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void leftClicked() {
		print();
	}
}
