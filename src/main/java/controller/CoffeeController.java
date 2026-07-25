package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
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
    private MenuButton addInsMenu;

    @FXML
    private Label selectedAddInsLabel;

    @FXML
    private Spinner<Integer> qtySpinner;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView coffeeImage;

    private final ArrayList<AddIn> selectedAddIns = new ArrayList<>();

    @FXML
    public void initialize() {
        sizeCombo.getItems().addAll(CupSize.values());

        qtySpinner.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 1)
        );

        createAddInMenuItems();

        sizeCombo.valueProperty().addListener(
            (observable, oldValue, newValue) -> {
                updateCoffeeImage(newValue);
                updatePrice();
            }
        );

        qtySpinner.valueProperty().addListener(
            (observable, oldValue, newValue) -> updatePrice()
        );
    }

    private void createAddInMenuItems() {
        addInsMenu.getItems().clear();

        for (AddIn addIn : AddIn.values()) {
            CheckMenuItem item = new CheckMenuItem(addIn.toString());

            item.selectedProperty().addListener(
                (observable, wasSelected, isSelected) -> {
                    if (isSelected) {
                        if (!selectedAddIns.contains(addIn)) {
                            selectedAddIns.add(addIn);
                        }
                    } else {
                        selectedAddIns.remove(addIn);
                    }

                    updateSelectedAddInsText();
                    updatePrice();
                }
            );

            addInsMenu.getItems().add(item);
        }
    }

    private void updateSelectedAddInsText() {
        if (selectedAddIns.isEmpty()) {
            selectedAddInsLabel.setText("No add-ins selected");
            addInsMenu.setText("Choose add-ins");
            return;
        }

        String selectedText = selectedAddIns.stream()
            .map(AddIn::toString)
            .collect(Collectors.joining(", "));

        selectedAddInsLabel.setText(selectedText);
        addInsMenu.setText(selectedAddIns.size() + " selected");
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
            System.err.println("Coffee image not found: " + imagePath);
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

        int quantity = qtySpinner.getValue();

        Coffee coffee = new Coffee(
            size,
            new ArrayList<>(selectedAddIns),
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
                "Choose a cup size."
            ).showAndWait();

            return;
        }

        int quantity = qtySpinner.getValue();

        OrderManager.getInstance()
            .getCurrentOrder()
            .addItem(
                new Coffee(
                    size,
                    new ArrayList<>(selectedAddIns),
                    quantity
                )
            );

        new Alert(
            Alert.AlertType.INFORMATION,
            "Added to order."
        ).showAndWait();
    }
}