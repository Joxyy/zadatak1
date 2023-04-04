package grandprix;


import java.io.IOException;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author joxy
 */
public class Simulate {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        Championship f1 = new Championship();
        
        
        System.out.println("Učitani podaci o igracima:\n");
        f1.printAllDrivers();
        System.out.println("-------------------------------------");
        System.out.println("Učitani podaci o stazama:\n");
        f1.printAllVenues();
        System.out.println("-------------------------------------");
        
        System.out.println("Koliko trka ce se voziti (3÷5)");
        int raceNum = 0;
        boolean notRead = true;
        do {
                if (sc.hasNextInt()) {
                        raceNum = sc.nextInt();
                        if(raceNum>=3 && raceNum<= 5) notRead = false;
                        else System.out.println("GRESKA - Pogresno unesena vrednost, pokusajte ponovo: ");
                } else {
                        System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
                }
                sc.nextLine(); //cisti sve sa ulaza sto nije broj ili ostatak teste posla broja
        } while (notRead);
        System.out.println("-------------------------------------");
        
        System.out.println("Odaberite prvu stazu (uneti pun naziv):");
        String venueName = sc.nextLine();
        while(f1.venueSelection(venueName)) venueName = sc.nextLine();
        System.out.println("-------------------------------------");
        
        System.out.println("Priprema za prvu stazu:\n");
        f1.prepareForTheRace();
        System.out.println("-------------------------------------");
        System.out.println(raceNum + ". trka:");
        //u for petlji do raceNum trka
        f1.race();
        
        
    }
    
}
