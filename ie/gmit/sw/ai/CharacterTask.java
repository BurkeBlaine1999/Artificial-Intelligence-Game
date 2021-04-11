package ie.gmit.sw.ai;

import java.util.concurrent.ThreadLocalRandom;

import javafx.concurrent.Task;

 
public class CharacterTask extends Task<Void>{
	
	//VARIABLES -----------------------------------------
	private static final int SLEEP_TIME = 600; //Sleep for 300 ms
	private static ThreadLocalRandom rand = ThreadLocalRandom.current();
	private boolean alive = true;
	private GameModel model;
	private char enemyID;
	private int row;
	private int col;
	GameWindow gw = new GameWindow();	
	Interactions interaction = new Interactions();	
	Player player = Player.getInstance();
	private String action;
	private Ghost ghost;
	private FuzzyGhost fGhost;
	private int distance;
	
	//BODY -----------------------------------------------
	
	public CharacterTask() {}
	
	public CharacterTask(GameModel model, char enemyID, int row, int col, Ghost ghost) {
		this.model = model;
		this.enemyID = enemyID;
		this.row = row;
		this.col = col;
		this.ghost = ghost;
	}
	
	public CharacterTask(GameModel model, char enemyID, int row, int col, FuzzyGhost fGhost) {
		this.model = model;
		this.enemyID = enemyID;
		this.row = row;
		this.col = col;
		this.fGhost = fGhost;
	}
	
	//----------------------------------------------------
	
    @Override
    public Void call() throws Exception {
    	
    	
    	while (alive) {
    		
        	if(interaction.ghostAlive() == false){
        		alive = false;
        		System.out.println("\n===========\nGHOST " + this.enemyID + " IS DEAD\n===========");
        	}
        	
        	if(ghost == null) {
        		
        		Thread.sleep(SLEEP_TIME);      	
            	int [] playerPos = player.getPos();
            	     	
            	distance = (int)interaction.checkDistance(row, col, playerPos[0], playerPos[1],fGhost);
            	System.out.println("FuzzyGhost distance = " + distance);
            	action = fGhost.ghostProcess(distance);
            	
            	System.out.println("\n===========\nGHOST " + this.enemyID + "\nCOMMAND : " + action + "\n===========");
            	
            	synchronized (model) {
            		int temp_row = row, temp_col = col;
            		if (rand.nextBoolean()) {
                		temp_row += rand.nextBoolean() ? 1 : -1;
                	}else {
                		temp_col += rand.nextBoolean() ? 1 : -1;
                	}
                	
                	if (model.isValidMove(row, col, temp_row, temp_col, enemyID)){
                		model.set(temp_row, temp_col, enemyID);
                		model.set(row, col, '\u0020');
                		row = temp_row;
                		col = temp_col;
                	}else {
                		if(model.get(row, col) == 1) {
                		}
                		fGhost.execute();
                	}
            	}
         }else {
        		Thread.sleep(SLEEP_TIME);      	
            	int [] playerPos = player.getPos();
            	     	
            	distance = (int)interaction.checkDistance(row, col, playerPos[0], playerPos[1],ghost);
            	System.out.println("Ghost distance = " + distance);
            	action = ghost.ghostProcess(distance);
            	
            	System.out.println("\n===========\nGHOST " + this.enemyID + "\nCOMMAND : " + action + "\n===========");
            	           	
            	synchronized (model) {
            		int temp_row = row, temp_col = col;
            		if (rand.nextBoolean()) {
                		temp_row += rand.nextBoolean() ? 1 : -1;
                	}else {
                		temp_col += rand.nextBoolean() ? 1 : -1;
                	}
                	
                	if (model.isValidMove(row, col, temp_row, temp_col, enemyID)){
                		model.set(temp_row, temp_col, enemyID);
                		model.set(row, col, '\u0020');
                		row = temp_row;
                		col = temp_col;
                	}else {
                		if(model.get(row, col) == 1) {
                		}
                		ghost.execute();
                	}
            	}

        	}
    		
    	}
		return null;
    }
    
    //----------------------------------------------------
    
	public int[] getGhostModel() {
		int[] GhostPos = {row,col};
		return GhostPos;
	}
}