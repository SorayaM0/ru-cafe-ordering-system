package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainController {

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab currentOrderTab;

    /*
     * JavaFX automatically injects the controller from the fx:include
     * whose fx:id is "currentOrder".
     */
    @FXML
    private CurrentOrderController currentOrderController;

    @FXML
    public void initialize() {
        currentOrderTab.selectedProperty().addListener(
            (observable, wasSelected, isSelected) -> {
                if (isSelected && currentOrderController != null) {
                    currentOrderController.refresh();
                }
            }
        );
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
            stage.setScene(new Scene(root));
            stage.setMinWidth(750);
            stage.setMinHeight(500);

            Window owner = tabPane.getScene().getWindow();
            stage.initOwner(owner);
            stage.initModality(Modality.WINDOW_MODAL);

            stage.showAndWait();

        } catch (IOException | RuntimeException exception) {
            exception.printStackTrace();

            Alert alert = new Alert(
                Alert.AlertType.ERROR,
                "Unable to open placed orders:\n"
                    + exception.getMessage()
            );

            alert.showAndWait();
        }
    }
}