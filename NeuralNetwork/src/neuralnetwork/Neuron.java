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
public class Neuron extends Node{
    private static final double SIGMOID_PARAMETER = 1.0;
    private final ArrayList<Pair<Node,Double>> weightedInputs;
    private Pair<Input,Double> weightedThreshold;
    private Double output;
    
    Neuron(){
        
        weightedInputs = new ArrayList<>();
        weightedThreshold = null;
        output = 0.0;
    }
    
    public boolean addInputAndWeight(Node input, double weight){
         
        return weightedInputs.add(new Pair<>(input, weight));
    }
    
    public boolean setThresholdAndWeight(Input input, double weight){
        
        weightedThreshold = new Pair<>(input, weight);
        return weightedThreshold != null;
    }
    
    @Override
    public double getOutput(){
     
        return output;
    }
    
    public void computeOutPut(){
        
        output = sigmoid(computeActivation() + 
                weightedThreshold.getFirst().getOutput() *
                        weightedThreshold.getSecond());
    }
    
    private double sigmoid(double x){
        return 1.0 / (1.0 + Math.pow(Math.E, -x/SIGMOID_PARAMETER));
    }
    
    private double computeActivation(){
        
        double activation = 0.0;
        
        for(int i = 0; i < weightedInputs.size(); ++i){
            activation += weightedInputs.get(i).getFirst().getOutput() * 
                    weightedInputs.get(i).getSecond();
        } 
        
        return activation;
    }
    
    
}
