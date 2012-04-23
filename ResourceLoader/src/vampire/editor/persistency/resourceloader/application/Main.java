package vampire.editor.persistency.resourceloader.application;

import java.io.IOException;


import vampire.editor.persistency.resourceloader.persistency.Loader;

public class Main {

	/**
	 * @param args
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws IOException, Throwable {
		Loader loader = new Loader();
		loader.test();

	}

}
