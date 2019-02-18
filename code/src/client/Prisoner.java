package client;

import server.ProsecutorInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Prisoner {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        System.setProperty("java.security.policy", "rmiecho.policy");

        System.out.println("Attempting lookup...");
        ProsecutorInterface service = (ProsecutorInterface) Naming.lookup("rmi://localhost:5099/PrisonerService");
        System.out.println("Lookup successful.");
        System.out.println(service.sayHello("hello"));
    }
}
