package neuralNetWork;

public class TestEquality {
	public static void main(String args[])throws Exception
	{	
		NeuralNetwork neuralNetwork=new NeuralNetwork();
		int [] neurons= {512,256,128,10};
		neuralNetwork=new NeuralNetwork(28*28,4,neurons);
		neuralNetwork.generateRand("Trying someting new");
		
		System.out.println("Saving...");
		neuralNetwork.writeFile("C:\\Users\\strio\\eclipse-workspace\\DrawNumber","test.ser");
		System.out.println("Saved");
		
		NeuralNetwork neuralNetwork1=new NeuralNetwork();
		
		System.out.println("Reading...");
		neuralNetwork1.readFile("C:\\Users\\strio\\eclipse-workspace\\DrawNumber","test.ser");
		System.out.println("Read");
		
		for(int i=0; i<neuralNetwork.internalLayers.size(); i++)
		{
			for(int j=0; j<neuralNetwork.internalLayers.get(i).neurons.size(); j++)
			{
				for(int k=0; k<neuralNetwork.internalLayers.get(i).neurons.get(j).weights.size(); k++)
				{
					if(neuralNetwork.internalLayers.get(i).neurons.get(j).weights.get(k) 
							!= neuralNetwork1.internalLayers.get(i).neurons.get(j).weights.get(k))
					{
						System.out.println(false+" "+neuralNetwork.internalLayers.get(i).neurons.get(j).weights.get(k)
								+" "+neuralNetwork1.internalLayers.get(i).neurons.get(j).weights.get(k));
					}
				}
			}
		}
	}
}
