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
