package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import model.*;
import java.io.File;
import javafx.collections.FXCollections;

public class AllOrdersController {
    @FXML private TableView<Order> ordersTable;
    @FXML private TableColumn<Order, Number> orderNoCol;
    @FXML private TableColumn<Order, String> totalCol;
    @FXML private TableColumn<Order, String> itemsCol;

    @FXML
    public void initialize(){
        orderNoCol.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getOrderNumber()));
        totalCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(String.format("$%.2f", c.getValue().getTotal())));
        itemsCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().briefItems()));
        refresh();
    }

    private void refresh(){
        ordersTable.setItems(FXCollections.observableArrayList(OrderManager.getInstance().getPlacedOrders()));
    }

    @FXML
    private void handleCancel(){
        Order selected = ordersTable.getSelectionModel().getSelectedItem();
        if (selected == null) return;
        OrderManager.getInstance().cancelOrderByNumber(selected.getOrderNumber());
        refresh();
    }

    @FXML
    private void handleExport(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Export Orders");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files","*.txt"));
        File target = chooser.showSaveDialog(ordersTable.getScene().getWindow());
        if (target == null) return;
        try {
            OrderManager.getInstance().exportAllOrders(target);
            new javafx.scene.control.Alert(Alert.AlertType.INFORMATION, "Exported to "+target.getAbsolutePath()).showAndWait();
        } catch (Exception ex){
            new javafx.scene.control.Alert(Alert.AlertType.ERROR, "Export failed: "+ex.getMessage()).showAndWait();
        }
    }
}
