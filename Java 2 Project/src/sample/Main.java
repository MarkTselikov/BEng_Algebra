package sample;

import Model.JNDIHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        primaryStage.setTitle("Quarto");
        primaryStage.setScene(new Scene(root,
                Integer.parseInt(JNDIHandler.getConfigParam("MENU_WIDTH")),
                Integer.parseInt(JNDIHandler.getConfigParam("MENU_HEIGHT"))));
        primaryStage.show();

        Parent rootSecond = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Stage secondStage = new Stage();
        secondStage.setScene(new Scene(rootSecond,
                Integer.parseInt(JNDIHandler.getConfigParam("MENU_WIDTH")),
                Integer.parseInt(JNDIHandler.getConfigParam("MENU_HEIGHT"))));
        secondStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
