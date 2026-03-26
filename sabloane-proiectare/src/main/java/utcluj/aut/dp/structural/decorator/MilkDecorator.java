package utcluj.aut.dp.structural.decorator;

public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", cu lapte";
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 2.0;
    }
}
