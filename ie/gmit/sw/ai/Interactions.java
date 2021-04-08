package ie.gmit.sw.ai;

public class Interactions {
	
	double distance;
	///Player player = new Player();
	Player player = Player.getInstance();
	
	//Ghost ghost = new Ghost();
	Ghost ghost = Ghost.getInstance();
	String sharpness="";
	
	public void checkDistance(int row, int col, int playerRow, int playerCol) {
		
		distance = Math.sqrt((playerRow - row) * (playerRow -row) + (playerCol - col) * (playerCol - col));
		distance = Math.round(distance);
		
		System.out.println(distance);
		System.out.println(playerRow + "    " + playerCol);
		
		if(distance <= 3) {
			if(ghostAlive()) {
				attack();
			}
		}else if(distance >= 15) {
			PlayerHeal();
			if(ghostAlive()) {
				GhostHeal();
			}
		}		
	}
	
	public void attack() {		
			System.out.println("This ghosts damage type is : " + ghost.damageType());
			this.ghost.takeDamage();	
			player.takeDamage(ghost.damageType());
						
	}
	public void PlayerHeal() {	
		
		player.heal();
	}
	
	public void GhostHeal() {	
		if(ghostAlive()) {
			ghost.heal();
		}
	}
	
	public void playerAlive(){
		//If player Alive keep playing
		
		//Else end Game
		
	}
	
//	public void setGhost(int[] g) throws Exception {
//		
//		if(g[1] == 2) {
//			sharpness = "Sharp";
//		}else if(g[1] == 1) {
//			sharpness = "Blunt";
//		}else {
//			sharpness = "Broken";
//		}
//		ghost = new Ghost(g[0],sharpness,g[2]);
//		
//	}
	
	public boolean ghostAlive() {
		return ghost.isAlive();
	}

}
