package plugin.api.application.sheet.events;

import plugin.api.application.sheet.controller.ValueControllerAPI;

public interface ValueEventAPI {

	public ValueControllerAPI getSource();

	public int getFormelValue();

	public int getNewValue();

	public int getFormerTempValue();

	public int getNewTempValue();

}