package client;

import server.ProsecutorInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Prisoner {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        System.setProperty("java.security.policy", "rmiecho.policy");

        System.out.println("Attempting lookup...");
        String hostname = "localhost";
        int port = 8080;
        String serviceName = "ProsecutorService";
        ProsecutorInterface service = (ProsecutorInterface) Naming.lookup("rmi://" + hostname + ":" + port + "/" + serviceName);    //Looking up register
        System.out.println("Lookup successful.");
        System.out.println(service.sayHello("hello"));  //Acknowledging connection

        Scanner input = new Scanner(System.in);
        int pID = -1;
        while (pID != 0 && pID != 1) {
            System.out.println("Enter prisoner ID (1 or 2):");
            pID = input.nextInt()-1;
            if (!service.checkID(pID)) {    //Checking given prisoner ID is available
                System.out.println("Prisoner ID already in use, please try again.");
                pID = -1;
            }
        }

        char choice = '\0';

        while (choice != 'C' && choice != 'B') {
            System.out.println("Enter your choice (C or B):");
            choice = input.next().charAt(0);
        }

        String reduction = service.processPrisoner(pID, choice);

        if (reduction == null) {
            while (reduction == null) {
                reduction = service.processPrisoner(pID, choice);       //Loops until reduction is received
            }
        }

        System.out.println("You shall receive " + reduction + " years reduction.");
        service.clearChoice(pID);

    }
}
