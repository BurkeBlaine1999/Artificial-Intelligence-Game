package ie.gmit.sw.ai.nn;

import java.io.File;
import org.encog.util.obj.SerializeObject;
import ie.gmit.sw.ai.nn.activator.Activator;

public class EnemyNN {
	
	public static void main(String[] args) throws Exception {new EnemyNN().go();}
	
	public void go() throws Exception {
		
		NeuralNetwork nn = new NeuralNetwork(Activator.ActivationFunction.Sigmoid, 3, 2, 2);
		BackpropagationTrainer trainer = new BackpropagationTrainer(nn);
		trainer.train(data, expected, 0.01, 100000);
		
		SerializeObject.save(new File("EnemyNetwork"), nn);
		System.out.println("SAVED DATA");
			
		double[] test = {2, 1, 1};
		double[] result = nn.process(test);
		
		for (int i = 0; i < result.length; i++){ 
			System.out.println(result[i]);
		}		
		System.out.println(Utils.getMaxIndex(result) + 1);//Pass input and returns action		
	}
	
	private double[][] data = { //Health, Sword, Player
		{2,0,0},{2,0,1},{2,1,0},{2,1,1},{0,0,0},
		{1,0,0},{1,0,1},{1,1,0},{1,1,1},		
		{2,2,0},{2,2,1},{2,2,0},{2,2,1},{0,2,0},
		{1,2,0},{1,2,1},{1,2,0},{1,2,1}
		};

	private double[][] expected = { //Attack,Run
		{0.0,1.0},{0.0,1.0},{0.0,1.0},{1,0,0.0},{0.0,1.0},
		{0.0,1.0},{0.0,1.0},{0.0,1.0},{0.0,1.0},
		{0.0,1.0},{1.0,0.0},{0.0,1.0},{1,0,0.0},{0.0,1.0},
		{0.0,1.0},{1.0,0.0},{0.0,1.0},{1.0,0.0}
		};
	
	
}
