package com.game;

import com.game.scene.StartPage;
import com.game.service.Service;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setOnCloseRequest(e -> {
            e.consume();
            Service.confirmExit();
        });
        stage.setResizable(false);
        new StartPage().startScene(stage);
    }
}
