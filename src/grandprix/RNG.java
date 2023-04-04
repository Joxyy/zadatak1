package grandprix;


import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joxy
 */
public class RNG {
    private int minimumValue;
    private int maximumValue;
    private int desiredRnd;
    private Random rnd = new Random ();
  
    public RNG(){}
    
    public RNG(int min, int max){
        this.minimumValue=min;
        this.maximumValue=max;
    }
    
    public int getRandomValue(){    
        this.desiredRnd = rnd.nextInt((this.maximumValue - this.minimumValue) + 1) + this.minimumValue;
        return desiredRnd;
    }
    
    public int getMinimumValue() {
        return minimumValue;
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public Random getRnd() {
        return rnd;
    }

    public int getDesiredRnd() {
        return desiredRnd;
    }
    

    public void setMinimumValue(int minimumValue) {
        this.minimumValue = minimumValue;
    }

    public void setMaximumValue(int maximumValue) {
        this.maximumValue = maximumValue;
    }

    public void setRnd(Random rnd) {
        this.rnd = rnd;
    }
    
    
}
