package model;
/** Abstract base for all menu items. */
public abstract class MenuItem {
    protected int quantity;
    public MenuItem(int quantity){
        this.quantity = Math.max(1, quantity);
    }
    public int getQuantity(){ return quantity; }
    public void setQuantity(int q){ this.quantity = Math.max(1, q); }
    public abstract double price();
    public abstract String description();
}
