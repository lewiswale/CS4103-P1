package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class GameServer {
    private static char p2Choice;

    public static void main(String[] args) throws RemoteException {
        Scanner input = new Scanner(System.in);

        int numberOFPrisoners = -1;

        while (numberOFPrisoners != 1 && numberOFPrisoners != 2) {
            System.out.println("Enter how many prisoners there will be (1 or 2):");
            numberOFPrisoners = input.nextInt();
        }

        if (numberOFPrisoners == 1) {
            p2Choice = '\0';

            while (p2Choice != 'B' && p2Choice != 'C') {
                System.out.println("Enter P2 choice (B or C):");
                p2Choice = input.next().charAt(0);
            }
        }

        System.setProperty("java.security.policy", "rmiecho.policy");

        System.out.println("Attempting registration...");
        int port = 8080;
        Registry registry = LocateRegistry.createRegistry(port);
        String service = "ProsecutorService";
        if (numberOFPrisoners == 2) {
            registry.rebind(service, new ProsecutorImpl());
        } else {
            registry.rebind(service, new ProsecutorImpl(p2Choice));
        }
        System.out.println("Successfully registered.");
        System.out.println("Listening...");
    }
}
