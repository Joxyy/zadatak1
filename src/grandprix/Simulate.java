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
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        Championship f1 = new Championship();
        System.out.println("Trenutno stanje na tabeli:\n");
        f1.getAllDrivers();
         
        
        System.out.println("Koliko trka ce se voziti (3÷5)");
        
        int raceNum = 0;
        boolean notRead = true;
        do {
                if (sc.hasNextInt()) {
                        raceNum = sc.nextInt();
                        if(raceNum>=3 && raceNum<= 5) notRead = false;
                        else System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
                } else {
                        System.out.println("GRESKA - Pogresno unsesena vrednost, pokusajte ponovo: ");
                }
                sc.nextLine(); //cisti sve sa ulaza sto nije broj ili ostatak teste posla broja
        } while (notRead);
        
        System.out.println("-------------------------------------");
        System.out.println("Odaberite prvu stazu (uneti pun naziv):\n");
        f1.getAllVenues();
        System.out.print("-->");
        String venueName = sc.nextLine();
        while(f1.venueSelection(venueName)) venueName = sc.nextLine();
        
    }
    
}
