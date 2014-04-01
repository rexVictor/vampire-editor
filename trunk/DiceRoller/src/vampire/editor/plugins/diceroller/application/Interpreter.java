package vampire.editor.plugins.diceroller.application;

import java.util.List;

public class Interpreter {
	
	public static int getNumberOfBruttoBotches(List<Integer> rolled){
		return getNumberOf(1, rolled);
	}
	
	public static int getNumberOf(int x, List<Integer> rolled){
		int xes = 0;
		for (int i = 0; i < rolled.size(); i++){
			if (rolled.get(i) == x){
				xes++;
			}
		}
		return xes;
	}
	
	public static int getNumberOfTens(List<Integer> rolled){
		return getNumberOf(10, rolled);
	}
	
	public static int getBruttoSucesses(int difficulty, List<Integer> rolled){
		return 0;
	}
	
}
