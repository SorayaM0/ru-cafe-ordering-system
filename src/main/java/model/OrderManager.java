package model;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OrderManager {
    private static final OrderManager INSTANCE = new OrderManager();
    public static OrderManager getInstance(){ return INSTANCE; }

    private final ArrayList<Order> placed = new ArrayList<>();
    private Order current = new Order();

    private OrderManager(){}

    public Order getCurrentOrder(){ return current; }
    public ArrayList<Order> getPlacedOrders(){ return placed; }

    public void placeCurrentOrder(){
        if (!current.getItems().isEmpty()){
            placed.add(current);
            current = new Order();
        }
    }

    public void cancelOrderByNumber(int orderNo){
        placed.removeIf(o -> o.getOrderNumber() == orderNo);
    }

    public void exportAllOrders(java.io.File file) throws IOException {
        try (FileWriter fw = new FileWriter(file)){
            for (Order o: placed){
                fw.write("Order #" + o.getOrderNumber() + "\n");
                for (MenuItem m: o.getItems()){
                    fw.write(" - " + m.description() + " : $" + String.format("%.2f", m.price()) + "\n");
                }
                fw.write(String.format("Subtotal: $%.2f\n", o.getSubtotal()));
                fw.write(String.format("Tax: $%.2f\n", o.getTax()));
                fw.write(String.format("Total: $%.2f\n\n", o.getTotal()));
            }
        }
    }
}
