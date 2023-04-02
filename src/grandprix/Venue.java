package grandprix;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joxy
 */
public class Venue {
    private int averageLapTime;
    private double chanceOfRain;
    private int numberOfLaps;
    private String venueName;
    public boolean flgTwice = false;    //za proveru da li je ponovo uneta ista staza
    
    public Venue(){}
    
    public Venue(String data){
        String [] tokens = data.split(",");
        if(tokens.length!=4){
            System.out.println("Greska pri ocitavanju" + tokens);
            System.exit(0);
	}
        this.venueName=tokens[0];
        this.numberOfLaps=Integer.parseInt(tokens[1]);
        this.averageLapTime=Integer.parseInt(tokens[2]);
        this.chanceOfRain=Double.parseDouble(tokens[3]);
    }
    
    @Override
    public String toString() {
        return this.venueName + ", broj krugova: " + this.numberOfLaps + " (prosečno vreme: " + this.averageLapTime + "), sa šansom za kišu " + String.format("%.1f",(this.chanceOfRain*100)) +"%";
    }
    
    public double getAverageLapTime() {
        return averageLapTime;
    }

    public double getChanceOfRain() {
        return chanceOfRain;
    }

    public int getNumberOfLaps() {
        return numberOfLaps;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setAverageLapTime(int averageLapTime) {
        this.averageLapTime = averageLapTime;
    }

    public void setChanceOfRain(double chanceOfRain) {
        this.chanceOfRain = chanceOfRain;
    }

    public void setNumberOfLaps(int numberOfLaps) {
        this.numberOfLaps = numberOfLaps;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
    
    
}
