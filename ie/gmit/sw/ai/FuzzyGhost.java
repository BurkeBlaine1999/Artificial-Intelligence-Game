package ie.gmit.sw.ai;

import java.io.IOException;

public class FuzzyGhost implements Command {
	
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
	
	public FuzzyGhost() {}
	
	//----------------------------------------------------

	public FuzzyGhost(int health, int Sharpness,int playerInRange) throws Exception{
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
		
		double output =0;
		
		try {
			output = new LoadFCL().Load(ghostHealth, swordSharpness);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("playerInRange = " + playerInRange);
		
		if(output >= 50 && playerInRange <= 1) {
			Command = "Attack";
		}else{
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
			System.out.println("FUZZY GHOST DEAD");	
		}else {
			ghostHealth -= 15;
			System.out.println("(DAMAGED) FUZZY GHOST Health : " + ghostHealth);
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
