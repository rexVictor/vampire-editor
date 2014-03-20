package vampire.editor.plugins.diceroller.application;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Roller {
	
	private final Random random = new Random();
	
	public List<Integer> roll(int diceCount){
		return roll(diceCount, 10);
	}
	
	public List<Integer> roll(int diceCount, int diceSides){
		List<Integer> results = new LinkedList<>();
		for (int i = 0; i < diceCount; i++){
			int thrown = random.nextInt(diceSides)+1;
			results.add(thrown);
		}
		return results;
	}

}
