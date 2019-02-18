package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ProsecutorImpl extends UnicastRemoteObject implements ProsecutorInterface {
    protected ProsecutorImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String s) throws RemoteException {
        System.out.println("Prisoner says " + s);
        return "Server says hello!";
    }
}
