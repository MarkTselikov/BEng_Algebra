package sample;

import Model.JNDIHandler;
import Utils.Reflector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu {

    public static boolean replayMode = false;

    @FXML
    private Button btnNewGame;

    @FXML
    private Button btnReplay;

    @FXML
    private Button btnDocumentation;

    @FXML
    private void initialize() {
        btnNewGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/board.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Quarto");
                    stage.setScene(new Scene(root, 800, 500));
                    stage.show();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnReplay.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                replayMode = true;
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/board.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Quarto");
                    stage.setScene(new Scene(root,
                            Integer.parseInt(JNDIHandler.getConfigParam("BOARD_WIDTH")),
                            Integer.parseInt(JNDIHandler.getConfigParam("BOARD_HEIGHT"))));
                    stage.show();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    private void getDocumentation() {
        Reflector.getReflectionDocument();
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Reflection document successfully created");
        alert.showAndWait();
    }
}
