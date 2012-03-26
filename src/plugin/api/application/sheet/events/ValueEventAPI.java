package plugin.api.application.sheet.events;

import application.sheet.controller.ValueController;

public interface ValueEventAPI {

	public ValueController getSource();

	public int getFormelValue();

	public int getNewValue();

	public int getFormerTempValue();

	public int getNewTempValue();

}