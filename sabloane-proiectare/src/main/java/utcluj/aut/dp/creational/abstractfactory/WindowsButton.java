package utcluj.aut.dp.creational.abstractfactory;

public class WindowsButton implements Button {
    private String text;
    private String style = "Windows";

    @Override
    public void render() {
        System.out.println("Rendering Windows button with text: " + text);
        System.out.println("Style: " + style);
    }

    @Override
    public void onClick() {
        System.out.println("Windows button clicked!");
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }
} 