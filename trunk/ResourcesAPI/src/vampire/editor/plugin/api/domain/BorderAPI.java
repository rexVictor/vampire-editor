package vampire.editor.plugin.api.domain;

import java.awt.Image;

public interface BorderAPI {

	public abstract Image getImage();

	public abstract int getLeftInset();

	public abstract int getRightInset();

	public abstract int getTopInset();

	public abstract int getBottomInset();

	public abstract int getSheetWidth();

	public abstract int getSheetHeight();

}