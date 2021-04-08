package ie.gmit.sw.ai;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * Main UI for the game. You should not have to alter anything in this class.
 * 
 */
public class GameWindow extends Application{
	private static final char PLAYER_ID = '1';
	private static final int DEFAULT_SIZE = 60;
	private static final int IMAGE_COUNT = 6;
	private GameView view;
	private GameModel model;
	private int currentRow;
	private int currentCol;
	
	static int[] playerPos = {0,0};
	Text controls = new Text(); 
	Text healthText = new Text(); 
	
	public String health = "100";
	
	VBox box = new VBox();
	Scene scene = new Scene(box);
	
	private static GameWindow gw;
	
	public static GameWindow getInstance() {
		if (gw == null)
			gw = new GameWindow();
		return gw;
	}
	
	@Override
    public void start(Stage stage) throws Exception {
		model = new GameModel(DEFAULT_SIZE); //Create a model
    	view = new GameView(model); //Create a view of the model
    	

    	stage.setTitle("GMIT - B.Sc. in Computing (Software Development) - AI Assignment 2021");
		stage.setWidth(600);
		stage.setHeight(800);
		stage.setOnCloseRequest((e) -> model.tearDown()); //Shut down the executor service
	
		
		controls.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 20)); 
		controls.setText("\nUse the arrow keys to move. \nPress 'z' to zoom out !\n");
		controls.setX(300); 
		controls.setY(790);	
        
		
		healthText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20)); 
		healthText.setText("Player Health : " + health);
		healthText.setX(300); 
		healthText.setY(790);
		

		scene.setOnKeyPressed(e -> keyPressed(e)); //Add a key listener
		stage.setScene(scene);
//		Image icon = new Image("Icon.png");
//		stage.getIcons().add(icon);
		
    	Sprite[] sprites = getSprites(); //Load the sprites from the res directory
    	view.setSprites(sprites); //Add the sprites to the view
    	placePlayer(); //Add the player
    	box.getChildren().add(view);
    	box.getChildren().add(controls);
    	box.getChildren().add(healthText);
		
    	view.draw(); //Paint the view
    	
		//Display the window
		stage.show();
		stage.centerOnScreen();
	}
	
    public void keyPressed(KeyEvent e) { //Handle key events
    	KeyCode key = e.getCode(); 
        if (key == KeyCode.RIGHT && currentCol < DEFAULT_SIZE - 1) {
        	if (model.isValidMove(currentRow, currentCol, currentRow, currentCol + 1, PLAYER_ID)) currentCol++;   	
        	
        }else if (key == KeyCode.LEFT && currentCol > 0) {
        	if (model.isValidMove(currentRow, currentCol, currentRow, currentCol - 1, PLAYER_ID)) currentCol--;	
        	
        }else if (key == KeyCode.UP && currentRow > 0) {
        	if (model.isValidMove(currentRow, currentCol, currentRow - 1, currentCol, PLAYER_ID)) currentRow--;
        	
        }
        else if (key == KeyCode.DOWN && currentRow < DEFAULT_SIZE - 1) {
        	if (model.isValidMove(currentRow, currentCol, currentRow + 1, currentCol, PLAYER_ID)) currentRow++;  
        	
        }else if (key == KeyCode.Z){
        	view.toggleZoom();
        }else{
        	return;
        }
        
        updateView();      
        updateHealth();
    }
	
	private void placePlayer(){  //Place the main player character	
    	currentRow = (int) (DEFAULT_SIZE * Math.random());
    	currentCol = (int) (DEFAULT_SIZE * Math.random());
    	model.set(currentRow, currentCol, PLAYER_ID); //Player is at index 1
    	updateView(); 		
	}
	
	private void updateView(){ 	
		view.setCurrentRow(currentRow);
		view.setCurrentCol(currentCol);
		playerPos[0] = currentRow;
		playerPos[1] = currentCol;
		
	}
	
	public void updateHealth(){
		Player p = Player.getInstance();
		healthText.setText("Player Health : " + p.getHealth());
	}
	
	private Sprite[] getSprites() throws Exception{
		/*
		 * Read in the images from the resources directory as sprites. Each sprite is
		 * referenced by its index in the array, e.g. a 3 implies a Pink Enemy... Ideally, 
		 * the array should dynamically created from the images... 
		 */
		Sprite[] sprites = new Sprite[IMAGE_COUNT];
		sprites[0] = new Sprite("Player", "/res/player-0.png", "/res/player-1.png", "/res/player-2.png", "/res/player-3.png", "/res/player-4.png", "/res/player-5.png", "/res/player-6.png", "/res/player-7.png");
		sprites[1] = new Sprite("Red Enemy", "/res/red-0.png", "/res/red-1.png", "/res/red-2.png", "/res/red-3.png", "/res/red-4.png", "/res/red-5.png", "/res/red-6.png", "/res/red-7.png");
		sprites[2] = new Sprite("Pink Enemy", "/res/pink-0.png", "/res/pink-1.png", "/res/pink-2.png", "/res/pink-3.png", "/res/pink-4.png", "/res/pink-5.png", "/res/pink-6.png", "/res/pink-7.png");
		sprites[3] = new Sprite("Blue Enemy", "/res/blue-0.png", "/res/blue-1.png", "/res/blue-2.png", "/res/blue-3.png", "/res/blue-4.png", "/res/blue-5.png", "/res/blue-6.png", "/res/blue-7.png");
		sprites[4] = new Sprite("Red Green Enemy", "/res/gred-0.png", "/res/gred-1.png", "/res/gred-2.png", "/res/gred-3.png", "/res/gred-4.png", "/res/gred-5.png", "/res/gred-6.png", "/res/gred-7.png");
		sprites[5] = new Sprite("Orange Enemy", "/res/orange-0.png", "/res/orange-1.png", "/res/orange-2.png", "/res/orange-3.png", "/res/orange-4.png", "/res/orange-5.png", "/res/orange-6.png", "/res/orange-7.png");		return sprites;
	}
	
	public int[] getPlayerModel() {
		return playerPos;
	}
	
}