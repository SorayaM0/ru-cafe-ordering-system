package model;
import java.util.ArrayList;
public class Sandwich extends MenuItem {
    private final Bread bread;
    private final Protein protein;
    private final ArrayList<AddOn> addOns;

    public Sandwich(Bread bread, Protein protein, ArrayList<AddOn> addOns, int quantity){
        super(quantity);
        this.bread = bread;
        this.protein = protein;
        this.addOns = (addOns == null) ? new ArrayList<>() : new ArrayList<>(addOns);
    }

    private double baseProtein(){
        return switch(protein){
            case BEEF -> Prices.S_BEEF;
            case CHICKEN -> Prices.S_CHICKEN;
            case SALMON -> Prices.S_SALMON;
        };
    }

    private double addOnCost(AddOn a){
        return switch(a){
            case LETTUCE -> Prices.A_LETTUCE;
            case TOMATOES -> Prices.A_TOMATOES;
            case ONIONS -> Prices.A_ONIONS;
            case CHEESE -> Prices.A_CHEESE;
        };
    }

    @Override
    public double price(){
        double unit = baseProtein();
        for (AddOn a: addOns) unit += addOnCost(a);
        return unit * quantity;
    }

    @Override
    public String description(){
        return String.format("%dx %s on %s +%d add-ons", quantity, protein.name(), bread.name(), addOns.size());
    }
}
