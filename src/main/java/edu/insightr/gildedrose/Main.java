package edu.insightr.gildedrose;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view.fxml"));
        primaryStage.setTitle("Item List of Items");
        primaryStage.setScene(new Scene(root, 581, 500));
        primaryStage.show();


    }
}