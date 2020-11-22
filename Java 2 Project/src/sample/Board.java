package sample;

import Model.*;
import Model.Cell;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import sample.RMI.Client;
import sample.RMI.ServerInterface;
import sample.XML.MovesLogger;
import sample.XML.SaxHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Board {

    //
    //// Variables and utilities for the game

    private String ICONS_PATH;

    private boolean isFirstPlayer;
    private boolean isPlayerTurn;
    private boolean availableMove;
    private boolean firstTurn;
    private boolean gameFinished = false;
    private boolean replayMode;
    private int nextFigureIndex = Indexing.EMPTY;

    private boolean onlineChatOn = true;
    private String onlineChatHistory = "";

    private int [][] boardState;

    private Socket socket;
    private PrintWriter outChat;
    private BufferedReader inChat;

    private ThreadsChatHandler threadsChatHandler;
    private ChatConnector onlineChatHandler;

    private Client client;
    private ServerInterface server;

    private MovesLogger logger;

    //
    //// Definition of FXML controls

    @FXML
    private GridPane board;

    @FXML
    private TextArea taChat;

    @FXML
    private CheckBox onlineChat;

    @FXML
    private TextField tfMessage;

    @FXML
    private Button btnSendMessage;

    @FXML
    private Button btnShortSolid;

    @FXML
    private Button btnShortHollow;

    @FXML
    private Button btnTallSolid;

    @FXML
    private Button btnTallHollow;

    @FXML
    private Button btnShortSolidSquare;

    @FXML
    private Button btnShortHollowSquare;

    @FXML
    private Button btnTallSolidSquare;

    @FXML
    private Button btnTallHollowSquare;

    @FXML
    private Button btnBShortSolid;

    @FXML
    private Button btnBShortHollow;

    @FXML
    private Button btnBTallSolid;

    @FXML
    private Button btnBTallHollow;

    @FXML
    private Button btnBShortSolidSquare;

    @FXML
    private Button btnBShortHollowSquare;

    @FXML
    private Button btnBTallSolidSquare;

    @FXML
    private Button btnBTallHollowSquare;

    @FXML
    private Button btnLoadGame;

    @FXML
    private Button btnSaveGame;

    @FXML
    private Label stateLabel;

    @FXML
    private ImageView nextFigureIcon;


    //
    //// Definition of actions for the FXML controls

    @FXML
    private void btnMessageAction() {
        if(onlineChatOn) {
            outChat.println(tfMessage.getText() + "\n\n");
            outChat.flush();
        }
        else {
            threadsChatHandler.sendMessage(tfMessage.getText());
        }

        tfMessage.setText("");

    }

    @FXML
    private void chatModeChange() {

        if(onlineChatOn) {

            onlineChatHistory = taChat.getText();
            taChat.setText("");
            threadsChatHandler.setActive(true);
        }

        else {

            threadsChatHandler.setActive(false);
            taChat.setText(onlineChatHistory);
        }

        onlineChatOn = !onlineChatOn;
    }

    @FXML
    private void btnSaveAction() {

        List<Cell> cellList = new ArrayList<>();
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                cellList.add((Cell) getNodeFromGridPane(board, i, j));
            }
        }
        GameState game = new GameState(cellList, boardState, isPlayerTurn);
        GameSerializer.saveGame(game);

        System.out.println("Game saved successfully");
    }

    @FXML
    private void btnLoadAction() {

        GameState game = GameSerializer.loadGame();
        firstTurn = game.getPlayerTurn();
        boardState = game.getBoardState();

        updateInterfaceOnLoad();
        System.out.println("Game loaded successfully");
    }

    @FXML
    private void btnShortSolidAction() {
        menuBtnPressed(Indexing.WHITE_SHORT_SOLID_CIRCLE, btnShortSolid);
    }

    @FXML
    private void btnShortHollowAction() {
        menuBtnPressed(Indexing.WHITE_SHORT_HOLLOW_CIRCLE, btnShortHollow);
    }

    @FXML
    private void btnTallSolidAction() {
        menuBtnPressed(Indexing.WHITE_TALL_SOLID_CIRCLE, btnTallSolid);
    }

    @FXML
    private void btnShortSolidSquareAction() {
        menuBtnPressed(Indexing.WHITE_SHORT_SOLID_SQUARE, btnShortSolidSquare);
    }

    @FXML
    private void btnShortHollowSquareAction() {
        menuBtnPressed(Indexing.WHITE_SHORT_HOLLOW_SQUARE, btnShortHollowSquare);
    }

    @FXML
    private void btnTallSolidSquareAction() {
        menuBtnPressed(Indexing.WHITE_TALL_SOLID_SQUARE, btnTallSolidSquare);
    }

    @FXML
    private void btnTallHollowSquareAction() {
        menuBtnPressed(Indexing.WHITE_TALL_HOLLOW_SQUARE, btnTallHollowSquare);
    }

    @FXML
    private void btnTallHollowAction() {
        menuBtnPressed(Indexing.WHITE_TALL_HOLLOW_CIRCLE, btnTallHollow);
    }

    @FXML
    private void btnBShortSolidAction() {
        menuBtnPressed(Indexing.BLACK_SHORT_SOLID_CIRCLE, btnBShortSolid);
    }

    @FXML
    private void btnBShortHollowAction() {
        menuBtnPressed(Indexing.BLACK_SHORT_HOLLOW_CIRCLE, btnBShortHollow);
    }

    @FXML
    private void btnBTallSolidAction() {
        menuBtnPressed(Indexing.BLACK_TALL_SOLID_CIRCLE, btnBTallSolid);
    }

    @FXML
    private void btnBShortSolidSquareAction() {
        menuBtnPressed(Indexing.BLACK_SHORT_SOLID_SQUARE, btnBShortSolidSquare);
    }

    @FXML
    private void btnBShortHollowSquareAction() {
        menuBtnPressed(Indexing.BLACK_SHORT_HOLLOW_SQUARE, btnBShortHollowSquare);
    }

    @FXML
    private void btnBTallSolidSquareAction() {
        menuBtnPressed(Indexing.BLACK_TALL_SOLID_SQUARE, btnBTallSolidSquare);
    }

    @FXML
    private void btnBTallHollowSquareAction() {
        menuBtnPressed(Indexing.BLACK_TALL_HOLLOW_SQUARE, btnBTallHollowSquare);
    }

    @FXML
    private void btnBTallHollowAction() {
        menuBtnPressed(Indexing.BLACK_TALL_HOLLOW_CIRCLE, btnBTallHollow);
    }


    public void clickGrid(MouseEvent event) {

        /*Node source = (Node)event.getSource();
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);

        try {
            String path = ICONS_PATH + nextFigureIndex + ".png";
            Image image = new Image(getClass().getResource(path).toURI().toString());
            //board.add(new ImageView(image), colIndex, rowIndex);
            System.out.printf("Mouse clicked cell [%d, %d]%n", colIndex.intValue(), rowIndex.intValue());
            board.add(new Button("TEST"), colIndex, rowIndex);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }


    //
    //// Definition of game flow functions

    @FXML
    public void initialize() {

        boardState = new int[4][4];
        firstTurn = true;

         ICONS_PATH = JNDIHandler.getConfigParam("ICONS_PATH");

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {

                Cell c = new Cell();
                c.initCell(j, i);
                c.setOnMouseClicked((MouseEvent t) -> {
                    if(!c.isOccupied() && availableMove && isPlayerTurn) {

                        Image image = getFigureIcon(nextFigureIndex);

                        ImageView view = new ImageView(image);
                        view.setFitHeight(100);
                        view.setFitWidth(100);

                        try { server.makeMove(c.getY(), c.getX(), nextFigureIndex); }
                        catch (RemoteException e) { e.printStackTrace(); }

                        availableMove = false;

                        btnLoadGame.setDisable(true);
                        stateLabel.setText("Select a figure for your opponent");
                        nextFigureIcon.setImage(null);
                    }
                });

                board.add(c, j, i);
                boardState[j][i] = Indexing.EMPTY;
            }
        }

        if(MainMenu.replayMode) {

            MainMenu.replayMode = false;
            replayMode = true;

            btnSendMessage.setDisable(true);
            onlineChat.setDisable(true);
            btnLoadGame.setDisable(true);
            btnSaveGame.setDisable(true);

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Replay File");
            fileChooser.setInitialDirectory(new File("GameRepeats"));
            String filePath = fileChooser.showOpenDialog(null).getPath();

            GameRepeater repeater = new GameRepeater(this);
            repeater.init(filePath);
            repeater.replay();
        }
        else {
            try {
                connectToChat();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Couldn't connect to the online chat, but you can still use the offline one");
                alert.showAndWait();
            }

            if (!ThreadsChatServer.isFirstPlayerJoined()) {
                ThreadsChatServer.setFirstPlayerJoined(true);
                isFirstPlayer = true;
            } else {
                ThreadsChatServer.setTwoPlayers(true);
                stateLabel.setText("You are the second player, wait for you opponent's move");
            }

            threadsChatHandler = new ThreadsChatHandler(taChat, isFirstPlayer);
            threadsChatHandler.setActive(false);
            threadsChatHandler.start();

            changeMenuState(true);
            isPlayerTurn = true;
            availableMove = false;
            nextFigureIndex = Indexing.EMPTY;

            connectToServer();

            logger = new MovesLogger();
            logger.createDocument();
        }
    }


    private void connectToChat() throws IOException {

        Socket s = new Socket("localhost", 12345);
        onlineChatHandler = new ChatConnector(s);
        onlineChatHandler.start();
    }


    private void switchTurns() {

        if(isPlayerTurn)
            isPlayerTurn = false;
        else
            isPlayerTurn = true;

        //try {
        Platform.runLater(() -> {
            stateLabel.setText("Your next figure: ");
        });
        //}
        //catch (Exception e) { System.out.println(""); }

        availableMove = true;
        firstTurn = false;

        if(gameFinished)
            changeMenuState(true);
    }


    public void changeMenuState(boolean state) {

        if(gameFinished)
            state = true;

        btnShortHollow.setDisable(state);
        btnShortSolid.setDisable(state);
        btnTallHollow.setDisable(state);
        btnTallSolid.setDisable(state);
        btnShortHollowSquare.setDisable(state);
        btnShortSolidSquare.setDisable(state);
        btnTallHollowSquare.setDisable(state);
        btnTallSolidSquare.setDisable(state);

        btnBShortHollow.setDisable(state);
        btnBShortSolid.setDisable(state);
        btnBTallHollow.setDisable(state);
        btnBTallSolid.setDisable(state);
        btnBShortHollowSquare.setDisable(state);
        btnBShortSolidSquare.setDisable(state);
        btnBTallHollowSquare.setDisable(state);
        btnBTallSolidSquare.setDisable(state);
    }


    private void menuBtnPressed(int nextFigureIndex, Button source) {

        if(replayMode) {

        }
        else {
            try { server.selectNextFigure(nextFigureIndex); }
            catch (RemoteException e) { e.printStackTrace(); }
            changeMenuState(true);
            source.setVisible(false);
        }
    }


    private Image getFigureIcon(int figureIndex) {
        try {

            String path = ICONS_PATH + figureIndex + ".png";
            Image image = new Image(getClass().getResource(path).toURI().toString());
            return image;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    private boolean hasFreeSpace() {
        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {

                if(boardState[i][j] == Indexing.EMPTY)
                    return true;
            }
        }

        return false;
    }


    private boolean checkForVictory() {
        return colorCondition() || heightCondition() || fillCondition() || shapeCondition();
    }


    private boolean colorCondition() {

        int[] board = transformBoard();

        for (int[] combo : Indexing.VICTORY_CONDITIONS){
            int pos1 = board[combo[0]] / 1000;
            int pos2 = board[combo[1]] / 1000;
            int pos3 = board[combo[2]] / 1000;
            int pos4 = board[combo[3]] / 1000;

            if(pos1 == pos2 && pos2 == pos3 && pos3 == pos4 && pos1 != 0)
                return true;
        }

        return false;
    }


    private boolean heightCondition() {

        int[] board = transformBoard();

        for (int[] combo : Indexing.VICTORY_CONDITIONS){
            int pos1 = (board[combo[0]] / 100) % 10;
            int pos2 = (board[combo[1]] / 100) % 10;
            int pos3 = (board[combo[2]] / 100) % 10;
            int pos4 = (board[combo[3]] / 100) % 10;

            if(pos1 == pos2 && pos2 == pos3 && pos3 == pos4 && pos1 != 0)
                return true;
        }

        return false;
    }


    private boolean fillCondition() {

        int[] board = transformBoard();

        for (int[] combo : Indexing.VICTORY_CONDITIONS){
            int pos1 = (board[combo[0]] % 100) / 10;
            int pos2 = (board[combo[1]] % 100) / 10;
            int pos3 = (board[combo[2]] % 100) / 10;
            int pos4 = (board[combo[3]] % 100) / 10;

            if(pos1 == pos2 && pos2 == pos3 && pos3 == pos4 && pos1 != 0)
                return true;
        }

        return false;
    }


    private boolean shapeCondition() {

        int[] board = transformBoard();

        for (int[] combo : Indexing.VICTORY_CONDITIONS){
            int pos1 = board[combo[0]] % 10;
            int pos2 = board[combo[1]] % 10;
            int pos3 = board[combo[2]] % 10;
            int pos4 = board[combo[3]] % 10;

            if(pos1 == pos2 && pos2 == pos3 && pos3 == pos4 && pos1 != Indexing.EMPTY)
                return true;
        }

        return false;
    }


    private int[] transformBoard() {
        int count = 0;
        int [] flatBoard = new int[16];
        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                flatBoard[count] = boardState[i][j];
                count++;
            }
        }
        return flatBoard;
    }


    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if(node instanceof Cell) {
                if (GridPane.getColumnIndex(node) == col
                        && GridPane.getRowIndex(node) == row
                        && GridPane.getColumnIndex(node) != null
                        && GridPane.getColumnIndex(node) != null) {
                    return node;
                }
            }
        }
        return null;
    }


    private void updateInterfaceOnLoad() {

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {

                int c = boardState[i][j];
                switch (c) {
                    case Indexing.WHITE_SHORT_SOLID_SQUARE:
                        btnShortSolidSquareAction();
                        break;
                    case Indexing.WHITE_SHORT_HOLLOW_SQUARE:
                        btnShortHollowSquareAction();
                        break;
                    case Indexing.WHITE_TALL_SOLID_SQUARE:
                        btnTallSolidSquareAction();
                        break;
                    case Indexing.WHITE_TALL_HOLLOW_SQUARE:
                        btnTallHollowSquareAction();
                        break;
                    case Indexing.WHITE_SHORT_SOLID_CIRCLE:
                        btnShortSolidAction();
                        break;
                    case Indexing.WHITE_SHORT_HOLLOW_CIRCLE:
                        btnShortHollowAction();
                        break;
                    case Indexing.WHITE_TALL_SOLID_CIRCLE:
                        btnTallSolidAction();
                        break;
                    case Indexing.WHITE_TALL_HOLLOW_CIRCLE:
                        btnTallHollowAction();
                        break;
                    case Indexing.BLACK_SHORT_SOLID_SQUARE:
                        btnBShortSolidSquareAction();
                        break;
                    case Indexing.BLACK_SHORT_HOLLOW_SQUARE:
                        btnBShortHollowSquareAction();
                        break;
                    case Indexing.BLACK_TALL_SOLID_SQUARE:
                        btnBTallSolidSquareAction();
                        break;
                    case Indexing.BLACK_TALL_HOLLOW_SQUARE:
                        btnTallHollowSquareAction();
                        break;
                    case Indexing.BLACK_SHORT_SOLID_CIRCLE:
                        btnBShortSolidAction();
                        break;
                    case Indexing.BLACK_SHORT_HOLLOW_CIRCLE:
                        btnBShortHollowAction();
                        break;
                    case Indexing.BLACK_TALL_SOLID_CIRCLE:
                        btnBTallSolidAction();
                        break;
                    case Indexing.BLACK_TALL_HOLLOW_CIRCLE:
                        btnBTallHollowAction();
                        break;
                }

                if(nextFigureIndex != Indexing.EMPTY) {

                    Cell cell = new Cell();
                    Image image = getFigureIcon(nextFigureIndex);

                    ImageView view = new ImageView(image);
                    view.setFitHeight(100);
                    view.setFitWidth(100);

                    cell.initCell(i, j);
                    cell.getChildren().add(view);
                    cell.setOccupied(true);

                    board.add(cell, j, i);

                    changeMenuState(false);
                    availableMove = false;
                }

                nextFigureIndex = Indexing.EMPTY;
            }
        }

        stateLabel.setText("Select a figure for your opponent");
        nextFigureIcon.setImage(null);
        nextFigureIndex = Indexing.EMPTY;
    }


    public void setNextFigureIndex(int figure) {
        nextFigureIndex = figure;
        switchTurns();

        switch (figure) {
            case Indexing.WHITE_SHORT_SOLID_SQUARE:
                btnShortSolidSquare.setDisable(true);
                break;
            case Indexing.WHITE_SHORT_HOLLOW_SQUARE:
                btnShortHollowSquare.setDisable(true);
                break;
            case Indexing.WHITE_TALL_SOLID_SQUARE:
                btnTallSolidSquare.setDisable(true);
                break;
            case Indexing.WHITE_TALL_HOLLOW_SQUARE:
                btnTallHollowSquare.setDisable(true);
                break;
            case Indexing.WHITE_SHORT_SOLID_CIRCLE:
                btnShortSolid.setDisable(true);
                break;
            case Indexing.WHITE_SHORT_HOLLOW_CIRCLE:
                btnShortHollow.setDisable(true);
                break;
            case Indexing.WHITE_TALL_SOLID_CIRCLE:
                btnTallSolid.setDisable(true);
                break;
            case Indexing.WHITE_TALL_HOLLOW_CIRCLE:
                btnTallHollow.setDisable(true);
                break;
            case Indexing.BLACK_SHORT_SOLID_SQUARE:
                btnBShortSolidSquare.setDisable(true);
                break;
            case Indexing.BLACK_SHORT_HOLLOW_SQUARE:
                btnBShortHollowSquare.setDisable(true);
                break;
            case Indexing.BLACK_TALL_SOLID_SQUARE:
                btnBTallSolidSquare.setDisable(true);
                break;
            case Indexing.BLACK_TALL_HOLLOW_SQUARE:
                btnTallHollowSquare.setDisable(true);
                break;
            case Indexing.BLACK_SHORT_SOLID_CIRCLE:
                btnBShortSolid.setDisable(true);
                break;
            case Indexing.BLACK_SHORT_HOLLOW_CIRCLE:
                btnBShortHollow.setDisable(true);
                break;
            case Indexing.BLACK_TALL_SOLID_CIRCLE:
                btnBTallSolid.setDisable(true);
                break;
            case Indexing.BLACK_TALL_HOLLOW_CIRCLE:
                btnBTallHollow.setDisable(true);
                break;
        }
    }


    public void appendMove(int x, int y, int figure) {

        switch (figure) {
            case Indexing.WHITE_SHORT_SOLID_SQUARE:
                btnShortSolidSquareAction();
                break;
            case Indexing.WHITE_SHORT_HOLLOW_SQUARE:
                btnShortHollowSquareAction();
                break;
            case Indexing.WHITE_TALL_SOLID_SQUARE:
                btnTallSolidSquareAction();
                break;
            case Indexing.WHITE_TALL_HOLLOW_SQUARE:
                btnTallHollowSquareAction();
                break;
            case Indexing.WHITE_SHORT_SOLID_CIRCLE:
                btnShortSolidAction();
                break;
            case Indexing.WHITE_SHORT_HOLLOW_CIRCLE:
                btnShortHollowAction();
                break;
            case Indexing.WHITE_TALL_SOLID_CIRCLE:
                btnTallSolidAction();
                break;
            case Indexing.WHITE_TALL_HOLLOW_CIRCLE:
                btnTallHollowAction();
                break;
            case Indexing.BLACK_SHORT_SOLID_SQUARE:
                btnBShortSolidSquareAction();
                break;
            case Indexing.BLACK_SHORT_HOLLOW_SQUARE:
                btnBShortHollowSquareAction();
                break;
            case Indexing.BLACK_TALL_SOLID_SQUARE:
                btnBTallSolidSquareAction();
                break;
            case Indexing.BLACK_TALL_HOLLOW_SQUARE:
                btnTallHollowSquareAction();
                break;
            case Indexing.BLACK_SHORT_SOLID_CIRCLE:
                btnBShortSolidAction();
                break;
            case Indexing.BLACK_SHORT_HOLLOW_CIRCLE:
                btnBShortHollowAction();
                break;
            case Indexing.BLACK_TALL_SOLID_CIRCLE:
                btnBTallSolidAction();
                break;
            case Indexing.BLACK_TALL_HOLLOW_CIRCLE:
                btnBTallHollowAction();
                break;
        }

        Platform.runLater(() -> {
            Cell cell = new Cell();
            Image image = getFigureIcon(nextFigureIndex);

            ImageView view = new ImageView(image);
            view.setFitHeight(100);
            view.setFitWidth(100);

            cell.initCell(x, y);
            cell.getChildren().add(view);
            cell.setOccupied(true);

            board.add(cell, y, x);
            boardState[y][x] = figure;

            if(!firstTurn && checkForVictory()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        isPlayerTurn ? "You won!" : "You've lost");
                alert.showAndWait();
                gameFinished = true;
                changeMenuState(true);
                try { logger.saveDocument(); }
                catch (TransformerException e) {
                    System.out.println("Couldn't save the game repeat file");
                }
            }

            if(!hasFreeSpace()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "It's a Draw");
                alert.showAndWait();
                gameFinished = true;
            }

            if(isPlayerTurn)
                changeMenuState(false);
            availableMove = false;
        });

        logger.appendMove(new Move(x, y, figure));
    }


    public void setTurn(boolean turn) {
        isPlayerTurn = turn;
    }


    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }


    private void connectToServer() {
        try {

            client = new Client(this);
            server = (ServerInterface) Naming.lookup("//localhost/QuartoServer");

            server.connect(client);
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "There was an error while connecting to the game server");
            alert.showAndWait();
        }
    }



    class ChatConnector extends Thread {

        private boolean state;

        public ChatConnector(Socket s) {

            socket = s;

            try {
                outChat = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                inChat = new BufferedReader(new InputStreamReader(s.getInputStream()));
            }
            catch (Exception e) {
                System.out.println("Something went wrong in the chat handler constructor: " + e.getMessage());
            }
        }


        @Override
        public void run() {

            try {

                String message;
                while (onlineChatOn) {
                    while ((message = inChat.readLine()) != null) {

                        taChat.appendText(message);
                    }
                }
            }
            catch (Exception e) {
                System.out.println("Something went wrong in the chat handler: " + e.getMessage());
                e.printStackTrace();
            }
            finally {
                try {
                    socket.close();
                    outChat.close();
                    inChat.close();
                } catch (IOException ex1) {
                    System.out.println(ex1.getMessage());
                }
            }
        }
    }



    class GameRepeater  {

        private Board ui;
        private List<Move> movesList = new ArrayList<>();

        public GameRepeater(Board ui) {
            this.ui = ui;
        }

        public void init(String filePath) {

            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();

                InputStream xmlInput  = null;
                xmlInput = new FileInputStream(filePath);

                SAXParser saxParser = factory.newSAXParser();
                SaxHandler handler   = new SaxHandler();
                saxParser.parse(xmlInput, handler);

                movesList = handler.getMovesList();
            }
            catch (Exception e) { e.printStackTrace(); }
        }

        public void replay(){
            Timer t = new Timer( );
            t.scheduleAtFixedRate(new TimerTask() {

                int moveCounter = 0;

                @Override
                public void run() {

                    Move m = movesList.get(moveCounter);

                    ui.changeMenuState(true);

                    Platform.runLater(() -> {
                        Cell cell = new Cell();
                        Image image = getFigureIcon(m.getFigure());

                        ImageView view = new ImageView(image);
                        view.setFitHeight(100);
                        view.setFitWidth(100);

                        cell.initCell(m.getX(), m.getY());
                        cell.getChildren().add(view);
                        cell.setOccupied(true);

                        board.add(cell, m.getY(), m.getX());
                    });

                    moveCounter++;
                    if(moveCounter >= movesList.size()) {
                        t.cancel();
                        t.purge();
                    }
                }
            }, 500,500);
            ui.changeMenuState(true);
        }
    }
}

