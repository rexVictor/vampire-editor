package vampire.editor.plugin.api.application.sheet.controller;

import vampire.editor.plugin.api.view.sheet.MiscView;

public interface MiscControllerAPI {

	public abstract MeritsControllerAPI getMerits();

	public abstract MeritsControllerAPI getFlaws();

	public abstract HealthControllerAPI getHealth();

	public abstract BloodPoolControllerAPI getBloodPool();

	public abstract MiscView getView();

}