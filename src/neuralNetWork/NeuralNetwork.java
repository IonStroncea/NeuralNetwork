package neuralNetWork;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class NeuralNetwork {
	EntryLayer entryLayer;
	ArrayList<InternalLayer> internalLayers;
	
	public void writeFile(String path,String name)throws Exception
	{
		ArrayList<Integer> layers=new ArrayList<Integer>();
		
		layers.add(internalLayers.size());
		layers.add(entryLayer.neurons.size());
		for(int i=0; i<internalLayers.size(); i++)
		{
			layers.add(internalLayers.get(i).neurons.size());
		}
		FileOutputStream file = new FileOutputStream(path+"\\"+"stucture"+name);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(layers);
        out.close();
       	file.close();
       	
       	ArrayList<Double> weights=new ArrayList<Double>();
       	for(int i=0; i<internalLayers.size(); i++)
       	{
       		for(int j=0; j<internalLayers.get(i).neurons.size(); j++)
       		{
       			for(int k=0; k<internalLayers.get(i).neurons.get(j).weights.size(); k++)
       			{
       				weights.add(internalLayers.get(i).neurons.get(j).weights.get(k));
       			}
       		}
       	}
       	file = new FileOutputStream(path+"\\"+"weights"+name);
         out = new ObjectOutputStream(file);
        out.writeObject(weights);
        out.close();
       	file.close();	
       	
       	ArrayList<Double> bias=new ArrayList<Double>();
       	for(int i=0; i<internalLayers.size(); i++)
       	{
       		for(int j=0; j<internalLayers.get(i).neurons.size(); j++)
       		{
       			bias.add(internalLayers.get(i).neurons.get(j).bias);
       		}
       	}
       	file = new FileOutputStream(path+"\\"+"biases"+name);
        out = new ObjectOutputStream(file);
        out.writeObject(bias);
        out.close();
       	file.close();	
	}
	
	public void readFile(String path, String name)throws Exception
	{
		ArrayList<Integer> layers;
		FileInputStream file = new FileInputStream(path+"\\"+"stucture"+name);
        ObjectInputStream in = new ObjectInputStream(file);
        layers=(ArrayList<Integer>)in.readObject();
        in.close();
        file.close();
        
        ArrayList<Double> weights;
        file = new FileInputStream(path+"\\"+"weights"+name);
        in = new ObjectInputStream(file);
        weights=(ArrayList<Double>)in.readObject();
        in.close();
        file.close();
        
    	ArrayList<Double> bias;
    	file = new FileInputStream(path+"\\"+"biases"+name);
        in = new ObjectInputStream(file);
        bias=(ArrayList<Double>)in.readObject();
        in.close();
        file.close();
        
        entryLayer=new EntryLayer(layers.get(1));
		internalLayers=new ArrayList<InternalLayer>();
		for(int i=0; i<layers.get(0); i++)
		{
			internalLayers.add(new InternalLayer(layers.get(2+i)));
		}
		
		ArrayList<InternalNeuron> neuronsList=internalLayers.get(0).neurons;
		int pointerW=0;
		int pointerB=0;
		for(int j=0; j<neuronsList.size(); j++)
		{
			InternalNeuron internalN=neuronsList.get(j);
			internalN.bias=bias.get(pointerB);
			pointerB++;
			internalN.weights=new ArrayList<Double>();
			internalN.weightDelta=new ArrayList<Double>(entryLayer.neurons.size());
			for(int k=0;k<entryLayer.neurons.size();k++)
			{
				internalN.weights.add(weights.get(pointerW));
				internalN.weightDelta.add(0.0d);
				pointerW++;
			}
		}
		
		for(int i=1;i<internalLayers.size();i++)
		{
			neuronsList=internalLayers.get(i).neurons;
			for(int j=0; j<neuronsList.size(); j++)
			{
				InternalNeuron internalN=neuronsList.get(j);
				internalN.bias=bias.get(pointerB);
				pointerB++;
				internalN.weights=new ArrayList<Double>();
				internalN.weightDelta=new ArrayList<Double>(internalLayers.get(i-1).neurons.size());
				for(int k=0;k<internalLayers.get(i-1).neurons.size();k++)
				{
					internalN.weights.add(weights.get(pointerW));
					internalN.weightDelta.add(0.0d);
					pointerW++;
				}
			}
		}
        
	}
	
	public NeuralNetwork() {}
	
	public NeuralNetwork(int entryNeurons, int internalLayersNum, int ... neurons) throws Exception 
	{
		
		if(internalLayersNum!=neurons.length)
		{
			throw new Exception("Incorect input");
		}
		entryLayer=new EntryLayer(entryNeurons);
		internalLayers=new ArrayList<InternalLayer>();
		for(int i : neurons)
		{
			internalLayers.add(new InternalLayer(i));
		}
		ArrayList<InternalNeuron> neuronsList=internalLayers.get(0).neurons;
		for(InternalNeuron j:neuronsList)
		{
			j.weights=new ArrayList<Double>(entryLayer.neurons.size());
			j.weightDelta=new ArrayList<Double>(entryLayer.neurons.size());
			for(int k=0;k<entryLayer.neurons.size();k++)
			{
				j.weights.add(0.0d);
				j.weightDelta.add(0.0d);
			}
		}
		for(int i=1;i<internalLayers.size();i++)
		{
			neuronsList=internalLayers.get(i).neurons;
			for(InternalNeuron j:neuronsList)
			{
				j.weights=new ArrayList<Double>(internalLayers.get(i-1).neurons.size());
				j.weightDelta=new ArrayList<Double>(internalLayers.get(i-1).neurons.size());
				for(int k=0;k<internalLayers.get(i-1).neurons.size();k++)
				{
					j.weights.add(0.0d);
					j.weightDelta.add(0.0d);
				}
			}
		}
	}
	
	public void run(double ... activation)
	{
		for(int i=0; i<entryLayer.neurons.size(); i++)
		{
			entryLayer.neurons.set(i, new EntryNeuron(activation[i]));
		}
		ArrayList<InternalNeuron> hereNeurons=internalLayers.get(0).neurons;
		ArrayList<EntryNeuron> entryNeurons=entryLayer.neurons;
		for(int j=0;j<internalLayers.get(0).neurons.size(); j++ )
		{
			double activ=0.0f;		
			for(int i=0; i<hereNeurons.get(j).weights.size(); i++ )
			{
				activ+=hereNeurons.get(j).weights.get(i)*entryNeurons.get(i).activation;
			}
			activ+=hereNeurons.get(j).bias;
			hereNeurons.get(j).activation=sigmoid(activ);
			
		}
		for(int z=1; z<internalLayers.size(); z++)
		{
			hereNeurons=internalLayers.get(z).neurons;
			ArrayList<InternalNeuron> previousNeurons=internalLayers.get(z-1).neurons;
			for(int j=0;j<hereNeurons.size(); j++ )
			{
				double activ=0.0f;
				for(int i=0; i<hereNeurons.get(j).weights.size(); i++ )
				{
					activ+=hereNeurons.get(j).weights.get(i)*previousNeurons.get(i).activation;
				}
				activ+=hereNeurons.get(j).bias;
				hereNeurons.get(j).activation=sigmoid(activ);
			}
		}
	}
	public static double sigmoid(double x) {
	    return (1/( 1 + Math.pow(Math.E,(-1*x))));
	  }
	
	public double[] getResult()
	{
		ArrayList<InternalNeuron> lastLayer=internalLayers.get(internalLayers.size()-1).neurons;
		double [] result=new double[lastLayer.size()];
		for(int i=0; i< lastLayer.size(); i++)
		{
			result[i]=lastLayer.get(i).activation;
		}
		return result;
	}
	
	public void generateRand(String seedStr)
	{
		int seed=0;
		for(int i=0; i<seedStr.length(); i++)
		{
			seed+=seedStr.charAt(i);
		}
		Random r=new Random(seed);
		for(int i=0; i<internalLayers.size(); i++)
		{
			ArrayList<InternalNeuron> neurons=internalLayers.get(i).neurons;
			for(int j=0; j<neurons.size(); j++)
			{
				InternalNeuron neuron=neurons.get(j);
				double biasValue=(r.nextDouble());
				neuron.bias=(biasValue==0.0d?0.01d:(biasValue*(r.nextBoolean()?1:-1)));
				for(int z=0; z<neuron.weights.size(); z++)
				{
					int sign=r.nextBoolean()?1:-1;
					double value=(r.nextDouble());
					neuron.weights.set(z, value==0.0d?0.01d:value*sign);
				}
			}
		}
	}

	public void printAll()
	{
		System.out.println();
		System.out.println();
		for(int i=0;i<entryLayer.neurons.size();i++)
		{
			System.out.println("Entry neuron: "+i+" activation: " +entryLayer.neurons.get(i).activation);
		}
		System.out.println();
		System.out.println();
		for(int i=0;i<internalLayers.size();i++)
		{
			ArrayList<InternalNeuron> neurons=internalLayers.get(i).neurons;
			for(int j=0;j<neurons.size(); j++)
			{
				InternalNeuron neuron=neurons.get(j);
				System.out.println("Layer: "+i+" Neuron: "+j+" activation: "+neuron.activation+" bias: "+neuron.bias);
				ArrayList<Double> weights=neuron.weights;
				for(int z=0; z<weights.size();z++)
				{
					System.out.println("weight "+z+" = "+weights.get(z));
				}
			}
			System.out.println();
			System.out.println();
		}
	}

	public void learn(double ... expectation)
	{
		InternalLayer lastLayer=internalLayers.get(internalLayers.size()-1);
		for(int i=0;i<lastLayer.neurons.size();i++)
		{
			InternalNeuron neuron=lastLayer.neurons.get(i);
			neuron.delta=0.0d;
			neuron.delta=2.0d*(expectation[i]-neuron.activation)*neuron.activation*(1.0d-neuron.activation);
			//System.out.println(2.0d+"*"+"("+expectation[i]+"-"+neuron.activation+")*"+neuron.activation+"*("+1.0d+"-"+neuron.activation+")="+neuron.delta);
		}
		for(int i=internalLayers.size()-2;i>=0;i--)
		{
			InternalLayer layer=internalLayers.get(i);
			InternalLayer prevLayer=internalLayers.get(i+1);
			for(int j=0;j<layer.neurons.size();j++)
			{
				InternalNeuron neuron=layer.neurons.get(j);
				neuron.delta=0.0d;
				for(int z=0;z<prevLayer.neurons.size();z++)
				{
					InternalNeuron prevneuron=prevLayer.neurons.get(z);
					neuron.delta+=prevneuron.delta*prevneuron.weights.get(j)*neuron.activation*(1.0d-neuron.activation);
				}
			}
			
		}
		InternalLayer firstlayer=internalLayers.get(0);
		for(InternalNeuron neuron:firstlayer.neurons)
		{
			neuron.biasDelta+=neuron.delta;
			ArrayList<Double> weightDelta=neuron.weightDelta;
			for(int j=0;j<weightDelta.size();j++)
			{
				weightDelta.set(j, (weightDelta.get(j)+(neuron.delta*entryLayer.neurons.get(j).activation)));
			}
		}
		for(int i=1;i<internalLayers.size();i++)
		{
			InternalLayer layer=internalLayers.get(i);
			for(InternalNeuron neuron:layer.neurons)
			{
				neuron.biasDelta+=neuron.delta;
				ArrayList<Double> weightDelta=neuron.weightDelta;
				for(int j=0;j<weightDelta.size();j++)
				{
					weightDelta.set(j, (weightDelta.get(j)+(neuron.delta*internalLayers.get(i-1).neurons.get(j).activation)));
				}
			}
		}
	}

	public void printLearn()
	{
		System.out.println();
		System.out.println();
	
		for(int i=0;i<internalLayers.size();i++)
		{
			ArrayList<InternalNeuron> neurons=internalLayers.get(i).neurons;
			for(int j=0;j<neurons.size(); j++)
			{
				InternalNeuron neuron=neurons.get(j);
				System.out.println("Layer: "+i+" Neuron: "+j+" delta:"+neuron.delta+" biasDelta: "+neuron.biasDelta);
				ArrayList<Double> weights=neuron.weightDelta;
				for(int z=0; z<weights.size();z++)
				{
					System.out.println("weightDelta "+z+" = "+weights.get(z));
				}
			}
			System.out.println();
			System.out.println();
		}
	}
	
	public void newLearn()
	{
		for(InternalLayer i:internalLayers)
		{
			for(InternalNeuron neuron: i.neurons)
			{
				neuron.delta=0.0d;
				neuron.biasDelta=0.0d;
				for(int j=0;j<neuron.weightDelta.size();j++)
				{
					neuron.weightDelta.set(j, 0.0d);
				}
			}
		}
	}
	
	public void addModif(int setSize)
	{
		double miu=0.1d;
		for(InternalLayer i:internalLayers)
		{
			for(InternalNeuron neuron: i.neurons)
			{
				double middleBias=neuron.biasDelta/setSize;
				neuron.bias+=(middleBias*miu);
				for(int j=0;j<neuron.weights.size();j++)
				{
					double middleWeight=neuron.weightDelta.get(j)/setSize;
					neuron.weights.set(j, (neuron.weights.get(j)+middleWeight*miu));
				}
			}
		}
	}
}
