/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork;

import java.util.ArrayList;

/**
 *
 * @author 1907riseyasenga
 */
public class NeuralNetwork {

    private ArrayList<Input> inputs;
    private ArrayList<ArrayList<Neuron>> neuronLayers;

    static public ArrayList<Input> createInputLayer(int nInputs){
        ArrayList<Input> inputLayer;
        inputLayer = new ArrayList<>();
        
        for(int i = 0; i < nInputs; ++i){
            inputLayer.add(new Input(Util.nextDouble()));
        }
        
        return inputLayer;
    }
    
    static public ArrayList<Neuron> createFirstNeuronLayer(
            ArrayList<Input> inputs, int nNeurons){
        
        ArrayList<Neuron> firstNeuronLayer = new ArrayList<>();
        
        for(int i = 0; i < nNeurons; ++i){
            Neuron neuron = new Neuron();
            
            for(int j = 0; j < inputs.size(); ++j){
                neuron.addInputAndWeight(inputs.get(j), Util.randomDouble());
            }
            
            neuron.setThresholdAndWeight(new Input(-1.0), Util.randomDouble());
            firstNeuronLayer.add(neuron);
        }
        
        return firstNeuronLayer;
    }
    
    static public boolean addNeuronLayer(
            ArrayList<ArrayList<Neuron>> neuronLayers, 
            int nNeurons){
        
        ArrayList<Neuron> newNeuronLayer;
        newNeuronLayer = new ArrayList<>();
        ArrayList<Neuron> topNeuronLayer = 
                neuronLayers.get(neuronLayers.size()-1);
        int nInputs = topNeuronLayer.size();

        for(int i = 0; i < nNeurons; ++i){
            Neuron neuron = new Neuron();

            for(int j = 0; j < nInputs; ++j){
                neuron.addInputAndWeight(
                        topNeuronLayer.get(j), 
                        Util.randomDouble());
            }

            neuron.setThresholdAndWeight(new Input(-1.0), Util.randomDouble());
            newNeuronLayer.add(neuron);
        }

        return neuronLayers.add(newNeuronLayer);        
    }
    
    public static NeuralNetwork createNeuralNet(
            int nInputs, 
            ArrayList<Integer> nNeuronsForEachLayer){
        
        NeuralNetwork res = new NeuralNetwork();
        res.inputs = createInputLayer(nInputs);
        
        if(nNeuronsForEachLayer.isEmpty()){
            return res;
        }
        
        res.neuronLayers = new ArrayList<>();
        res.neuronLayers.add(createFirstNeuronLayer(
                res.inputs, 
                nNeuronsForEachLayer.get(0)));
        int nLayers = nNeuronsForEachLayer.size();
        
        for(int i = 1; i < nLayers; ++i){
            addNeuronLayer(res.neuronLayers, nNeuronsForEachLayer.get(i));
        }
        
        return res;
    }
    
    public NeuralNetwork(){
        inputs = new ArrayList<>();
        neuronLayers = new ArrayList<>();
    }
    
    public boolean addNeuronLayer(ArrayList<Neuron> neuronLayer){
        
        return neuronLayers.add(neuronLayer);
    }
    
    public Double[] computeOutput(){
        
        int nLayers = neuronLayers.size();
        
        for(int i = 0; i < nLayers; ++i){
            ArrayList<Neuron> currentNeuronLayer;
            currentNeuronLayer = neuronLayers.get(i);
            
            for(int j = 0; j < currentNeuronLayer.size(); ++j){
                Neuron neuron = currentNeuronLayer.get(j);
                neuron.computeOutPut();
            }
        }

        ArrayList<Neuron> topLayer = neuronLayers.get(neuronLayers.size()-1);
        int nOutputs = topLayer.size();
        Double[] outputs = new Double[nOutputs];
        
        for(int i = 0; i < nOutputs; ++i){
            outputs[i] = topLayer.get(i).getOutput();
        }
        
        return outputs;
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ArrayList<Integer> nNeuronsPerLayer = new ArrayList<>();
        int nLayers = Util.nextInt(20) + 80;
        System.out.println("nLayers = " + nLayers);
        
        for(int i = 0; i < nLayers; ++i){
            int r = Util.nextInt(500) + 500;
            System.out.println("layer " + i + "\tnumber of neurons = " + r);
            nNeuronsPerLayer.add(r);
        }
        
        System.out.println("nLayers = " + nNeuronsPerLayer.size());
        int nInputs = Util.nextInt(500) + 500;
        System.out.println("nInputs = " + nInputs);
        
        NeuralNetwork nn = 
                NeuralNetwork.createNeuralNet(nInputs, nNeuronsPerLayer);
        
        System.out.println("nn.inputs.size() = " + nn.inputs.size());
        System.out.println("nn.neuronLayers.size() = " + nn.neuronLayers.size());
        
        Double[] outputs = nn.computeOutput();
        
        for(int i = 0; i < outputs.length; ++i){
            System.out.println("output " + i + " = " + outputs[i]);
        }
    }
    
}
