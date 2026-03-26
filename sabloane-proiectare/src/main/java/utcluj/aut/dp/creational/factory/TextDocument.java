package utcluj.aut.dp.creational.factory;

import java.util.ArrayList;
import java.util.List;

public class TextDocument implements Document {
    private String content;
    private List<String> pages;
    private boolean isOpen;
    private static final String TYPE = "Text";

    public TextDocument() {
        this.pages = new ArrayList<>();
        this.isOpen = false;
    }

    @Override
    public void open() {
        isOpen = true;
        System.out.println("Opening Text document...");
    }

    @Override
    public void save() {
        if (!isOpen) {
            throw new IllegalStateException("Cannot save closed document");
        }
        System.out.println("Saving Text document...");
        System.out.println("Content: " + content);
        System.out.println("Total pages: " + pages.size());
    }

    @Override
    public void close() {
        isOpen = false;
        System.out.println("Closing Text document...");
    }

    @Override
    public void setContent(String content) {
        this.content = content;
        System.out.println("Setting Text content...");
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public int getPageCount() {
        return pages.size();
    }

    @Override
    public void addPage(String content) {
        pages.add(content);
        System.out.println("Adding new page to Text document...");
    }
} 