package model;
public enum DonutFlavor {
    GLAZED(DonutType.YEAST),
    CHOCOLATE_ICED(DonutType.YEAST),
    STRAWBERRY(DonutType.YEAST),
    JELLY(DonutType.YEAST),
    BOSTON_CREAM(DonutType.YEAST),
    MAPLE(DonutType.YEAST),

    VANILLA_CAKE(DonutType.CAKE),
    CHOCOLATE_CAKE(DonutType.CAKE),
    RED_VELVET(DonutType.CAKE),

    SUGAR_HOLE(DonutType.HOLE),
    CINNAMON_HOLE(DonutType.HOLE),
    CHOCOLATE_HOLE(DonutType.HOLE),

    PUMPKIN_SPICE(DonutType.SEASONAL),
    SPOOKY_SPRINKLE(DonutType.SEASONAL),
    GINGERBREAD(DonutType.SEASONAL);

    private final DonutType type;
    DonutFlavor(DonutType t){ this.type = t; }
    public DonutType type(){ return type; }
}