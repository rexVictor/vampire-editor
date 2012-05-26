package vampire.editor.plugin.fullapi.sheet.view;

import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributesAPI;

public interface IValueViewAttributes extends ValueViewAttributesAPI{

	@Override
	public boolean isShowSpace();

	public void setShowSpace(boolean showSpace);

	@Override
	public boolean isDynamic();

	public void setDynamic(boolean dynamic);

	@Override
	public int getCircles();

	public void setCircles(int circles);
	
	public void setSize(int size);
	

	public IValueViewAttributes clone();

}