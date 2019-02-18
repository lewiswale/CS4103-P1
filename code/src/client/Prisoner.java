package client;

import server.ProsecutorInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Prisoner {
    private static String hostname = "localhost";
    private static int port = 8080;
    private static String serviceName = "ProsecutorService";

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        System.setProperty("java.security.policy", "rmiecho.policy");

        System.out.println("Attempting lookup...");
        ProsecutorInterface service = (ProsecutorInterface) Naming.lookup("rmi://" + hostname + ":" + port + "/" + serviceName);
        System.out.println("Lookup successful.");
        System.out.println(service.sayHello("hello"));

        Scanner input = new Scanner(System.in);
        System.out.println("Enter your choice (C or B):");
        char choice = input.next().charAt(0);

        while (choice != 'C' && choice != 'B') {
            System.out.println("Enter your choice (C or B):");
            choice = input.next().charAt(0);
        }

        String reduction = service.processPrisoner(choice);

        if (reduction.equals("-1")) {
            System.out.println("somethings gone wrong...");
        } else {
            System.out.println("You shall receive " + reduction + " years reduction.");
        }
    }
}
