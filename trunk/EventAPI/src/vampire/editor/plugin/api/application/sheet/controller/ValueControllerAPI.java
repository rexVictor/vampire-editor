package vampire.editor.plugin.api.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.events.ValueListener;
import vampire.editor.plugin.api.domain.sheet.ValueAPI;
import vampire.editor.plugin.api.view.sheet.ValueView;

public interface ValueControllerAPI {

	public void setValue(int value);

	public void setTempValue(int value);

	public ValueAPI getValue();

	public ValueView getView();

	public void addListener(ValueListener listener);

	public void removeListener(ValueListener listener);

}