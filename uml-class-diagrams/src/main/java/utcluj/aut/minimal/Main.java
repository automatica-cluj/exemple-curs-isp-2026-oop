package utcluj.aut.minimal;


import java.util.ArrayList;

class Page{
    private int n;
    public Page(int i) {
    }
}

class Book {
    ArrayList<Page> pages = new ArrayList<>();

    public Book() {
        this.pages = new ArrayList<>();
        pages.add(new Page(1));
        pages.add(new Page(2));
        pages.add(new Page(3));
    }
}

public class Main {
    public static void main(String[] args) {
        Book book = new Book();
        System.out.println("Book has " + book.pages.size() + " pages.");
    }
}
