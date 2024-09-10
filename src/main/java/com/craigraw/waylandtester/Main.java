package com.craigraw.waylandtester;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Font.loadFont(Main.class.getResourceAsStream("/font/RobotoMono-Regular.ttf"), 13);

        primaryStage.setTitle("Hello World!");

        StackPane root = new StackPane();
        for(int i = 0; i < 2000; i++) {
            root.getChildren().add(createButton());
        }
        primaryStage.setScene(new Scene(root, 300, 600));
        primaryStage.show();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This is a modal dialog.");
        alert.showAndWait();
    }

    private static Button createButton() {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(event -> System.out.println("Hello World!"));
        return btn;
    }
}