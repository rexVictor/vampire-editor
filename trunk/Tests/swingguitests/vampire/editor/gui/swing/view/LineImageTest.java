package vampire.editor.gui.swing.view;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;

import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import vampire.editor.gui.swing.domain.Line;

public class LineImageTest {

	@Test
	public void test() throws InterruptedException {
		List<Line> lines = new LinkedList<>();
		for (int i = 0; i < 100; i++){
			lines.add(Line.getDefault());
		}
		LineImage lineImage = new LineImage(lines.get(0).getImage());
		lineImage.setTitle("Geile Sache");
		LineImage lineImage2 = new LineImage(lines.get(1).getImage());
		
		JFrame frame = new JFrame();
		FormLayout layout = new FormLayout("100px, 20px", "50px, 50px");
		
		JPanel panel = new FormDebugPanel();
		panel.setLayout(layout);
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight 	= 	1;
		constraints.gridWidth	=	2;
		constraints.gridX		=	1;
		constraints.gridY		=	1;
		
		panel.add(lineImage.getPanel(), constraints);
		constraints.gridY = 2;
		panel.add(lineImage2.getPanel(), constraints);
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		while (frame.isVisible()){
			Thread.sleep(10);
		}
		fail("");
		
	}

}
