package ie.gmit.sw.ai;

import java.io.IOException;

public class Interactions {
	
	double distance;
	Player player = Player.getInstance();
	
	Ghost ghost = new Ghost();
	String sharpness="";
	
	//----------------------------------------------------
	
	public double checkDistance(int row, int col, int playerRow, int playerCol,Ghost setGhost) {
		
		ghost = setGhost;
		
		distance = Math.sqrt((playerRow - row) * (playerRow -row) + (playerCol - col) * (playerCol - col));
		distance = Math.round(distance);
		
		if(distance <= 3) {
			if(ghost.isAlive()) {
				attack();
			}
		}else if(distance >= 15) {
			PlayerHeal();
			if(ghost.isAlive()) {
				GhostHeal();
			}
		}		
		
		if(distance <= 1) {
			distance = 1;
		}else {
			distance =0;
		}
		
		return distance;
	}
	
	//----------------------------------------------------
	
	public String ghostCommand() throws ClassNotFoundException, IOException {
		int[] ghostDetails = ghost.getGhost();
		return ghost.ghostProcess(ghostDetails[2]);
		
	}
	
	//----------------------------------------------------
	
	public void attack() {		
			ghost.takeDamage();	
			player.takeDamage(ghost.damageType());
						
	}
	
	//----------------------------------------------------
	
	public void PlayerHeal() {	
		
		player.heal();
	}
	
	//----------------------------------------------------
	
	public void GhostHeal() {	
		if(ghost.isAlive()) {
			ghost.heal();
		}
	}

	//----------------------------------------------------
	
	public boolean ghostAlive() {
		return ghost.isAlive();
	}
}
