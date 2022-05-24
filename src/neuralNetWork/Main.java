package neuralNetWork;


public class Main {
	public static void main(String args[])
	{
		try
		{
			int internal[]= {5,5,4,5,3};
			NeuralNetwork neuralNetwork=new NeuralNetwork(3,5,internal);
			neuralNetwork.generateRand("Salvadore");
			
			double activation[]= {1.0f,0.3f,0.9f};
			neuralNetwork.run(activation);
			
			double results[]=neuralNetwork.getResult();
			System.out.println("Results:");
			System.out.println();
			for(double d:results)
			{
				System.out.print(d+" ");
			}
			
			neuralNetwork.printAll();
			
			double expected[]= {1.0d,1.0d,0.0d};
			for(int k=0;k<100;k++) 
			{
				System.out.println(k);
				for(int j=0;j<1000;j++)
				{
					
					for(int i=0;i<100;i++)
					{
						neuralNetwork.run(activation);
						neuralNetwork.learn(expected);	
						
					}
					neuralNetwork.addModif(100);
					neuralNetwork.newLearn();	
//					System.out.println("----");
//					for(InternalNeuron neuron: neuralNetwork.internalLayers.get(neuralNetwork.internalLayers.size()-1).neurons)
//					{
//						System.out.println("Distance: "+Math.abs(expected[neuralNetwork.internalLayers.get(neuralNetwork.internalLayers.size()-1).neurons.indexOf(neuron)]-neuron.activation));
//					}
				}
			}
			neuralNetwork.run(activation);
			results=neuralNetwork.getResult();
			System.out.println("Results:");
			System.out.println();
			for(double d:results)
			{
				System.out.print(d+" ");
			}
			//neuralNetwork.printLearn();
           	
		}
		catch(Exception e)
		{
			System.out.println("Error "+e.toString());
			System.out.println("Error "+e.getMessage());
		}
	}
}
