package sample.RMI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends UnicastRemoteObject implements ServerInterface {

    private boolean firstPlayerConnected = false;
    private boolean twoPlayersConnected = false;

    private List<ClientInterface> clients = new ArrayList<>();
    private static final Logger LOG = Logger.getLogger(Server.class.getName());

    public Server() throws RemoteException { }

    public boolean isFirstPlayerConnected() throws RemoteException {
        return firstPlayerConnected;
    }

    public boolean isTwoPlayersConnected() throws RemoteException {
        return twoPlayersConnected;
    }

    @Override
    public void makeMove(int x, int y, int figure) throws RemoteException {
        for (ClientInterface c : clients) {
            c.sendMove(x, y, figure);
        }
    }

    @Override
    public void selectNextFigure(int figure) throws RemoteException {
        for (ClientInterface c : clients) {
            c.sendNextFigure(figure);
        }
    }

    @Override
    public void connect(ClientInterface client) throws RemoteException {

        clients.add(client);

        if(!twoPlayersConnected && firstPlayerConnected){
            System.out.println("Setting up the second player...");
            twoPlayersConnected = true;
            client.setTurn(false);

            for(ClientInterface c : clients) {
                c.startGame();
            }
        }

        if(!firstPlayerConnected) {
            System.out.println("Setting up the first player...");
            firstPlayerConnected = true;
            client.setTurn(true);
        }

        LOG.log(Level.INFO, "New client has connected to the server");
    }


}



