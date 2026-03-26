package utcluj.aut.dp.creational.abstractfactory;

public class WindowsTextField implements TextField {
    private String text;
    private String placeholder;
    private String style = "Windows";

    @Override
    public void render() {
        System.out.println("Rendering Windows text field");
        System.out.println("Style: " + style);
        System.out.println("Placeholder: " + placeholder);
        System.out.println("Text: " + text);
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public String getPlaceholder() {
        return placeholder;
    }
} 