package vampire.editor.domain;

import java.awt.Image;

import vampire.editor.plugin.api.domain.BorderAPI;

public class Border implements BorderAPI {
	
	private final Image image;
	
	private final int left;
	
	private final int right;
	
	private final int up;
	
	private final int down;
	
	private final int sheetWidth;
	
	private final int sheetHeight;

	public Border(Image image, int left, int right, int up, int down,
			int sheetWidth, int sheetHeight) {
		super();
		this.image = image;
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
		this.sheetWidth = sheetWidth;
		this.sheetHeight = sheetHeight;
	}

	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public int getLeftInset() {
		return left;
	}

	@Override
	public int getRightInset() {
		return right;
	}

	@Override
	public int getTopInset() {
		return up;
	}

	@Override
	public int getBottomInset() {
		return down;
	}

	@Override
	public int getSheetWidth() {
		return sheetWidth;
	}

	@Override
	public int getSheetHeight() {
		return sheetHeight;
	}

}
