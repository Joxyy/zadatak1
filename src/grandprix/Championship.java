package grandprix;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joxy
 */
public class Championship {
   
    static final int MINOR_MECHANICAL_FAULT = 5;        //static se koristi da se ne bi za svaki obj kreirala prom i bzv da uzima mem
    static final int MAJOR_MECHANICAL_FAULT = 3;
    static final int UNRECOVERABLE_MECHANICAL_FAULT = 1;
    static final int penalties[] = {0,3,5,7,10}; 
    static final int points[] = {8,5,3,1}; 
    
    public ArrayList<Driver> drivers = new ArrayList<Driver>();
    public ArrayList<Venue> venues = new ArrayList<Venue>();
    
    
    private static int t;
    private Venue currentVenue;
    private Driver leader;

    
    //konstruktor treba da cita podatke iz txt fajlova
    public Championship() throws IOException{  
        System.out.println("-------------------------------------");

        String sP = System.getProperty("file.separator");
        File f = new File("."+sP+"fajlovi"+sP+"vozaci.txt");	//kreiranje File objekta koji reprezentuje vozaci.txt fajl
        
        //provera da li postoji fajl
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream( f), "UTF8")); 
            String data;
            while((data = in.readLine()) != null) {
                    drivers.add(new Driver(data));
            }
            System.out.println("Data obtained from the file vozaci.txt succesefully");
            System.out.println("-------------------------------------");
            in.close();
        }catch(FileNotFoundException e) {
            System.out.println("The file is not found!");
        }
        f = new File("."+sP+"fajlovi"+sP+"staze.txt");
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream( f), "UTF8")); 
            String data;
            while((data = in.readLine()) != null) {
                    venues.add(new Venue(data));
            }
            System.out.println("Data obtained from the file vozaci.txt succesefully");
            System.out.println("-------------------------------------");
            in.close();
        }catch(FileNotFoundException e){
            System.out.println("The file is not found!");
        }
    }
    public boolean venueSelection(String venueName){
        for(int i = 0; i < venues.size(); i++){
            if(venueName.equalsIgnoreCase(venues.get(i).getVenueName())){
                System.out.println("Odabrana je staza: "+venues.get(i));
                this.currentVenue=venues.get(i);
                return false;
            }
        }
        System.out.println("Venue does not exist");
        return true;
    }
    public void printAllDrivers(){
        for (Driver d: drivers) {
                System.out.println(d);
        }
        System.out.println("\n");
    }
    public void printAllVenues(){
        for (int i = 0; i < venues.size(); i++) {
                System.out.println(venues.get(i));
        }
        System.out.println("\n");
    }
    public void prepareForTheRace(){ // inicijalizacija atributa za svakog vozaca
        Driver.setSortParam("ranking");
        Collections.sort(drivers);
        System.out.println("Current status:\n");
        for(int i = 0; i < drivers.size(); i++){
            Driver d=drivers.get(i);
            if(i<4) d.setAccumulatedTime(penalties[i]);
            else d.setAccumulatedTime(penalties[4]);        
            drivers.get(i).setEligibleToRace(true);
            drivers.get(i).setDryPneu(true);
            System.out.println(d.getRanking()+".) "+ "points:" + d.getAccumulatedPoints() +" (penalty " + d.getAccumulatedTime()+ ")\t"+d.getName() );
        }
    }  
    
    private void lap(){     //simulacija 1 kruga
        boolean end=false;
        while(!end){
            //System.out.println(t);
            for(Driver d : drivers){
                if(d.getAccumulatedTime()==t){  //odbrojava vreme t dok ne izjednaci sa accumulated time-om nekog od igraca; tad dobijamo lidera
                    leader=d;       
                    end=true;
                    break;
                }
            }
            t++;
            //Thread.sleep(1000);
        }
    }
    
    
    public void race() throws InterruptedException{    //simulacija 1 trke
        t = 0;
        this.prepareForTheRace();
        for(int lapNo=1;lapNo<=currentVenue.getNumberOfLaps();lapNo++){
            System.out.println("-------------------------------------");
            System.out.println(lapNo+". lap:");
            System.out.println("-------------------------------------");
            this.driveAverageLapTime();
            this.applySpecialSkills(lapNo);
            this.checkMechanicalProblem();
            if(lapNo==2) this.pneuChange();
            this.checkWeather();
            this.lap();    //odbrojava vreme t dok ne izjednaci sa accumulated time-om nekog od igraca; tad dobijamo lidera
            printLeader(lapNo);
            Thread.sleep(3000);
        }
        this.pointsAssign();
        this.rankingAssign();
        this.printWinnersAfterRace(currentVenue.getVenueName());
        venues.remove(venues.indexOf(currentVenue));

    }
    public void driveAverageLapTime(){ // svakom vozaču koji vozi trku, dodeli srednje vreme voženja kruga (određeno samom stazom)     
        System.out.println("-------------------------------------");
        System.out.println("Accumulated time update:\n");
        for(Driver d : drivers){
            if(d.isEligibleToRace()){
                d.setAccumulatedTime(d.getAccumulatedTime()+this.currentVenue.getAverageLapTime());
                System.out.println(d.getName()+" - "+d.getAccumulatedTime() + "s");
            }
            else{
                System.out.println(d.getName()+" - is not eligible to race");
            }
        }
        System.out.println("-------------------------------------");
    }
    public void applySpecialSkills(int lapNo){  // primeni specijalne veštine za svakog vozača u datom krugu
        for(Driver d : drivers){
            if(d.isEligibleToRace()){
                if(d.getSpecialSkill().equalsIgnoreCase("overtaking")){
                    if(lapNo%3==0) {
                        RNG rng = new RNG(10,20);
                        d.useSpecialSkill(rng);
                    }
                }
                else{
                    RNG rng = new RNG(1,8);
                    d.useSpecialSkill(rng);
                }
            }
        }
    }
    public void checkMechanicalProblem(){ // proveri da li su neki vozači imali kvar (jedan od tri) i ako treba ažuriraj im vreme voženja kruga, odnosno atribut eligibleToRace
        RNG rng = new RNG(1,100);
        System.out.println("-------------------------------------");
        for(Driver d : drivers){
            if(d.isEligibleToRace()){
                if(rng.getRandomValue()<=MINOR_MECHANICAL_FAULT){
                    d.setAccumulatedTime(d.getAccumulatedTime()+20);
                    System.out.println(d.getName()+" had minor mechanical fault (+20s; updated accumulated time "+d.getAccumulatedTime()+"s)");
                }
                if(rng.getRandomValue()<=MAJOR_MECHANICAL_FAULT){
                    d.setAccumulatedTime(d.getAccumulatedTime()+120);
                    System.out.println(d.getName()+" had major mechanical fault (+120s; updated accumulated time "+d.getAccumulatedTime()+"s)");
                }
                if(rng.getRandomValue()<=UNRECOVERABLE_MECHANICAL_FAULT){
                    System.out.println(d.getName()+" had unrecoverable mechanical fault (not egible to finish the race)");
                    d.setEligibleToRace(false);
                    d.setAccumulatedTime(-1);
                }
            }
        }
        System.out.println("-------------------------------------");
    }
    public void pneuChange(){   //stavi u dr krug
        RNG rng = new RNG(1,100);
        System.out.println("-------------------------------------");     
        System.out.println("Players that changed their pneumatics (+10s)\n");        
        for(Driver d : drivers){
            if(d.isEligibleToRace()){
                    if(rng.getRandomValue()<=50){
                    d.setDryPneu(false);
                    d.setAccumulatedTime(d.getAccumulatedTime()+10);
                    System.out.println(d.getName()+" (updated accumulated time "+d.getAccumulatedTime()+"s)");
                }
            }
        }
        System.out.println("-------------------------------------");
    }
    public void checkWeather(){
        RNG rng = new RNG(1,100);
        if(rng.getRandomValue()<=(currentVenue.getChanceOfRain()*100)){
            System.out.println("-------------------------------------");
            System.out.println(" It rains.....");

            for(Driver d : drivers){
                if(d.isEligibleToRace()){
                    if(d.isDryPneu()){
                        d.setAccumulatedTime(d.getAccumulatedTime()+5);
                        System.out.println(d.getName()+ " has pneumatics for dry weather (+5s; updated accumulated time "+d.getAccumulatedTime()+"s)");
                    }
                }
            }
            System.out.println("-------------------------------------");
        }
    }
    
    public void printLeader(int lap){ // ispiši ko je na prvom mestu nakon kruga lap
        System.out.println("-------------------------------------");
        System.out.println("Leading player in the end of the " + lap + ". lap is " + leader.getName() + " (" + leader.getAccumulatedTime()+"s)");
        System.out.println("-------------------------------------");
    }
    public void printWinnersAfterRace(String venueName){ // ispiši imena pobednika (četiri najbolje rangirana vozača) na stazi venueName

        System.out.println("**************************************************");
        System.out.println("4 best ranged players ("+ venueName +"):\n");
        for(int i=0;i<4;i++){
            System.out.println((i+1)+".) "+drivers.get(i).getName()+ " - " + drivers.get(i).getAccumulatedPoints() + " points");
            //drivers.get(i).setRanking(1); postvi pri incijalizaciji trke
        }
        System.out.println("**************************************************");
    }
    public void printChampion(int numOfRaces){ // ispisati poruku o tome ko je sampion na kraju šampionata, tj. nakon numOfRaces odvozanih trka
        Driver.setSortParam("points");    //sortira po akumuliranom vremenu
        Collections.sort(drivers);
        System.out.println("**************************************************");
        System.out.println("**************************************************");
        System.out.println("The champion after " + numOfRaces + " races is " + drivers.get(0).getName());
        System.out.println("**************************************************");
        System.out.println("**************************************************");
        System.out.println("Championship table status:");
        printAllDrivers();
    }
    public void pointsAssign(){
        Driver.setSortParam("time");    //sortira po akumuliranom vremenu
        Collections.sort(drivers);
        for (int i=0;i<4;i++){
            drivers.get(i).setAccumulatedPoints(drivers.get(i).getAccumulatedPoints()+points[i]);
        }
    }
    public void rankingAssign(){
        Driver.setSortParam("points");    //sortira po poenima
        Collections.sort(drivers);
        for (int i=0;i<4;i++){
            drivers.get(i).setRanking(i+1);
        }
        for (int i=4;i<drivers.size();i++){
            drivers.get(i).setRanking(5);
        }
    }
 
    public ArrayList<Driver> getDrivers() {
        return drivers;
    }
    public ArrayList<Venue> getVenues() {
        return venues;
    }
    public Venue getCurrentVenue() {
        return currentVenue;
    }
 
    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }
    public void setVenues(ArrayList<Venue> venues) {
        this.venues = venues;
    }
    public void setCurrentVenue(Venue currentVenue) {
        this.currentVenue = currentVenue;
    }

}
