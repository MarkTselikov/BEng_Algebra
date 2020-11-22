package sample;

import Model.Indexing;

import java.util.ArrayList;

public class ThreadsChatServer {

    private static boolean firstPlayerJoined = false;
    private static boolean twoPlayers = false;
    private static int nextFigure = Indexing.EMPTY;
    private static boolean chatInProgress = false;

    private static ArrayList<Boolean> chatTurns = new ArrayList<>();
    private static ArrayList<String> chatHistory = new ArrayList<>();
    private static ArrayList<ThreadsChatHandler> handlers = new ArrayList<>();

    public static void addHandler(ThreadsChatHandler h) {
        handlers.add(h);
    }

    public static ArrayList<ThreadsChatHandler> getHandlers() { return handlers; }

    public static int getNextFigure() {
        return nextFigure;
    }

    public static ArrayList<String> getChatHistory() {
        return chatHistory;
    }

    public static boolean isFirstPlayerJoined() {
        return firstPlayerJoined;
    }

    public static boolean isTwoPlayers() {
        return twoPlayers;
    }

    public static ArrayList<Boolean> getChatTurns() {
        return chatTurns;
    }

    public static void setNextFigure(int nextFigure) {
        ThreadsChatServer.nextFigure = nextFigure;
    }

    public static void setTwoPlayers(boolean twoPlayers) {
        ThreadsChatServer.twoPlayers = twoPlayers;
    }

    public static void appendNewMessage(String message, boolean player) {

        ChatUpdater cu = new ChatUpdater(message, player);
        cu.start();
        synchronized (chatHistory) {
            if(chatInProgress) {
                try { cu.wait(); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }

    public static void setFirstPlayerJoined(boolean firstPlayerJoined) {
        ThreadsChatServer.firstPlayerJoined = firstPlayerJoined;
    }

    public static boolean isChatInProgress() {
        return chatInProgress;
    }

    public static void setChatInProgress(boolean chatInProgress) {
        ThreadsChatServer.chatInProgress = chatInProgress;
    }


    static class ChatUpdater extends Thread {

        private String message;
        private boolean player;

        public ChatUpdater(String message, boolean player) {
            this.message = message;
            this.player = player;
        }

        @Override
        public void run() {

            chatInProgress = true;
            chatHistory.add(message);
            chatTurns.add(player);
            if(chatInProgress) {
                synchronized (this) {
                    notifyAll();
                    chatInProgress = false;
                }
            }
        }
    }
}
