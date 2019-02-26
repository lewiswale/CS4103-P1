package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ProsecutorImpl extends UnicastRemoteObject implements ProsecutorInterface {
    private char p2Choice;
    private char[] choices;
    private final int PRISONERS = 2;
    private int prisoner = 0;

    ProsecutorImpl() throws RemoteException {
        super();
        choices = new char[PRISONERS];
    }

    ProsecutorImpl(char choice) throws RemoteException {
        super();
        this.p2Choice = choice;
        choices = new char[PRISONERS];
        choices[1] = choice;
    }

    @Override
    public String sayHello(String s) throws RemoteException {
        System.out.println("Prisoner says " + s);
        return "Server says hello!";
    }

//    @Override
//    public String processPrisoner(char choice) throws RemoteException {
//        System.out.println("P1 has chosen: " + choice);
//        System.out.println("P2 has chosen: " + p2Choice);
//
//        if (p2Choice == 'C' && choice == 'C') {
//            return "5";
//        }
//
//        if (p2Choice == 'C' && choice == 'B') {
//            return "3";
//        }
//
//        if (p2Choice == 'B' && choice == 'C') {
//            return "2";
//        }
//
//        if (p2Choice == 'B' && choice == 'B') {
//            return "1";
//        }
//
//        return "-1";
//    }

    @Override
    public String processPrisoner(int prisoner, char choice) {
        choices[prisoner] = choice;
        if (choices[0] != '\0' && choices[1] != '\0') {
            if (choices[0] == 'C' && choices[1] == 'C') {
                return "5";
            }

            if (choices[0] == 'C' && choices[1] == 'B') {
                if (prisoner == 0)
                    return "2";
                else
                    return "3";
            }

            if (choices[0] == 'B' && choices[1] == 'C') {
                if (prisoner == 0)
                    return "3";
                else
                    return "2";
            }

            if (choices[0] == 'B' && choices[1] == 'B') {
                return "1";
            }
        }

        return null;
    }

    @Override
    public void clearChoice(int prisoner) {
        choices[prisoner] = '\0';
    }

    @Override
    public boolean checkID(int prisoner) {
        return choices[prisoner] == '\0';
    }
}
