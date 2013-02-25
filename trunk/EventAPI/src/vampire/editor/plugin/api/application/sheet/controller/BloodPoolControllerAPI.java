package vampire.editor.plugin.api.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.events.BloodPoolListener;
import vampire.editor.plugin.api.domain.sheet.BloodPoolAPI;
import vampire.editor.plugin.api.view.sheet.BloodPoolView;

public interface BloodPoolControllerAPI {
	
	public BloodPoolAPI getBloodPool();
	
	public BloodPoolView getBloodPoolView();
	
	public void addBloodPoolListener(BloodPoolListener listener);
	
	public void removeBloodPoolListener(BloodPoolListener listener);
	
	public void setValue(int value);
	
	public void setMaxValue(int maxValue);

}
