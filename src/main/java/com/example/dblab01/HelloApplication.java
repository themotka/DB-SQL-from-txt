package com.example.dblab01;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    static ObservableList<Students> studentData = FXCollections.observableArrayList();
    static ObservableList<Variants> varData = FXCollections.observableArrayList();

    static ObservableList<Testing> testData = FXCollections.observableArrayList();

    static ObservableList<Marks> markData = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("DataBaseSimulator2000");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            studentData.add(new Students(i,"Михайлэнко", "Данило", "Мыколович"));
        }
        for (int i = 0; i < 5; i++) {
            varData.add(new Variants(i,"C:\\Windows\\System3" + i));
        }
        launch();
    }
}