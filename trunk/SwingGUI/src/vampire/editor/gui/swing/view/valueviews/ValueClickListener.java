package vampire.editor.gui.swing.view.valueviews;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


class ValueClickListener implements MouseListener{
	
	private final AbstractValueView valueView;
	
	private final int position;
	
	ValueClickListener(AbstractValueView valueView, int position) {
		super();
		this.valueView = valueView;
		this.position = position;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if(!event.isControlDown()){
			switch (event.getButton()) {
			case MouseEvent.BUTTON1 : 
				valueView.setValue0(position+1);
				break;
			case MouseEvent.BUTTON3 :
				valueView.setValue0(position);
				break;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}


}