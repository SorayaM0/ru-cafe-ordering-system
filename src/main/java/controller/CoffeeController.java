package controller;

import java.net.URL;
import java.util.ArrayList;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import model.AddIn;
import model.Coffee;
import model.CupSize;
import model.OrderManager;

public class CoffeeController {

    @FXML
    private ComboBox<CupSize> sizeCombo;

    @FXML
    private ListView<AddIn> addInsList;

    @FXML
    private Spinner<Integer> qtySpinner;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView coffeeImage;

    @FXML
    public void initialize() {
        sizeCombo.getItems().addAll(CupSize.values());

        addInsList.getItems().addAll(AddIn.values());
        addInsList.getSelectionModel()
                  .setSelectionMode(SelectionMode.MULTIPLE);

        qtySpinner.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(
                1,
                99,
                1
            )
        );

        sizeCombo.valueProperty().addListener(
            (observable, oldValue, newValue) -> {
                updateCoffeeImage(newValue);
                updatePrice();
            }
        );

        addInsList.getSelectionModel()
                  .getSelectedItems()
                  .addListener(
                      (ListChangeListener<? super AddIn>) change ->
                          updatePrice()
                  );

        qtySpinner.valueProperty().addListener(
            (observable, oldValue, newValue) -> updatePrice()
        );
    }

    private void updateCoffeeImage(CupSize size) {
        if (size == null) {
            coffeeImage.setImage(null);
            return;
        }

        String imageName = switch (size) {
            case SHORT -> "short.png";
            case TALL -> "tall.png";
            case GRANDE -> "grande.png";
            case VENTI -> "venti.png";
        };

        String imagePath = "/view/img/" + imageName;
        URL imageUrl = getClass().getResource(imagePath);

        if (imageUrl == null) {
            System.err.println(
                "Coffee image not found: " + imagePath
            );
            coffeeImage.setImage(null);
            return;
        }

        coffeeImage.setImage(
            new Image(imageUrl.toExternalForm())
        );
    }

    private void updatePrice() {
        CupSize size = sizeCombo.getValue();

        if (size == null) {
            priceLabel.setText("$0.00");
            return;
        }

        ArrayList<AddIn> addIns = new ArrayList<>(
            addInsList.getSelectionModel().getSelectedItems()
        );

        int quantity = qtySpinner.getValue();

        Coffee coffee = new Coffee(
            size,
            addIns,
            quantity
        );

        priceLabel.setText(
            String.format("$%.2f", coffee.price())
        );
    }

    @FXML
    private void handleAdd() {
        CupSize size = sizeCombo.getValue();

        if (size == null) {
            new Alert(
                Alert.AlertType.WARNING,
                "Choose a size."
            ).showAndWait();

            return;
        }

        ArrayList<AddIn> addIns = new ArrayList<>(
            addInsList.getSelectionModel().getSelectedItems()
        );

        int quantity = qtySpinner.getValue();

        OrderManager.getInstance()
                    .getCurrentOrder()
                    .addItem(
                        new Coffee(
                            size,
                            addIns,
                            quantity
                        )
                    );

        new Alert(
            Alert.AlertType.INFORMATION,
            "Added to order."
        ).showAndWait();
    }
}