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
public class Neuron implements Node{
    private static final double SIGMOID_PARAMETER = 1.0;
    private final ArrayList<Node> inputs;
    private final ArrayList<Double> weights;
    private final double threshold;
    private Double output;
    
    Neuron(){
        
        inputs = new ArrayList();
        weights = new ArrayList();
        threshold = -1.0;
        output = 0.0;
    }
    
    public boolean addInput(Node input){
        
        return inputs.add(input);
    }
    
    public boolean addWeight(double weight){
        
        return weights.add(weight);
    }
    
    public boolean addInputAndWeight(Node input, double weight){
         
        return addInput(input) && addWeight(weight);
    }
     
    @Override
    public double getOutput(){
     
        return output;
    }
    
    public double computeOutPut(){
        
        if(weights.size() - inputs.size() != 1){
            System.err.println("The number of weights should be equal to "
                    + "the number of inputs plus 1!");
            System.exit(1);
        }
        
        output = sigmoid(computeActivation() + getLastWeight() * threshold);
        
        return output;
    }
    
    private double sigmoid(double x){
        return 1.0 / (1.0 + Math.pow(Math.E, -x/SIGMOID_PARAMETER));
    }
    
    private double computeActivation(){
        
        double activation = 0.0;
        
        for(int i = 0; i < inputs.size(); ++i){
            activation += inputs.get(i).getOutput() * weights.get(i);
        } 
        
        return activation;
    }
    
    private double getLastWeight(){
        
        return weights.get(weights.size()-1);
    }   
}
