package ie.gmit.sw.ai;

import java.io.File;
import java.io.IOException;

import org.encog.util.obj.SerializeObject;

import ie.gmit.sw.ai.nn.EnemyNN;
import ie.gmit.sw.ai.nn.NeuralNetwork;
import ie.gmit.sw.ai.nn.Utils;

public class LoadNN {
	
	//private static NeuralNetwork NN;
	private static NeuralNetwork NN;
	
	public NeuralNetwork Load() throws ClassNotFoundException, IOException, Exception {
		
		try{
			
			if((NeuralNetwork) SerializeObject.load(new File("EnemyNetwork")) != null){
				
				NN = (NeuralNetwork) SerializeObject.load(new File("EnemyNetwork"));
				
				return NN;
			}
			
		} catch (IOException e){
	    	  
				new EnemyNN().go();
				NN = (NeuralNetwork) SerializeObject.load(new File("EnemyNetwork"));
				
				return NN;
	      }
		return null;
		
	}
	
	public int runNetwork(double[] data) throws Exception{
		double[] result = NN.process(data);
		int output;
		output = Utils.getMaxIndex(result) + 1;
		return output;
	}
	
}
