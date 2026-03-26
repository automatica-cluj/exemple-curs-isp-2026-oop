package utcluj.aut.dp.structural.decorator;

public class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Cafea simplă";
    }

    @Override
    public double getCost() {
        return 5.0;
    }
}
