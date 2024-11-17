import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PHONEBILL extends PhoneBillPaymentSystem {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Phone Bill Payment System");

        // Create labels and text fields
        Label callMinutesLabel = new Label("Call Minutes:");
        TextField callMinutesField = new TextField();

        Label dataUsedLabel = new Label("Data Used (MB):");
        TextField dataUsedField = new TextField();

        Label billAmountLabel = new Label("Total Bill Amount:");
        TextField billAmountField = new TextField();
        billAmountField.setEditable(false); // Make this field read-only

        Button calculateButton = new Button("Calculate Bill");
        Button payButton = new Button("Pay");

        // Set action for Calculate button
        calculateButton.setOnAction(e -> {
            try {
                double callMinutes = Double.parseDouble(callMinutesField.getText());
                double dataUsed = Double.parseDouble(dataUsedField.getText());

                // Basic calculation for bill amount
                double callRate = 0.1; // Rate per minute
                double dataRate = 0.05; // Rate per MB
                double totalBill = (callMinutes * callRate) + (dataUsed * dataRate);
                billAmountField.setText(String.format("%.2f", totalBill));
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter valid numbers for call minutes and data used.");
            }
        });

        // Set action for Pay button
        payButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(billAmountField.getText());
                if (amount <= 0) {
                    showAlert("Error", "No bill amount to pay.");
                    return;
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Payment Confirmation");
                alert.setHeaderText("Payment Successful!");
                alert.setContentText("You have paid: $" + amount);
                alert.showAndWait();

                // Reset fields
                callMinutesField.clear();
                dataUsedField.clear();
                billAmountField.clear();
            } catch (NumberFormatException ex) {
                showAlert("Error", "Please calculate the bill before making a payment.");
            }
        });

        // Create layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Add controls to the grid
        grid.add(callMinutesLabel, 0, 0);
        grid.add(callMinutesField, 1, 0);
        grid.add(dataUsedLabel, 0, 1);
        grid.add(dataUsedField, 1, 1);
        grid.add(billAmountLabel, 0, 2);
        grid.add(billAmountField, 1, 2);
        grid.add(calculateButton, 0, 3);
        grid.add(payButton, 1, 3);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
