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
    
    public ArrayList<Driver> drivers = new ArrayList<Driver>();
    public ArrayList<Venue> venues = new ArrayList<Venue>();
    
    private Venue currentVenue;
    private int sec;
    private int lapNo;
    private int raceNo=1;

    
    //konstruktor treba da cita podatke iz txt fajlova
    public Championship() throws IOException{
        
        System.out.println("-------------------------------------");

        String sP = System.getProperty("file.separator");
        File f = new File("."+sP+"fajlovi"+sP+"vozaci.txt");	//kreiranje File objekta koji reprezentuje vozaci.txt fajl
        
        //provera da li postoji fajl
        if(f.exists()){
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream( f), "UTF8")); 
            String data;
            while((data = in.readLine()) != null) {
                    drivers.add(new Driver(data));
            }
            in.close();
        }else {
            System.out.println("Nije pronadjen fajl!");
        }
        f = new File("."+sP+"fajlovi"+sP+"staze.txt");
        if(f.exists()){
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream( f), "UTF8")); 
            String data;
            while((data = in.readLine()) != null) {
                    venues.add(new Venue(data));
            }
            in.close();
        }else {
            System.out.println("Nije pronadjen fajl!");
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
        System.out.println("Uneta je nepostojeća staza");
        return true;
    }
    public void sortDrivers(){
        Collections.sort(drivers);
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
        this.sortDrivers();
        System.out.println("Aktuelno stanje na tabeli:\n");
        for(int i = 0; i < drivers.size(); i++){
            Driver d=drivers.get(i);
            if(i<4) d.setAccumulatedTime(penalties[i]);
            else d.setAccumulatedTime(penalties[4]);        
            
            System.out.println(d.getRanking()+".) "+ "poeni:" + d.getAccumulatedPoints() +" (kazna " + d.getAccumulatedTime()+ ")\t"+d.getName() );
        }
    }
    
    private void lap(){     //simulacija 1 kruga
        this.driveAverageLapTime();
        boolean kraj=false;
        while(!kraj){
            System.out.println(this.sec);
            for(Driver d : drivers){
                if(d.getAccumulatedTime()==0){
                    System.out.println("Vodeci igrac na kraju " + this.lapNo + ". kruga je " + d.getName());
                    kraj=true;
                    break;
                }
                else d.setAccumulatedTime(d.getAccumulatedTime()-1);
            }
            this.sec++;
            try {
                Thread.sleep(200); // sleep for 200ms
            }catch(InterruptedException e){     //ako se desi interrupt dok spava
                e.printStackTrace();
            }
        }
    }
    public void race(){    //simulacija 1 trke
        for(lapNo=1;lapNo<=currentVenue.getNumberOfLaps();lapNo++){
            System.out.println("-------------------------------------");
            System.out.println(lapNo+". krug:");
            this.lap();
        }
    }

    public void driveAverageLapTime(){ // svakom vozaču koji vozi trku, dodeli srednje vreme voženja kruga (određeno samom stazom)     
        System.out.println("-------------------------------------");
        System.out.println("Ocekivano vreme potrebno svakom igracu za predstojeci krug:\n");
        for(Driver d : drivers){
            d.setAccumulatedTime(this.currentVenue.getAverageLapTime()+d.getAccumulatedTime());
            System.out.println(d.getName()+" - "+d.getAccumulatedTime() + "s");
        }
        System.out.println("-------------------------------------");
    }
    public void applySpecialSkills(){  // primeni specijalne veštine za svakog vozača u datom krugu
    }
    public void checkMechanicalProblem(){ // proveri da li su neki vozači imali kvar (jedan od tri) i ako treba ažuriraj im vreme voženja kruga, odnosno atribut eligibleToRace

    }
    public void printLeader(int lap){ // ispiši ko je na prvom mestu nakon kruga lap
    }
    public void printWinnersAfterRace(String venueName){ // ispiši imena pobednika (četiri najbolje rangirana vozača) na stazi venueName
    }
    public void printChampion(int numOfRaces){ // ispisati poruku o tome ko je sampion na kraju šampionata, tj. nakon numOfRaces odvozanih trka
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
