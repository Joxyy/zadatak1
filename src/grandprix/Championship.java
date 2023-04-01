package grandprix;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
    
    public static ArrayList<Driver> drivers = new ArrayList<Driver>();
    public static ArrayList<Venue> venues = new ArrayList<Venue>();
    
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
    public static void getAllDrivers(){
            for (int i = 0; i < drivers.size(); i++) {
                    System.out.println(drivers.get(i));
            }
    }
    public static void getAllVenues(){
        for (int i = 0; i < venues.size(); i++) {
                System.out.println(venues.get(i));
        }
    }
    
    public void prepareForTheRace(){ // inicijalizacija atributa za svakog vozaca
    }
    public void driveAverageLapTime(){ // svakom vozaču koji vozi trku, dodeli srednje vreme voženja kruga (određeno samom stazom)     
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

    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }

    public void setVenues(ArrayList<Venue> venues) {
        this.venues = venues;
    }
    
    
}
