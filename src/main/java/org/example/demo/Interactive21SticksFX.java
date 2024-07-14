package org.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class Interactive21SticksFX extends Application {

    private int totalSticks = 21;
    private Random random = new Random();
    private Label infoLabel = new Label("Choisissez le nombre de bâtons que vous voulez prendre entre 1 et 3 : ");
    private VBox root; // Sınıf düzeyinde root tanımlıyoruz

    @Override
    public void start(Stage primaryStage) {
        root = new VBox(10);
        root.setPadding(new Insets(20));

        // Label for information
        root.getChildren().add(infoLabel);

        // Button for user to choose sticks
        Button[] choiceButtons = new Button[3];
        for (int i = 0; i < 3; i++) {
            final int choice = i + 1;
            choiceButtons[i] = new Button(String.valueOf(choice));
            choiceButtons[i].setOnAction(event -> {
                playTurn(choice);
            });
        }

        // Add buttons to an HBox for layout
        HBox buttonBox = new HBox(10, choiceButtons);
        root.getChildren().add(buttonBox);

        // Create scene and set it on the stage
        Scene scene = new Scene(root, 600, 200);
        primaryStage.setTitle("Interactive 21 Sticks");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void playTurn(int choice) {
        totalSticks -= choice;
        infoLabel.setText("Vous avez choisi : " + choice + " bâton(s).\nIl reste " + totalSticks + " bâton(s).");

        if (totalSticks <= 0) {
            infoLabel.setText("C'est dommage! Vous avez perdu !");
            disableButtons();
        } else {
            int computerChoice = random.nextInt(3) + 1;
            totalSticks -= computerChoice;
            infoLabel.setText(infoLabel.getText() + "\nL'ordinateur a choisi " + computerChoice + " bâton(s).\nIl reste " + totalSticks + " bâton(s).");

            if (totalSticks <= 0) {
                infoLabel.setText("Bravo ! Vous avez gagné !");
                disableButtons();
            }
        }
    }

    private void disableButtons() {
        // Disable all choice buttons
        for (Node button : ((HBox) root.getChildren().get(1)).getChildren()) {
            button.setDisable(true);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}