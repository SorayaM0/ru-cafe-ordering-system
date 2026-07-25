package model;
public class Donut extends MenuItem {
    private final DonutFlavor flavor;

    public Donut(DonutFlavor flavor, int quantity){
        super(quantity);
        this.flavor = flavor;
    }

    @Override
    public double price(){
        double unit;
        switch(flavor.type()){
            case YEAST -> unit = Prices.DONUT_YEAST;
            case CAKE -> unit = Prices.DONUT_CAKE;
            case HOLE -> unit = Prices.DONUT_HOLE;
            case SEASONAL -> unit = Prices.DONUT_SEASONAL;
            default -> unit = 0.0;
        }
        return unit * quantity;
    }

    @Override
    public String description(){
        return String.format("%dx %s donut", quantity, flavor.name().replace('_',' ').toLowerCase());
    }
}
