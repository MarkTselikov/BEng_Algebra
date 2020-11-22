package sample.RMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerHandler {

    public static void main(String[] args) {

        try {
            LocateRegistry.createRegistry(1099);
            Server server = new Server();
            Naming.rebind("//localhost/QuartoServer", server);
            System.out.println("Server is ready.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
