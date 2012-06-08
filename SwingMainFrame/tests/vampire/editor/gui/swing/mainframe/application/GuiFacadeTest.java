package vampire.editor.gui.swing.mainframe.application;

import static org.junit.Assert.*;

import org.junit.Test;

public class GuiFacadeTest {

	@Test
	public void testCreateErrorMessage() {
		GuiFacade facade = new GuiFacade();
		facade.createErrorMessage("Dies ist ein beliebiger Text, der nur dazu da ist, die CreateErrorDialog Methode aufzurufen." +
				"\nWeiterhin kann man damit prüfen, ob der Zeilenumbruch korrekt durchgeführt wird, was auch immer das heißen mag." +
				"\nDies ist auch der Hauptgrund, warum dieser Text so unnötig lang ist.");
		fail("Not well implemented");
		
	}
	
	@Test
	public void testOpenFileDialog() {
		GuiFacade facade = new GuiFacade();
		facade.openFileView();
	}

}
