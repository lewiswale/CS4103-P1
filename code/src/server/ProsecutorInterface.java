package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProsecutorInterface extends Remote {
    String sayHello(String s) throws RemoteException;
    String processPrisoner(char choice) throws RemoteException;
}
