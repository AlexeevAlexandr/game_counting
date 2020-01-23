package com.game.scene;

import com.game.service.Service;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

class GamePage {

    private Text textException = new Text("");
    private String expression;

    void gameScene(Stage stage) {
        VBox root = new VBox();
        Label label = new Label();
        Scene scene = new Scene(root, 600, 600);

        //set style
        Service.setStyle(root, stage);

        //set text fields
        GridPane pane = new GridPane ();
        pane.setAlignment(Pos.CENTER);

        HBox textFields = new HBox();
        textFields.setSpacing(10);
        textFields.setAlignment(Pos.CENTER);
        TextField fieldFirst = new TextField();
        fieldFirst.setMaxWidth(100);
        expression = Service.getExpression();
        fieldFirst.setText(expression);
        fieldFirst.setEditable(false);
        TextField fieldSecond = new TextField();
        fieldSecond.setMaxWidth(40);
        Button buttonCheck = new Button("Check");
        textFields.getChildren().addAll(label, fieldFirst, fieldSecond, buttonCheck);

        pane.add(textException, 1, 1);
        pane.add(textFields, 1, 2);

        //set buttons
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(20);
        AnchorPane buttonsNavigation = new AnchorPane();
        Button buttonMainScene = new Button("Main Page");
        Button buttonExit = new Button("Exit");
        AnchorPane.setLeftAnchor(buttonMainScene, 0.0);
        AnchorPane.setRightAnchor(buttonExit, 0.0);
        buttonsNavigation.getChildren().addAll(buttonMainScene, buttonExit);

        //handle buttons actions
        buttonMainScene.setOnAction(e -> new StartPage().startScene(stage));//switch to mainScene
        buttonExit.setOnAction(e -> Service.confirmExit());
        buttonCheck.setOnAction(e -> {
                if (Service.checkExpression(expression, fieldSecond.getText())) {
                    expression = Service.getExpression();
                    fieldFirst.setText(expression);
                    fieldSecond.setText("");
                    textException.setText("");
                } else {
                    textException.setText(String.format("%s is incorrect answer", fieldSecond.getText()));
                    throw new IllegalArgumentException("Incorrect answer");
                }
        });

        //adding components to the scene
        root.getChildren().addAll(buttonsNavigation, pane, buttons);
        stage.setScene(scene);
        stage.show();

    }
}