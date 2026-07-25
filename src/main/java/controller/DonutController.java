package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;
import java.util.stream.Collectors;

public class DonutController {
    @FXML private ComboBox<DonutType> typeCombo;
    @FXML private ComboBox<DonutFlavor> flavorCombo;
    @FXML private Spinner<Integer> qtySpinner;
    @FXML private Label priceLabel;
    @FXML private ImageView donutImage;

    @FXML
    public void initialize(){
        typeCombo.getItems().addAll(DonutType.values());
        qtySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 1));
        typeCombo.valueProperty().addListener((obs, o, n) -> {
            updateFlavorChoices();
            updateImage(n);
            updatePrice();
        });
        flavorCombo.valueProperty().addListener((obs,o,n)-> updatePrice());
        qtySpinner.valueProperty().addListener((obs,o,n)-> updatePrice());
    }

    private void updateFlavorChoices(){
        flavorCombo.getItems().clear();
        DonutType t = typeCombo.getValue();
        if (t == null) return;
        flavorCombo.getItems().addAll(
            java.util.Arrays.stream(DonutFlavor.values())
                .filter(f -> f.type() == t)
                .collect(Collectors.toList())
        );
        if (!flavorCombo.getItems().isEmpty()) {
            flavorCombo.getSelectionModel().select(0);
        }
    }

    private void updateImage(DonutType t){
        if (t == null) return;
        String img = switch(t){
            case YEAST -> "yeast.png";
            case CAKE -> "cake.png";
            case HOLE -> "hole.png";
            case SEASONAL -> "seasonal.png";
        };
        donutImage.setImage(new Image(getClass().getResourceAsStream("/view/img/" + img)));
    }

    private void updatePrice(){
        DonutType t = typeCombo.getValue();
        DonutFlavor f = flavorCombo.getValue();
        Integer q = qtySpinner.getValue();
        if (t == null || f == null || q == null) { priceLabel.setText("$0.00"); return; }
        Donut d = new Donut(f, q);
        priceLabel.setText(String.format("$%.2f", d.price()));
    }

    @FXML
    private void handleAdd(){
        DonutType t = typeCombo.getValue();
        DonutFlavor f = flavorCombo.getValue();
        Integer q = qtySpinner.getValue();
        if (t == null || f == null || q == null) {
            new Alert(Alert.AlertType.WARNING, "Please select type, flavor, and quantity.").showAndWait();
            return;
        }
        OrderManager.getInstance().getCurrentOrder().addItem(new Donut(f, q));
        new Alert(Alert.AlertType.INFORMATION, "Added to order.").showAndWait();
    }
}
