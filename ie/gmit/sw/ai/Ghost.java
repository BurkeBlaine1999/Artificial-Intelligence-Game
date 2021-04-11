package ie.gmit.sw.ai;

import java.io.IOException;

public class Ghost implements Command {
	
	//VARIABLES -----------------------------------------
	int NNHealth;
	int swordSharpness;
	int player;
	private int ghostHealth;
	boolean alive = true;
	int timer;
	String Command;	
	GameWindow gw = new GameWindow();
	
	//BODY------------------------------------------------
	
	public Ghost() {}
	
	public Ghost(int health, int Sharpness,int playerInRange) throws Exception{
		swordSharpness = Sharpness;
		player = playerInRange;
		ghostHealth = health;
		
		if(health >= 70) {
			NNHealth=2;			
		}else if(health >= 40 && health < 70) {
			NNHealth=1;			
		}else{		
			NNHealth=0;
		}
	}
	
	//----------------------------------------------------
	
	public String ghostProcess(int playerInRange) throws ClassNotFoundException, IOException {
		
		if(ghostHealth >= 70) {
			NNHealth=2;
		}else if(ghostHealth >= 40 && ghostHealth < 70) {
			NNHealth=1;
		}else{
			NNHealth=0;
		}
		
		System.out.println("\n===========\nGHOST STATS \nHEALTH : " + NNHealth + "\nSHARPNESS : " + swordSharpness + "\nPlayer in range :  " + playerInRange + "\n===========");
		
		double[] input = {NNHealth,swordSharpness,playerInRange};
		int output = 0;
		
		try {
			output = new LoadNN().runNetwork(input);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(output == 1) {
			Command = "Attack";
		}else if(output == 2){
			Command = "Run";
		}
		return Command; 
		
	}

	//----------------------------------------------------

	public void heal() {
		if(this.ghostHealth >= 100) {
			ghostHealth = 100;		
		}else {
			if(timer <= 2) {
				timer++;
			}else {
				timer = 0;
				ghostHealth += 2;
			}
		}

	}

	//----------------------------------------------------

	public void takeDamage() {
		gw.setGhostHealth(ghostHealth);
		if(ghostHealth <= 0) {
			alive = false;
		}else {
			ghostHealth -= 15;
		}
	}
	
	//----------------------------------------------------
	
	public int damageType() {
		return swordSharpness;
	}
	
	//----------------------------------------------------
	
	public void setDamageType(int sharpness) {
	 swordSharpness = sharpness;
	}
	
	//----------------------------------------------------

	public int[] getGhost() {
		int[] ghostDetails = {NNHealth,swordSharpness,player};
		return ghostDetails;
	}
	
	//----------------------------------------------------

	public int getGhostHealth() {
		return ghostHealth;
	}
	
	//----------------------------------------------------
	
	public boolean isAlive() {
		return alive;
	}
	
	//----------------------------------------------------
	
	@Override
	public void execute() {}

}
