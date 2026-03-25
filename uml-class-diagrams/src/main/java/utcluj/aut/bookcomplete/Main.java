package utcluj.aut.bookcomplete;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

// ── Interfaces ──

interface Downloadable {
    void download();
    String getFormat();
}

interface LicenseManaged {
    boolean isLicenseValid();
    void renewLicense(int days);
}

// ── Base Classes ──

class Page {
    private int number;
    private String content;

    Page(int number, String content) {
        this.number = number;
        this.content = content;
    }

    int getNumber() { return number; }
    String getContent() { return content; }
}

class Author {
    private String name;

    Author(String name) { this.name = name; }

    String getName() { return name; }
}

class Book {
    private String title;
    private Author author;
    private List<Page> pages;

    Book(String title, Author author) {
        this.title = title;
        this.author = author;
        this.pages = new ArrayList<>();
    }

    String getTitle() { return title; }
    Author getAuthor() { return author; }
    List<Page> getPages() { return pages; }
    void addPage(Page page) { pages.add(page); }

    @Override
    public String toString() {
        return "\"" + title + "\" by " + author.getName();
    }
}

// Reader = a DEVICE (like Kindle, Audible player)
class Reader {
    private String model;
    private String serialNumber;

    Reader(String model, String serialNumber) {
        this.model = model;
        this.serialNumber = serialNumber;
    }

    String getModel() { return model; }
    String getSerialNumber() { return serialNumber; }

    void read(Book book) {
        System.out.println("  [" + model + "] reading " + book);
    }

    @Override
    public String toString() {
        return model + " (SN: " + serialNumber + ")";
    }
}

// ── Book subclasses ──

// EBook: Downloadable only (free content, no license needed)
class EBook extends Book implements Downloadable {
    private boolean downloaded;

    EBook(String title, Author author) {
        super(title, author);
        this.downloaded = false;
    }

    @Override
    public void download() {
        this.downloaded = true;
        System.out.println("  Downloaded EBook: " + getTitle() + " [" + getFormat() + "]");
    }

    @Override
    public String getFormat() { return "EPUB"; }

    boolean isDownloaded() { return downloaded; }
}

// AudioBook: Downloadable + LicenseManaged (rented content that expires)
class AudioBook extends Book implements Downloadable, LicenseManaged {
    private LocalDate licenseExpiry;
    private boolean downloaded;

    AudioBook(String title, Author author, LocalDate licenseExpiry) {
        super(title, author);
        this.licenseExpiry = licenseExpiry;
        this.downloaded = false;
    }

    @Override
    public void download() {
        if (isLicenseValid()) {
            this.downloaded = true;
            System.out.println("  Downloaded AudioBook: " + getTitle() + " [" + getFormat() + "]");
        } else {
            System.out.println("  License expired for: " + getTitle() + ". Please renew.");
        }
    }

    @Override
    public String getFormat() { return "MP3"; }

    @Override
    public boolean isLicenseValid() {
        return LocalDate.now().isBefore(licenseExpiry);
    }

    @Override
    public void renewLicense(int days) {
        this.licenseExpiry = LocalDate.now().plusDays(days);
        System.out.println("  Content license renewed until " + licenseExpiry);
    }
}

// ── Reader (device) subclasses ──

// EBookReader: LicenseManaged only (device needs activation)
class EBookReader extends Reader implements LicenseManaged {
    private LocalDate licenseExpiry;

    EBookReader(String model, String serialNumber, LocalDate licenseExpiry) {
        super(model, serialNumber);
        this.licenseExpiry = licenseExpiry;
    }

    @Override
    public boolean isLicenseValid() {
        return LocalDate.now().isBefore(licenseExpiry);
    }

    @Override
    public void renewLicense(int days) {
        this.licenseExpiry = LocalDate.now().plusDays(days);
        System.out.println("  Device " + getModel() + " license renewed until " + licenseExpiry);
    }

    void read(EBook ebook) {
        if (!isLicenseValid()) {
            System.out.println("  Device " + getModel() + " not activated. Cannot read.");
            return;
        }
        if (!ebook.isDownloaded()) {
            System.out.println("  EBook not downloaded yet. Downloading first...");
            ebook.download();
        }
        super.read(ebook);
    }
}

// AudioBookReader: LicenseManaged only (device needs activation)
class AudioBookReader extends Reader implements LicenseManaged {
    private LocalDate licenseExpiry;

    AudioBookReader(String model, String serialNumber, LocalDate licenseExpiry) {
        super(model, serialNumber);
        this.licenseExpiry = licenseExpiry;
    }

    @Override
    public boolean isLicenseValid() {
        return LocalDate.now().isBefore(licenseExpiry);
    }

    @Override
    public void renewLicense(int days) {
        this.licenseExpiry = LocalDate.now().plusDays(days);
        System.out.println("  Device " + getModel() + " license renewed until " + licenseExpiry);
    }

    void listen(AudioBook audioBook) {
        if (!isLicenseValid()) {
            System.out.println("  Device " + getModel() + " not activated. Cannot play.");
            return;
        }
        if (!audioBook.isLicenseValid()) {
            System.out.println("  AudioBook rental expired. Cannot play " + audioBook);
            return;
        }
        System.out.println("  [" + getModel() + "] playing " + audioBook);
    }
}

// ── Composition ──

class Library {
    private String name;
    private List<Book> books;

    Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    void addBook(Book book) {
        books.add(book);
        System.out.println("  Added to " + name + ": " + book);
    }

    List<Book> getBooks() { return books; }
}

// ── Main ──

public class Main {
    public static void main(String[] args) {

        Author tolkien = new Author("J.R.R. Tolkien");
        Author orwell = new Author("George Orwell");

        // 1. Regular Book (no interfaces)
        System.out.println("=== Regular Book ===");
        Book book = new Book("The Hobbit", tolkien);
        book.addPage(new Page(1, "In a hole in the ground..."));
        System.out.println("  Created: " + book);

        // 2. EBook (Downloadable only — free content)
        System.out.println("\n=== EBook (Downloadable) ===");
        EBook ebook = new EBook("1984", orwell);
        ebook.download();

        // 3. AudioBook (Downloadable + LicenseManaged — rented content)
        System.out.println("\n=== AudioBook (Downloadable + LicenseManaged) ===");
        AudioBook audioBook = new AudioBook("The Lord of the Rings", tolkien,
                LocalDate.now().plusDays(30));
        audioBook.download();  // valid license -> works

        AudioBook expiredAudio = new AudioBook("Animal Farm", orwell,
                LocalDate.now().minusDays(1));
        expiredAudio.download();        // expired -> fails
        expiredAudio.renewLicense(60);  // renew
        expiredAudio.download();        // now works

        // 4. Regular Reader device (no interfaces)
        System.out.println("\n=== Regular Reader Device ===");
        Reader basicReader = new Reader("BasicReader", "BR-001");
        basicReader.read(book);

        // 5. EBookReader device (LicenseManaged — needs activation)
        System.out.println("\n=== EBookReader Device (LicenseManaged) ===");
        EBookReader kindle = new EBookReader("Kindle Paperwhite", "KP-2024",
                LocalDate.now().plusDays(365));
        kindle.read(ebook);  // device active + auto-downloads ebook

        EBookReader expiredKindle = new EBookReader("Old Kindle", "KP-2018",
                LocalDate.now().minusDays(30));
        expiredKindle.read(ebook);          // device expired -> fails
        expiredKindle.renewLicense(365);    // renew device
        expiredKindle.read(ebook);          // now works

        // 6. AudioBookReader device (LicenseManaged — needs activation)
        System.out.println("\n=== AudioBookReader Device (LicenseManaged) ===");
        AudioBookReader audiblePlayer = new AudioBookReader("Audible Player", "AP-100",
                LocalDate.now().plusDays(90));
        audiblePlayer.listen(audioBook);     // device active + content valid -> works
        audiblePlayer.listen(expiredAudio);  // device active but content expired -> fails

        // 7. Library (contains all Book types via polymorphism)
        System.out.println("\n=== Library ===");
        Library library = new Library("City Library");
        library.addBook(book);
        library.addBook(ebook);
        library.addBook(audioBook);

        // 8. Polymorphism via Downloadable (only books)
        System.out.println("\n=== Polymorphism: Downloadable (content) ===");
        Downloadable[] downloadables = { ebook, audioBook };
        for (Downloadable d : downloadables) {
            System.out.println("  Format: " + d.getFormat());
        }

        // 9. Polymorphism via LicenseManaged (mix of content and devices)
        System.out.println("\n=== Polymorphism: LicenseManaged (content + devices) ===");
        LicenseManaged[] licensed = { audioBook, kindle, audiblePlayer };
        for (LicenseManaged lm : licensed) {
            System.out.println("  Valid license? " + lm.isLicenseValid());
        }
    }
}