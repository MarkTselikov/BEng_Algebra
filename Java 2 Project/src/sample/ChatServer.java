package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer {

    private static final Logger LOG = Logger.getLogger(ChatServer.class.getName());
    private static List<PrintWriter> clients = new ArrayList<>();
    private static List<String> chatHistory = new ArrayList<>();

    public static void addClient(PrintWriter c) {
        clients.add(c);
    }

    public static void appendMessage(String message) { chatHistory.add(message); }

    public static List<String> getChatHistory() {
        return chatHistory;
    }

    public static List<PrintWriter> getClients() {
        return clients;
    }

    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(12345);

            while (true) {
                Socket cs = ss.accept();

                ConnectionThread gt = new ConnectionThread(cs);
                gt.start();

                System.out.println("A player has connected.");
            }
        } catch (Exception e) {
            LOG.warning("\nAn error occurred in the main function \n" + e.getMessage());
        }
    }
}


class ConnectionThread extends Thread {

    private static final Logger LOG = Logger.getLogger(ConnectionThread.class.getName());

    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;

    ConnectionThread(Socket s) throws IOException {

        socket = s;
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        ChatServer.addClient(pw);
    }

    @Override
    public void run() {

        try {
            while (true) {

                String message = br.readLine();
                if(message != null) {
                    System.out.println("Received a new message: " + message);
                    ChatServer.appendMessage(message);

                    for (PrintWriter c : ChatServer.getClients()) {

                        c.println(message);
                        c.flush();
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("\nAn error occurred in the chat handler \n" + e.getMessage());
            e.printStackTrace();
        }

        finally {
            try {
                socket.close();
            } catch (IOException ex1) {
                LOG.log(Level.WARNING, ex1.getMessage(), ex1);
            }
        }
    }
}
