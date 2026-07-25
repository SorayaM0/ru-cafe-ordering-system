package model;
import java.util.ArrayList;
public class Coffee extends MenuItem {
    private final CupSize size;
    private final ArrayList<AddIn> addIns;

    public Coffee(CupSize size, ArrayList<AddIn> addIns, int quantity){
        super(quantity);
        this.size = size;
        this.addIns = (addIns == null) ? new ArrayList<>() : new ArrayList<>(addIns);
    }

    private double baseForSize(){
        int steps = switch(size){
            case SHORT -> 0;
            case TALL -> 1;
            case GRANDE -> 2;
            case VENTI -> 3;
        };
        return Prices.COFFEE_BASE_SHORT + Prices.SIZE_STEP * steps;
    }

    @Override
    public double price(){
        double unit = baseForSize() + (addIns.size() * Prices.ADDIN);
        return unit * quantity;
    }

    @Override
    public String description(){
        return String.format("%dx %s coffee +%d add-ins", quantity, size.name(), addIns.size());
    }
}
