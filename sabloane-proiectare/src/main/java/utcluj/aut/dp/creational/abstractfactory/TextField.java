package utcluj.aut.dp.creational.abstractfactory;

public interface TextField {
    void render();
    void setText(String text);
    String getText();
    void setPlaceholder(String placeholder);
    String getPlaceholder();
} 