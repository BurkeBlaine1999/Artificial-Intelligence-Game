package ie.gmit.sw.ai;

import javafx.application.Application;

public class Player {

	private static Player player;

	public static Player getInstance() {
		if (player == null)
			player = new Player();
		return player;
	}

	private int health;
	GameWindow gw = new GameWindow();

	public void heal() {
		if (this.health >= 100) {
			System.out.println("Player Health : " + health);
		} else {
			this.health += 5;
			System.out.println("(HEALING) Player Health : " + health);
		}
		
		
	}

	public void takeDamage(int sharpness) {
		if (health <= 0) {
		} else {
			if (sharpness == 2) {
				this.health -= 10;
			} else if (sharpness == 1) {
				this.health -= 5;
			} else {
				this.health -= 2;
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
