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
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        Championship f1 = new Championship();
        

        System.out.println("Desired number of races (3-5): ");
        int numOfRaces = 0;
        
        boolean notRead = true;
        do {
                if (sc.hasNextInt()) {
                        numOfRaces = sc.nextInt();
                        if(numOfRaces>=3 && numOfRaces<= 5) notRead = false;
                        else System.out.println("ERROR - Wrong value, try again: ");
                } else {
                        System.out.println("ERROR - Wrong value, try again: ");
                }
                sc.nextLine(); //cisti sve sa ulaza sto nije broj ili ostatak teste posla broja
        } while (notRead);
        
        for(int i=1;i<=numOfRaces;i++){
            System.out.println("-------------------------------------");
            System.out.println("Choose " + i + ". venue (full name):\n");
            f1.printAllVenues();
            String venueName = sc.nextLine();
            while(f1.venueSelection(venueName)) venueName = sc.nextLine();
            System.out.println("-------------------------------------");

            System.out.println("-------------------------------------");
            System.out.println(i + ". trka:");
            System.out.println("-------------------------------------");
            //u for petlji do raceNum trka
            f1.race();
        }
        f1.printChampion(numOfRaces);
        
    }
    
}
