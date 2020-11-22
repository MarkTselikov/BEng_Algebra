package sample.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {

    void sendMove(int x, int y, int figure) throws RemoteException;

    void sendNextFigure(int figure)throws RemoteException;

    void setTurn(boolean turn) throws RemoteException;

    void startGame() throws RemoteException;
}
