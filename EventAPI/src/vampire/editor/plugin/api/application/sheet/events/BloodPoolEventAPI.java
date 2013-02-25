package vampire.editor.plugin.api.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.BloodPoolControllerAPI;

public interface BloodPoolEventAPI {
	
	public BloodPoolControllerAPI getSource();
	
	public int getFormerMaxValue();
	
	public int getNewMaxValue();
	
	public int getFormerValue();
	
	public int getNewValue();

}
