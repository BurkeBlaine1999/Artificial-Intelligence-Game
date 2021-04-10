package ie.gmit.sw.ai;

import java.io.IOException;

public class Interactions {
	
	//VARIABLES -----------------------------------------
	double distance;
	String sharpness="";
	
	Player player = Player.getInstance();
	Ghost ghost = new Ghost();
	FuzzyGhost fGhost = new FuzzyGhost();
	
	//BODY------------------------------------------------
	
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
	
	public double checkDistance(int row, int col, int playerRow, int playerCol, FuzzyGhost setFGhost) {
		
		fGhost = setFGhost;
		
		distance = Math.sqrt((playerRow - row) * (playerRow -row) + (playerCol - col) * (playerCol - col));
		distance = Math.round(distance);
		
		System.out.println("=======================" + distance);
		
		if(distance <= 3) {
			if(fGhost.isAlive()) {
				attack();
			}
		}else if(distance >= 15) {
			PlayerHeal();
			if(fGhost.isAlive()) {
				GhostHeal();
			}
		}	
		
		return distance;
	}
	
	//----------------------------------------------------
	
	public String ghostCommand() throws ClassNotFoundException, IOException {
		if(ghost == null) {
			int[] ghostDetails = fGhost.getGhost();
			return fGhost.ghostProcess(ghostDetails[2]);
		}else {
			int[] ghostDetails = ghost.getGhost();
			return ghost.ghostProcess(ghostDetails[2]);
		}

		
	}
	
	//----------------------------------------------------
	
	public void attack() {		
		if(ghost == null) {
			fGhost.takeDamage();	
		}else {
			ghost.takeDamage();	
		}
			
		player.takeDamage(ghost.damageType());
						
	}
	
	//----------------------------------------------------
	
	public void PlayerHeal() {	
		
		player.heal();
	}
	
	//----------------------------------------------------
	
	public void GhostHeal() {	
		if(ghost == null) {
			if(fGhost.isAlive()) {
				fGhost.heal();
			}
		}else {
			if(ghost.isAlive()) {
				ghost.heal();
			}
		}
	}

	//----------------------------------------------------
	
	public boolean ghostAlive() {
		if(ghost == null) {
			return fGhost.isAlive();
		}else {
			return ghost.isAlive();
		}
		
	}



}
