package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GameServer {
    private static char p2Choice;
    private static int port = 8080;
    private static String service = "ProsecutorService";

    public static void main(String[] args) throws RemoteException {
        if (args.length != 1) {
            System.out.println("Please give P2 choice.");
            System.exit(1);
        } else {
            if (args[0].charAt(0) != 'C' && args[0].charAt(0) != 'B') {
                System.out.println("Choice needs to be either B or C");
                System.exit(1);
            }
        }

        p2Choice = args[0].charAt(0);

        System.setProperty("java.security.policy", "rmiecho.policy");

        System.out.println("Attempting registration...");
        Registry registry = LocateRegistry.createRegistry(port);
        registry.rebind(service, new ProsecutorImpl(p2Choice));
        System.out.println("Successfully registered.");
    }
}
