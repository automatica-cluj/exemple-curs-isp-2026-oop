package utcluj.aut.dp.structural.decorator;

public class DecoratorDemo {
    public static void main(String[] args) {
        // Cafea simplă
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println(simpleCoffee.getDescription() + " costă: " + simpleCoffee.getCost() + " lei");
        
        // Cafea cu lapte
        Coffee milkCoffee = new MilkDecorator(simpleCoffee);
        System.out.println(milkCoffee.getDescription() + " costă: " + milkCoffee.getCost() + " lei");
        
        // Cafea cu lapte și zahăr
        Coffee sweetMilkCoffee = new SugarDecorator(milkCoffee);
        System.out.println(sweetMilkCoffee.getDescription() + " costă: " + sweetMilkCoffee.getCost() + " lei");
        
        // Cafea cu lapte, zahăr și frișcă
        Coffee deluxeCoffee = new WhippedCreamDecorator(sweetMilkCoffee);
        System.out.println(deluxeCoffee.getDescription() + " costă: " + deluxeCoffee.getCost() + " lei");
    }
}
