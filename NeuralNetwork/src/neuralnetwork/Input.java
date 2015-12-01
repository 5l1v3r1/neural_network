/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork;

/**
 *
 * @author 1907riseyasenga
 */
public class Input implements Node{
    private double value;
    
    public Input(){
        value = 0.0;
    }
    
    public Input(double value){
        this. value = value;
    }
    
    @Override
    public double getOutput(){
        return value;
    }
    
    public void setValue(double value){
        this.value = value;
    }    
}
