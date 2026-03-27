package utcluj.aut.minimal;


import java.util.ArrayList;

class Page{
    private int n;
    public Page(int i) {
    }
}

class Book2{
    private Page[] pages = new Page[5];

    Book2(){
        pages[0] = new Page(2);
    }

    void prapareBook(){

    }
}

class Book {
    private ArrayList<Page> pages = new ArrayList<>();

    public Book() {
        this.pages = new ArrayList<>();
        pages.add(new Page(1));
        pages.add(new Page(2));
        pages.add(new Page(3));
    }
}

class Author{

}

class Book3{

    private Author author;

    public Book3(Author author) {
        this.author = author;
    }
}

class Person{
    public void readBook(Book b){
        System.out.println("Reading "+ b);
    }
}

public class Main {
    public static void main(String[] args) {
        Book book = new Book();
        System.out.println("Book has " + book.pages.size() + " pages.");
    }
}
