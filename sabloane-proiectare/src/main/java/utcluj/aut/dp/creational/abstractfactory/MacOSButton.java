package utcluj.aut.dp.creational.abstractfactory;

public class MacOSButton implements Button {
    private String text;
    private String style = "MacOS";

    @Override
    public void render() {
        System.out.println("Rendering MacOS button with text: " + text);
        System.out.println("Style: " + style);
    }

    @Override
    public void onClick() {
        System.out.println("MacOS button clicked!");
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