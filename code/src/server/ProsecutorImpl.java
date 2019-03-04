package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ProsecutorImpl extends UnicastRemoteObject implements ProsecutorInterface {
    private char[] choices;
    private final int PRISONERS = 2;

    /**
     * EXTENSION
     * Constructor for two prisoner control
     * @throws RemoteException
     */
    ProsecutorImpl() throws RemoteException {
        super();
        choices = new char[PRISONERS];
    }

    /**
     * Constructor where choice for 2nd prisoner is chosen by server initialiser.
     * @param choice choice for prisoner 2
     * @throws RemoteException
     */
    ProsecutorImpl(char choice) throws RemoteException {
        super();
        choices = new char[PRISONERS];
        choices[1] = choice;            //Sets P2 choice to predetermined choice
    }

    /**
     * Connction acknowledgement method
     * @param s message received from client
     * @return message back to client
     * @throws RemoteException
     */
    @Override
    public String sayHello(String s) throws RemoteException {
        System.out.println("Prisoner says " + s);
        return "Prosecutor says hello!";
    }

    /**
     * Prisoner processing method
     * @param prisoner prisoner ID to be processed
     * @param choice choice of given prisoner
     * @return sentence reduction of prisoner
     */
    @Override
    public String processPrisoner(int prisoner, char choice) {
        choices[prisoner] = choice;         //array of choices updated
        if (choices[0] != '\0' && choices[1] != '\0') {         //checks if both prisoners have made choices
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

    /**
     * Clears prisoners choice
     * @param prisoner prisoner ID
     */
    @Override
    public void clearChoice(int prisoner) {
        choices[prisoner] = '\0';
    }

    /**
     * checks if prisoner as already made choice
     * @param prisoner prisoner ID
     * @return whether or not given prisoner has made a choice or not
     */
    @Override
    public boolean checkID(int prisoner) {
        return choices[prisoner] == '\0';
    }
}
