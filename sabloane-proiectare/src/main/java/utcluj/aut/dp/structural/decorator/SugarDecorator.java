package utcluj.aut.dp.structural.decorator;

public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", cu zahăr";
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 1.0;
    }
}
