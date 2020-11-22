package sample;

import
        javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ThreadsChatHandler extends Thread {

    private TextArea chat;
    private String newMessage = null;
    private boolean player;
    private boolean active;

    public ThreadsChatHandler(TextArea chat, boolean isFirstPlayer) {
        this.chat = chat;
        this.player = isFirstPlayer;

        ThreadsChatServer.addHandler(this);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getNewMessage() {
        return newMessage;
    }

    @Override
    public void run() {
        Timer t = new Timer( );
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                if(active) {
                    ArrayList<String> chatHistory = ThreadsChatServer.getChatHistory();
                    ArrayList<Boolean> chatTurns = ThreadsChatServer.getChatTurns();

                    if (newMessage != null) {
                        try {
                            ThreadsChatServer.appendNewMessage(newMessage, player);

                            newMessage = null;
                        } catch (Exception e) {
                            System.out.println("An Error happened: " + e.getMessage());
                        }
                    }

                    StringBuilder chatHistoryStr = new StringBuilder();
                    for (int i = 0; i < chatHistory.size(); i++) {
                        if (player == chatTurns.get(i))
                            chatHistoryStr.append("Me: ");
                        else
                            chatHistoryStr.append("Opponent: ");

                        chatHistoryStr.append(chatHistory.get(i)).append("\n");
                    }

                    if (!chatHistoryStr.toString().equals(chat.getText()))
                        chat.setText(chatHistoryStr.toString());
                }


            }
        }, 500,500);
    }


    public void sendMessage(String newMessage) {
        this.newMessage = newMessage + "\n";
    }


    class ConnectionChecker extends Thread {

        @Override
        public void run() {

            while (true) {

                if(ThreadsChatServer.isTwoPlayers()) {
                    System.out.print("");
                    break;
                }
                else
                    System.out.print("");

            }
        }
    }
}


