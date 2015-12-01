/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork;

import java.util.Random;

/**
 *
 * @author 1907riseyasenga
 */
public class Util {

    private final static Random RAND = new Random(0);
    
    public static double nextDouble(){
        
        return RAND.nextDouble();
    }
    
    public static int nextInt(int n){
        
        return RAND.nextInt(n);
    }
    
    public static double randomDouble(){
        
        return 2.0 * nextDouble() - 1.0;
    }
}
