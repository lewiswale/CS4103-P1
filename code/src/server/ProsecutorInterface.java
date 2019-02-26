package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ProsecutorInterface extends Remote {
    String sayHello(String s) throws RemoteException;
    String processPrisoner(int prisoner, char choice) throws RemoteException;
    void clearChoice(int prisoner) throws RemoteException;
    boolean checkID(int prisoner) throws RemoteException;
}
