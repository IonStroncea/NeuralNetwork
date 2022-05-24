package neuralNetWork;

import java.io.Serializable;
import java.util.ArrayList;

public class InternalNeuron implements Serializable {
	public ArrayList<Double> weights;
	public double activation=0.0f;
	public double bias;
	public ArrayList<Double> weightDelta;
	public double biasDelta=0.0f;
	public double delta=0.0f;
	
}
