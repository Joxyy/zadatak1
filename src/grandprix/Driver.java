package grandprix;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joxy
 */
public class Driver implements Comparable<Driver>{
    private String name;
    private int ranking;
    private String specialSkill;
    private boolean eligibleToRace;    //podrazumevano je false
    private int accumulatedTime;
    private int accumulatedPoints;
    private static String sortParam;
    private boolean dryPneu;
    
    public Driver(){}
    
    public Driver(String data){
        String [] tokens = data.split(",");
        if(tokens.length!=3){
            System.out.println("Greska pri ocitavanju" + tokens);
            System.exit(0);
	}
        this.name=tokens[0];
        this.ranking=Integer.parseInt(tokens[1]);
        this.specialSkill=tokens[2];
    }
    
    //prebacivanje objekta Driver u string reprezentaciju
    @Override
    public String toString() {
            return "(" + this.ranking + ") " + this.name + ", ("+this.accumulatedPoints+" points); skill: " + this.specialSkill;
    }
    
    
    
    @Override   //ovo ce nam omoguciti da sortiramo
    public int compareTo(Driver other) {
            if(sortParam.equalsIgnoreCase("ranking")){
                if (this.ranking < other.ranking) return -1;
                else if (this.ranking > other.ranking) return 1;
                else return 0;
            }
            else if(sortParam.equalsIgnoreCase("points")){
                if (this.accumulatedPoints < other.accumulatedPoints) return 1;
                else if (this.accumulatedPoints > other.accumulatedPoints) return -1;
                else return 0;
            }
            
            else if(sortParam.equalsIgnoreCase("time")){
                if(this.accumulatedTime==-1)    return 1;     //ako nije eligible
                if(this.accumulatedTime == other.accumulatedTime){
                    RNG rng = new RNG(1,8);
                    this.accumulatedTime=rng.getRandomValue();
                    other.accumulatedTime=rng.getRandomValue();
                }
                if (this.accumulatedTime < other.accumulatedTime) return -1;
                if (this.accumulatedTime > other.accumulatedTime) return 1;
            }
            return 0;
    } 
    
    public void useSpecialSkill(RNG rng){
        this.accumulatedTime-=rng.getRandomValue();
        System.out.println(this.name + " will use " + this.specialSkill + " (-" + rng.getDesiredRnd()+ "s; updated accumulated time "+this.getAccumulatedTime()+"s)");
    }

    public String getName() {
        return name;
    }

    public int getRanking() {
        return ranking;
    }

    public String getSpecialSkill() {
        return specialSkill;
    }

    public boolean isEligibleToRace() {
        return eligibleToRace;
    }

    public int getAccumulatedTime() {
        return accumulatedTime;
    }

    public static String getSortParam() {
        return sortParam;
    }
    

    public int getAccumulatedPoints() {
        return accumulatedPoints;
    }

    public boolean isDryPneu() {
        return dryPneu;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public void setSpecialSkill(String specialSkill) {
        this.specialSkill = specialSkill;
    }

    public void setEligibleToRace(boolean eligibleToRace) {
        this.eligibleToRace = eligibleToRace;
    }

    public void setAccumulatedTime(int accumulatedTime) {
        this.accumulatedTime = accumulatedTime;
    }

    public void setAccumulatedPoints(int accumulatedPoints) {
        this.accumulatedPoints = accumulatedPoints;
    }

    public static void setSortParam(String sortParam) {
        Driver.sortParam = sortParam;
    }

    public void setDryPneu(boolean dryPneu) {
        this.dryPneu = dryPneu;
    }
    
}
