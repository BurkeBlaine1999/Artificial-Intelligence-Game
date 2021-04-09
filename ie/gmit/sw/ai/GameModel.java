package ie.gmit.sw.ai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import javafx.concurrent.Task;

/*
 * [READ THIS CAREFULLY]
 * You will need to change the method addGameCharacter() below and configure each 
 * instance of CharacterTask with a Command object. The implementation below uses
 * a lambda expression ()-> System.out.println("Action executing!") as the default
 * logic for the execute() method.
 * 
 * [WARNING] Don't mess with anything else in this class unless you know exactly 
 * what you're at... If you break it, you own it.
 */

public class GameModel implements Command{
	private static final int MAX_CHARACTERS = 10;
	private ThreadLocalRandom rand = ThreadLocalRandom.current();
	private char[][] model;
	
	
	private final ExecutorService exec = Executors.newFixedThreadPool(MAX_CHARACTERS, e -> {
        Thread t = new Thread(e);
        t.setDaemon(true);
        return t ;
    });
	
	public GameModel(int dimension) throws Exception{
		model = new char[dimension][dimension];
		init();
		carve();
		addGameCharacters();
	}
	
	public void tearDown() {
		exec.shutdownNow();
	}
	
	/*
	 * Initialises the game model by creating an n x m array filled with hedge  
	 */
	private void init(){
		for (int row = 0; row < model.length; row++){
			for (int col = 0; col < model[row].length; col++){
				model[row][col] = '\u0030'; //\u0030 = 0x30 = 0 (base 10) = A hedge
			}
		}
	}
	
	/*
	 * Carve paths through the hedge to create passages.
	 */
	public void carve(){
		for (int row = 0; row < model.length; row++){
			for (int col = 0; col < model[row].length - 1; col++){
				if (row == 0) {
					model[row][col + 1] = '\u0020';
				}else if (col == model.length - 1) {
					model[row - 1][col] = '\u0020';
				}else if (rand.nextBoolean()) {
					model[row][col + 1] = '\u0020';
				}else {
					model[row - 1][col] = '\u0020';
				}
			}
		}
	}



	private void addGameCharacters() throws Exception {
		Collection<Task<Void>> tasks = new ArrayList<>();
		
						//Tasks, enemy ID,Wall, numEnemies,   ghost
		//addGameCharacter(tasks, '\u0032', '0', MAX_CHARACTERS / 5,new Ghost(variables)); //2 is a Red Enemy, 0 is a wall
		addGameCharacter(tasks, '\u0032', '0', MAX_CHARACTERS / 5,new Ghost(100,2,0)); //2 is a Red Enemy, 0 is a wall
		addGameCharacter(tasks, '\u0033', '0', MAX_CHARACTERS / 5,new Ghost(100,1,0)); //3 is a Pink Enemy, 0 is a wall
		addGameCharacter(tasks, '\u0034', '0', MAX_CHARACTERS / 5,new Ghost(100,0,0)); //4 is a Blue Enemy, 0 is a wall
	//	addGameCharacter(tasks, '\u0035', '0', MAX_CHARACTERS / 5,new FuzzyGhost(100,100)); //5 is a Red Green Enemy, 0 is a wall
	//	addGameCharacter(tasks, '\u0036', '0', MAX_CHARACTERS / 5,new FuzzyGhost(100,100)); //6 is a Orange Enemy, 0 is a wall
		tasks.forEach(exec::execute);
		
	}


//	private void addGameCharacter(Collection<Task<Void>> tasks, char enemyID, char replace, int numEnemies,FuzzyGhost fuzzyGhost) {
//		int counter = 0;
//
//		while (counter < numEnemies){
//			int row = rand.nextInt(model.length);
//			int col = rand.nextInt(model[0].length);
//
//			if (model[row][col] == replace){
//				
//				model[row][col] = enemyID;
//				
//				tasks.add(new CharacterTask(this, enemyID, row, col, ()-> System.out.println("Action Executed!")));
//				counter++;
//			}
//
//
//		}
//		
//	}

	private void addGameCharacter(Collection<Task<Void>> tasks, char enemyID, char replace, int numEnemies, Ghost ghost) throws Exception{

		int counter = 0;

		while (counter < numEnemies){
			int row = rand.nextInt(model.length);
			int col = rand.nextInt(model[0].length);

			if (model[row][col] == replace){
				
				model[row][col] = enemyID;
				
				tasks.add(new CharacterTask(this, enemyID, row, col, ghost));
				counter++;
			}


		}
	}


	@Override
	public void execute() {}
	
	public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, char character){
		if (toRow <= this.size() - 1 && toCol <= this.size() - 1 && this.get(toRow, toCol) == ' '){
			this.set(fromRow, fromCol, '\u0020');
			this.set(toRow, toCol, character);
			return true;
		}else{
			return false; //Can't move
		}
	}

	
	public char get(int row, int col){
		return this.model[row][col];
	}
	
	public void set(int row, int col, char c){
		this.model[row][col] = c;
	}
	
	public int size(){
		return this.model.length;
	}

}