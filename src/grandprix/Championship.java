package grandprix;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
    
    private ArrayList<Driver> drivers;
    private ArrayList<Venue> venues;
    
    //konstruktor treba da cita podatke iz txt fajlova
    public Championship()throws IOException{
        
        	System.out.println("-------------------------------------");

		File f1 = new File("vozaci");	//kreiranje File objekta koji reprezentuje vozaci.txt fajl
		//provera da li postoji fajl
                if(f1.exists()){
			//koristimo reader, a ne stream, da bi bilo sve korektno sa UTF8 enkodingom
			//reader je buffer-izovan zbog performansi
			BufferedReader in1 = new BufferedReader(new InputStreamReader(new FileInputStream( f1), "UTF8")); 
			String s2;
			while((s2 = in1.readLine()) != null) {
				//da bi se videla cirilicna slova, mora se podesiti enkoding 
				//ici na Run->Run configurations->Common->Encoding->Other UTF8
				System.out.println(s2);
			}
			in1.close();
		} else {
			System.out.println("Ne postoji fajl!");
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
