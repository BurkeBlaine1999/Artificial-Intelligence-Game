package ie.gmit.sw.ai;

import java.io.IOException;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class LoadFCL {
	
	//BODY------------------------------------------------
	public double Load(int healthValue, int sharpnessValue) throws ClassNotFoundException, IOException, Exception {
	
	FIS fis = FIS.load("FuzzyLogic.fcl", true); //Load and parse the FCL
	FunctionBlock fb = fis.getFunctionBlock("GhostCommand");
	//JFuzzyChart.get().chart(fis);
	fis.setVariable("Health", healthValue); //Apply a value to a variable
	fis.setVariable("Sharpness", sharpnessValue);
	fis.evaluate(); //Execute the fuzzy inference engine
	
	//System.out.println(fis.getVariable("Command").getValue()); //Output end result 
	
	return fis.getVariable("Command").getValue();

	}
	
	//----------------------------------------------------
	
	public double runFCL(int health,int sharpness) throws Exception{
		double result = Load(health,sharpness);
		return result;
	}
}
