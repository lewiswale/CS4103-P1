package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GameServer {

    public static void main(String[] args) throws RemoteException {
        System.setProperty("java.security.policy", "rmiecho.policy");
        System.setProperty("java.rmi.server.hostname", "localhost");

        System.out.println("Attempting registration...");
        Registry registry = LocateRegistry.createRegistry(5099);
        registry.rebind("PrisonerService", new ProsecutorImpl());
        System.out.println("Successfully registered.");
    }
}
