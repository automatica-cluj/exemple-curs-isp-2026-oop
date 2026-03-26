package utcluj.aut.dp.creational.factory;

public interface Document {
    void open();
    void save();
    void close();
    void setContent(String content);
    String getContent();
    String getType();
    int getPageCount();
    void addPage(String content);
} 