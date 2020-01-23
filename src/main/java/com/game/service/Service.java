package com.game.service;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class Service {

    public static void setStyle(VBox root, Stage stage) {
        BackgroundImage myBI= new BackgroundImage(
                new Image("royalty-free-worksheet-school.jpg",600,600,false,true),
                BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));
        stage.setTitle("Game");
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(400);
    }

    public static void confirmExit(){
            StackPane stackPane = new StackPane();
            Scene modalScene = new Scene(stackPane, 250, 100);
            HBox modalHBox = new HBox();
            Button yes = new Button("Yes");
            Button no = new Button("No");
            modalHBox.setAlignment(Pos.CENTER);
            modalHBox.setSpacing(50);
            modalHBox.getChildren().addAll(yes, no);
            stackPane.getChildren().addAll(modalHBox);
            Stage modalStage = new Stage();
            modalStage.setTitle("Confirm exit");
            modalStage.setAlwaysOnTop(true);
            modalStage.setScene(modalScene);
            modalStage.show();

            no.setOnAction(actionEvent1 -> modalStage.close());
            yes.setOnAction(actionEvent1 -> System.exit(0));
    }

    public static void alert(String message){
        StackPane stackPane = new StackPane();
        Scene modalScene = new Scene(stackPane, 250, 100);
        Label alert = new Label(message);
        alert.setWrapText(true);
        alert.setMaxWidth(250);
        VBox vBox = new VBox ();
        Button ok = new Button("Ok");
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(alert, ok);
        stackPane.getChildren().addAll(vBox);
        Stage modalStage = new Stage();
        modalStage.setResizable(false);
        modalStage.setTitle("Alert");
        modalStage.setAlwaysOnTop(true);
        modalStage.setScene(modalScene);
        modalStage.show();

        ok.setOnAction(actionEvent1 -> modalStage.close());
    }

    public static boolean checkExpression(String expression, String answer) {
        String[] arr = expression.split(" ");
        switch (arr[1]){
            case "+":
                return ((Integer.parseInt(arr[0]) + Integer.parseInt(arr[2])) == Integer.parseInt(answer));
            case "-":
                return ((Integer.parseInt(arr[0]) - Integer.parseInt(arr[2])) == Integer.parseInt(answer));
        }
        return false;
    }

    public static String getExpression() {
        String sign = getRandomSign();
        StringBuilder expression = new StringBuilder();
        int a, b;
        switch (sign) {
            case "-":
                a = getRandomNumber(Value.min, Value.max);
                b = getRandomNumber(a, Value.max);
                expression.append(b).append(" - ").append(a).append(" = ");
                break;
            case "+":
                a = getRandomNumber(Value.min, Value.max);
                b = getRandomNumber(Value.min, Value.max);
                expression.append(b).append(" + ").append(a).append(" = ");
                break;
        }
        return expression.toString();
    }

    private static int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    private static String getRandomSign() {
        List<String> list = Arrays.asList("-", "+");
        return list.get((int)(Math.random() * 2));
    }
}