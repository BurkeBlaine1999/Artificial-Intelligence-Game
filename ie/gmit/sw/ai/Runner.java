package ie.gmit.sw.ai;

import java.io.IOException;

import javafx.application.Application;

public class Runner {
	
	public static void main(String[] args) throws Exception {	
		
		new Runner().train();
		
		Application.launch(GameWindow.class, args);	      
	}
	
	private void train() throws ClassNotFoundException, IOException, Exception {
		new LoadNN().Load();
	}
}