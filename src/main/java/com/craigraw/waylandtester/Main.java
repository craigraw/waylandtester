package com.craigraw.waylandtester;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Main extends Application {
    private static boolean initOwner = false;
    private static boolean useVbox = false;

    public static void main(String[] args) {
        if(args.length > 0) {
            initOwner = Boolean.parseBoolean(args[0]);
        }
        if(args.length > 1) {
            useVbox = Boolean.parseBoolean(args[1]);
        }

        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Font.loadFont(Main.class.getResourceAsStream("/font/RobotoMono-Regular.ttf"), 13);

        try {
            FXMLLoader appLoader = new FXMLLoader(Main.class.getResource("app.fxml"));
            Parent root = appLoader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(Main.class.getResource("app.css").toExternalForm());

            stage.setTitle("Wayland Tester");
            stage.setMinWidth(650);
            stage.setMinHeight(708);
            stage.setScene(scene);

            stage.show();
        } catch(IOException e) {
            throw new IllegalStateException(e);
        }

        CustomTextInputDialog alert = new CustomTextInputDialog(useVbox);
        if(initOwner) {
            alert.initOwner(stage.getScene().getWindow());
        }
        
        alert.showAndWait();

//        WalletPasswordDialog dlg = new WalletPasswordDialog();
//        dlg.initOwner(stage.getScene().getWindow());
//        Optional<String> optionalPassword = dlg.showAndWait();
    }

    private static Button createButton() {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(event -> System.out.println("Hello World!"));
        return btn;
    }
}