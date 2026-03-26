package utcluj.aut.dp.creational.abstractfactory;

public class UIFactoryDemo {
    public static void main(String[] args) {
        // Create Windows UI components
        System.out.println("Creating Windows UI components:");
        UIFactory windowsFactory = new WindowsUIFactory();
        Button windowsButton = windowsFactory.createButton();
        TextField windowsTextField = windowsFactory.createTextField();
        
        windowsButton.setText("Click me!");
        windowsTextField.setPlaceholder("Enter text...");
        windowsTextField.setText("Hello Windows!");
        
        windowsButton.render();
        windowsTextField.render();
        
        // Create MacOS UI components
        System.out.println("\nCreating MacOS UI components:");
        UIFactory macFactory = new MacOSUIFactory();
        Button macButton = macFactory.createButton();
        TextField macTextField = macFactory.createTextField();
        
        macButton.setText("Click me!");
        macTextField.setPlaceholder("Enter text...");
        macTextField.setText("Hello MacOS!");
        
        macButton.render();
        macTextField.render();
    }
} 