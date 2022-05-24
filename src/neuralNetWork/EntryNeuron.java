package neuralNetWork;

import java.io.Serializable;

public class EntryNeuron implements Serializable {
	public double activation;
	public EntryNeuron()
	{}
	public EntryNeuron(double activation)
	{
		this.activation=activation;
	}
}
