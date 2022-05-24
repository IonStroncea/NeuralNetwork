package neuralNetWork;

import java.io.Serializable;
import java.util.ArrayList;

public class InternalLayer implements Serializable {
	public ArrayList<InternalNeuron> neurons;
	public InternalLayer(int n)
	{
		neurons=new ArrayList<InternalNeuron>(n);
		for(int i=0;i<n;i++)
		{
			neurons.add(new InternalNeuron());
		}
	}
}
