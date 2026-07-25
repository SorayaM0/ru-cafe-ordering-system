package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import model.MenuItem;
import model.OrderManager;

public class CurrentOrderController {

    @FXML
    private TableView<MenuItem> itemsTable;

    @FXML
    private TableColumn<MenuItem, String> descCol;

    @FXML
    private TableColumn<MenuItem, String> priceCol;

    @FXML
    private Label subtotalLabel;

    @FXML
    private Label taxLabel;

    @FXML
    private Label totalLabel;

    @FXML
    public void initialize() {
        descCol.setCellValueFactory(cell ->
            new javafx.beans.property.SimpleStringProperty(
                cell.getValue().description()
            )
        );

        priceCol.setCellValueFactory(cell ->
            new javafx.beans.property.SimpleStringProperty(
                String.format("$%.2f", cell.getValue().price())
            )
        );

        refresh();
    }

    public void refresh() {
        ObservableList<MenuItem> data =
            FXCollections.observableArrayList(
                OrderManager.getInstance()
                    .getCurrentOrder()
                    .getItems()
            );

        itemsTable.setItems(data);

        double subtotal = OrderManager.getInstance()
            .getCurrentOrder()
            .getSubtotal();

        double tax = OrderManager.getInstance()
            .getCurrentOrder()
            .getTax();

        double total = subtotal + tax;

        subtotalLabel.setText(String.format("$%.2f", subtotal));
        taxLabel.setText(String.format("$%.2f", tax));
        totalLabel.setText(String.format("$%.2f", total));
    }

    @FXML
    private void handleRemove() {
        MenuItem selected =
            itemsTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            return;
        }

        OrderManager.getInstance()
            .getCurrentOrder()
            .removeItem(selected);

        refresh();
    }

    @FXML
    private void handlePlace() {
        if (OrderManager.getInstance()
                .getCurrentOrder()
                .getItems()
                .isEmpty()) {

            new Alert(
                Alert.AlertType.WARNING,
                "The current order is empty."
            ).showAndWait();

            return;
        }

        OrderManager.getInstance().placeCurrentOrder();

        new Alert(
            Alert.AlertType.INFORMATION,
            "Order placed!"
        ).showAndWait();

        refresh();
    }

    @FXML
    private void handleClear() {
        OrderManager.getInstance()
            .getCurrentOrder()
            .clear();

        refresh();
    }
}