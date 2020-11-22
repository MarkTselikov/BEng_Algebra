package sample.RMI;

import sample.Board;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements ClientInterface {

    private Board clientUI;

    public Client(Board ui) throws RemoteException {
        clientUI = ui;
    }

    @Override
    public void sendMove(int x, int y, int figure) throws RemoteException {
        clientUI.appendMove(x, y, figure);
    }

    @Override
    public void sendNextFigure(int figure) throws RemoteException {
        clientUI.setNextFigureIndex(figure);
    }

    @Override
    public void setTurn(boolean turn) throws RemoteException {
        clientUI.setTurn(turn);
    }

    public void startGame() throws RemoteException {
        if(clientUI.isPlayerTurn())
            clientUI.changeMenuState(false);
    }
}
