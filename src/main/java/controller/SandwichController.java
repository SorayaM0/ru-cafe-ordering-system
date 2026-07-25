package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import java.util.ArrayList;

public class SandwichController {
    @FXML private ComboBox<Bread> breadCombo;
    @FXML private ComboBox<Protein> proteinCombo;
    @FXML private ListView<AddOn> addOnsList;
    @FXML private Spinner<Integer> qtySpinner;
    @FXML private Label priceLabel;

    @FXML
    public void initialize(){
        breadCombo.getItems().addAll(Bread.values());
        proteinCombo.getItems().addAll(Protein.values());
        addOnsList.getItems().addAll(AddOn.values());
        addOnsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        qtySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 1));

        breadCombo.valueProperty().addListener((o,oldV,newV)-> updatePrice());
        proteinCombo.valueProperty().addListener((o,oldV,newV)-> updatePrice());
        addOnsList.getSelectionModel().getSelectedItems().addListener((javafx.collections.ListChangeListener<? super AddOn>) c -> updatePrice());
        qtySpinner.valueProperty().addListener((o,oldV,newV)-> updatePrice());
    }

    private void updatePrice(){
        Bread b = breadCombo.getValue();
        Protein p = proteinCombo.getValue();
        Integer q = qtySpinner.getValue();
        if (b == null || p == null || q == null){ priceLabel.setText("$0.00"); return; }
        ArrayList<AddOn> ons = new ArrayList<>(addOnsList.getSelectionModel().getSelectedItems());
        Sandwich s = new Sandwich(b, p, ons, q);
        priceLabel.setText(String.format("$%.2f", s.price()));
    }

    @FXML
    private void handleAdd(){
        Bread b = breadCombo.getValue();
        Protein p = proteinCombo.getValue();
        if (b == null || p == null){
            new Alert(Alert.AlertType.WARNING, "Choose bread and protein.").showAndWait();
            return;
        }
        java.util.ArrayList<AddOn> ons = new java.util.ArrayList<>(addOnsList.getSelectionModel().getSelectedItems());
        int q = qtySpinner.getValue();
        OrderManager.getInstance().getCurrentOrder().addItem(new Sandwich(b, p, ons, q));
        new Alert(Alert.AlertType.INFORMATION, "Added to order.").showAndWait();
    }
}
