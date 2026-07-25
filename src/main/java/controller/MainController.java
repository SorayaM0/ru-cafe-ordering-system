package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TabPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainController {

    @FXML
    private TabPane tabPane;

    /*
     * JavaFX injects the controller belonging to:
     *
     * <fx:include fx:id="currentOrder" ... />
     *
     * by using the name currentOrderController.
     */
    @FXML
    private CurrentOrderController currentOrderController;

    @FXML
    public void initialize() {
        /*
         * Refresh the order panel whenever the user changes
         * between Donuts, Coffee, and Sandwiches.
         */
        tabPane.getSelectionModel()
               .selectedItemProperty()
               .addListener((observable, oldTab, newTab) -> {
                   refreshCurrentOrder();
               });
    }

    public void refreshCurrentOrder() {
        if (currentOrderController != null) {
            currentOrderController.refresh();
        }
    }

    @FXML
    private void openPlacedOrders(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/all_orders.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Placed Orders");
            stage.setScene(new Scene(root, 850, 550));

            Window owner = tabPane.getScene().getWindow();
            stage.initOwner(owner);
            stage.initModality(Modality.WINDOW_MODAL);

            stage.showAndWait();

            refreshCurrentOrder();

        } catch (IOException | RuntimeException exception) {
            exception.printStackTrace();

            new Alert(
                Alert.AlertType.ERROR,
                "Unable to open placed orders:\n"
                    + exception.getMessage()
            ).showAndWait();
        }
    }
}