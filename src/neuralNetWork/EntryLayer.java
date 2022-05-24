package neuralNetWork;

import java.io.Serializable;
import java.util.ArrayList;

public class EntryLayer implements Serializable {
	public ArrayList<EntryNeuron> neurons;
	public EntryLayer(int n)
	{
		neurons=new ArrayList<EntryNeuron>();
		for(int i=0 ; i<n ;i++)
		{
			neurons.add(new EntryNeuron());
		}
	}
}
