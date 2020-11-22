package sample.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    void makeMove(int x, int y, int figure) throws RemoteException;

    void selectNextFigure(int figure) throws RemoteException;

    void connect(ClientInterface client) throws RemoteException;

    boolean isFirstPlayerConnected() throws RemoteException;

    boolean isTwoPlayersConnected() throws RemoteException;
}
