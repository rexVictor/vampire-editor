package vampire.editor.plugin.fullapi.sheet.view;

import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributesAPI;

public interface IValueViewAttributes extends ValueViewAttributesAPI{

	public boolean isShowSpace();

	public void setShowSpace(boolean showSpace);

	public boolean isDynamic();

	public void setDynamic(boolean dynamic);

	public int getCircles();

	public void setCircles(int circles);

}