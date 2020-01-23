package com.game.scene;

import com.game.service.Service;
import com.game.service.Value;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StartPage {

    public void startScene(Stage stage) {
        VBox root = new VBox();
        Scene scene = new Scene(root, 600, 600);

        //set style
        Service.setStyle(root, stage);

        //set buttons switcher scenes
        AnchorPane buttonsNavigation = new AnchorPane();
        Button buttonExit = new Button("Exit");
        AnchorPane.setRightAnchor(buttonExit, 0.0);
        buttonsNavigation.getChildren().addAll(buttonExit);

        //set text fields
        GridPane pane = new GridPane ();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(5);
        pane.setVgap(5);
        TextField fieldFirst = new TextField();
        fieldFirst.setMaxWidth(40);
        fieldFirst.setText("0");
        Label fieldFirstLabel = new Label("Min value");
        fieldFirstLabel.setTextFill(Color.web("white"));
        TextField fieldSecond = new TextField();
        fieldSecond.setMaxWidth(40);
        fieldSecond.setText("10");
        Label fieldSecondLabel = new Label("Max value");
        fieldSecondLabel.setTextFill(Color.web("white"));
        pane.add(fieldFirstLabel, 0, 1);
        pane.add(fieldFirst, 1, 1);
        pane.add(fieldSecondLabel, 0, 2);
        pane.add(fieldSecond, 1, 2);
        Button startGameButton = new Button("Start Game");
        pane.add(startGameButton, 0, 3, 2, 1);

        //handle buttons actions
        startGameButton.setOnAction(e -> {
            int min, max;
            try {
                min = Integer.parseInt(fieldFirst.getText());
                max = Integer.parseInt(fieldSecond.getText());
            } catch (Exception a) {
                Service.alert("Min and Max values must be numbers");
                throw new IllegalArgumentException("Min and Max values must be numbers");
            }
            if (min >= max){ Service.alert("Min value cannot be greater than or equal to max");
                throw new IllegalArgumentException("Min value cannot be greater than or equal to max");
            }

            Value.min = min;
            Value.max = max;
            new GamePage().gameScene(stage);
        });
        buttonExit.setOnAction(e -> Service.confirmExit());

        root.getChildren().addAll(buttonsNavigation, pane);
        stage.setScene(scene);
        stage.show();
    }
}
