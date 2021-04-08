package ie.gmit.sw.ai;

import java.io.File;

import org.encog.util.obj.SerializeObject;

import ie.gmit.sw.ai.nn.NeuralNetwork;

public class Ghost implements Command {
	
	int enemyHealth;
	int swordSharpness;
	int player;
	private int ghostHealth;
	String Command;
	
	GameWindow gw = new GameWindow();
	//private int[] playerPos = gw.getPlayerModel();
	
	boolean alive = true;
	
	public Ghost() {}
	
	public Ghost(int health, String Sharpness,int playerInRange) throws Exception{
		
		NeuralNetwork nn = (NeuralNetwork) SerializeObject.load(new File("EnemyNetwork"));
		
		if(health >= 70) {
			enemyHealth=2;			
		}else if(health >= 40 && health < 70) {
			enemyHealth=1;			
		}else{		
			enemyHealth=0;
		}
		
		if(Sharpness == "Sharp") {
			swordSharpness = 2;
		}else if(Sharpness == "blunt") {
			swordSharpness = 1;
		}else{
			swordSharpness = 0;
		}
		
		player = playerInRange;
		
		double[] input = {enemyHealth,swordSharpness,playerInRange};
		int output = 0;
		
		try {
			output = new LoadNN().runNetwork(input);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(output == 1) {
			Command = "Attack";
			System.out.println("ATTACK PLAYER");
		}else{
			Command = "Run";
		}
	}
	


	public void heal() {
		if(ghostHealth >= 100) {
			ghostHealth = 100;		
		}else {
			ghostHealth += 5;
		}

	}
	
	public int damageType() {
		return swordSharpness;

	}
	
	public void takeDamage() {
		if(ghostHealth <= 0) {
			alive = false;
			System.out.println("GHOST DEAD");	
		}else {
			ghostHealth -= 10;
			System.out.println("(DAMAGED) GHOST Health : " + ghostHealth);
		}

	}
	
	public int[] getGhost() {
		int[] ghostDetails = {enemyHealth,swordSharpness,player};
		return ghostDetails;
	}
	
	public boolean isAlive() {
		return alive;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("EXECUTING");
		
	}

}
