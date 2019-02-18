package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ProsecutorImpl extends UnicastRemoteObject implements ProsecutorInterface {
    private char p2Choice;

    protected ProsecutorImpl(char choice) throws RemoteException {
        super();
        this.p2Choice = choice;
    }

    @Override
    public String sayHello(String s) throws RemoteException {
        System.out.println("Prisoner says " + s);
        return "Server says hello!";
    }

    @Override
    public String processPrisoner(char choice) throws RemoteException {
        System.out.println("P1 has chosen: " + choice);
        System.out.println("P2 has chosen: " + p2Choice);

        if (p2Choice == 'C' && choice == 'C') {
            return "5";
        }

        if (p2Choice == 'C' && choice == 'B') {
            return "3";
        }

        if (p2Choice == 'B' && choice == 'C') {
            return "2";
        }

        if (p2Choice == 'B' && choice == 'B') {
            return "1";
        }

        return "-1";
    }
}
