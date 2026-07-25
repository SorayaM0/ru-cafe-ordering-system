package model;
import java.util.ArrayList;
public class Order {
    private static int nextNumber = 1;
    private final int orderNumber;
    private final ArrayList<MenuItem> items = new ArrayList<>();

    public Order(){
        this.orderNumber = nextNumber++;
    }

    public int getOrderNumber(){ return orderNumber; }
    public ArrayList<MenuItem> getItems(){ return items; }

    public void addItem(MenuItem it){ items.add(it); }
    public void removeItem(MenuItem it){ items.remove(it); }
    public void clear(){ items.clear(); }

    public double getSubtotal(){
        return items.stream().mapToDouble(MenuItem::price).sum();
    }
    public double getTax(){
        return getSubtotal() * Prices.TAX;
    }
    public double getTotal(){
        return getSubtotal() + getTax();
    }

    public String briefItems(){
        StringBuilder sb = new StringBuilder();
        for (MenuItem m: items){
            sb.append(m.description()).append("; ");
        }
        return sb.toString();
    }
}
