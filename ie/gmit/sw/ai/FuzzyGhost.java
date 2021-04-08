package ie.gmit.sw.ai;

import java.io.File;

import org.encog.util.obj.SerializeObject;

import ie.gmit.sw.ai.nn.NeuralNetwork;

public class FuzzyGhost {
	int enemyHealth;
	int swordSharpness;
	int player;
	private int ghostHealth;
	String Command;
	
	GameWindow gw = new GameWindow();
	private int[] playerPos = gw.getPlayerModel();
	
	public FuzzyGhost(int health, int Sharpness) throws Exception{
		
		
		
		if(health >= 70) {
			enemyHealth=2;			
		}else if(health >= 40 && health < 70) {
			enemyHealth=1;			
		}else{		
			enemyHealth=0;
		}
		
		if(Sharpness > 70) {
			swordSharpness = 2;
		}else if(Sharpness < 70 && Sharpness > 40 ) {
			swordSharpness = 1;
		}else{
			swordSharpness = 0;
		}
		
		double[] input = {enemyHealth,swordSharpness};
		int output = 0;
		
		try {
			//output = new LoadNN().runNetwork(input);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(output == 1) {
			Command = "Attack";
		}else{
			Command = "Run";
		}
	}
	
	public void heal() {
		ghostHealth += 5;
		System.out.println("(HEALING)Health : " + ghostHealth);
	}
	
	public void takeDamage() {
		ghostHealth -= 20;
		System.out.println("(DAMAGED)Health : " + ghostHealth);
	}


}
