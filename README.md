# Project 4 JavaFX Starter

## Prereqs
- Java JDK 17 installed
- Maven installed (mvn on PATH) OR use VS Code's Java extension pack

## Run
```bash
mvn -q javafx:run
```
or in VS Code:
- Open folder
- Let it import Maven project
- Run: **Run > Run Without Debugging** on `App.main()` or use `mvn javafx:run` in terminal.

## Structure
- `Project4.App` boots `view/main.fxml`
- Controllers are in `controller/*Controller.java`
- Models and pricing in `model/*`

Next steps (follow your spec):
1. Fill in TODOs in model classes (price calculations, description text).
2. Wire listeners in controllers to update running price and add items to current order.
3. Implement Current Order totals, Place Order workflow, All Orders with export.
