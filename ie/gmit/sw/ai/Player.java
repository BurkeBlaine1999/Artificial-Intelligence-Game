package ie.gmit.sw.ai;

import javafx.application.Application;

public class Player {

	private static Player player;
	private int health = 100;
	GameWindow gw = GameWindow.getInstance();
	int timer;
	
	
	public static Player getInstance() {
		if (player == null)
			player = new Player();
		return player;
	}

	public void heal() {
		if (this.health >= 100) {
			System.out.println("Player Health : " + health);
		} else {
			if(timer < 3) {
				timer++;
			}else {
				timer = 0;
				this.health += 1;
				System.out.println("(HEALING) Player Health : " + health);
			}

		}
		
		if(health > 100) {
			health = 100;
		}
	}

	public void takeDamage(int sharpness) {
		if (health <= 0) {
			System.exit(0);
		} else {
			if (sharpness == 2) {
				System.out.println("PLAYER TAKE 20 DAMAGE");
				this.health -= 20;
			} else if (sharpness == 1) {
				System.out.println("PLAYER TAKE 10 DAMAGE");
				this.health -= 10;
			} else if (sharpness == 0){
				System.out.println("PLAYER TAKE 5 DAMAGE");
				this.health -= 5;
			}
			System.out.println("(DAMAGED)Health : " + health);
		}
	}

	public int getHealth() {
		return this.health;
	}

	public int[] getPos() {
		return gw.getPlayerModel();
	}
	
	

}
