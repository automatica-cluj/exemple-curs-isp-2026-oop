package utcluj.aut.dp.creational.factory;

public class DocumentFactoryDemo {
    public static void main(String[] args) {
        DocumentFactory factory = new DocumentFactory();

        // Create and work with a PDF document
        System.out.println("Working with PDF document:");
        Document pdfDoc = factory.createDocument("pdf");
        pdfDoc.open();
        pdfDoc.setContent("This is a PDF document");
        pdfDoc.addPage("Page 1 content");
        pdfDoc.addPage("Page 2 content");
        System.out.println("Document type: " + pdfDoc.getType());
        System.out.println("Page count: " + pdfDoc.getPageCount());
        pdfDoc.save();
        pdfDoc.close();

        // Create and work with a Word document
        System.out.println("\nWorking with Word document:");
        Document wordDoc = factory.createDocument("word");
        wordDoc.open();
        wordDoc.setContent("This is a Word document");
        wordDoc.addPage("Page 1 content");
        System.out.println("Document type: " + wordDoc.getType());
        System.out.println("Page count: " + wordDoc.getPageCount());
        wordDoc.save();
        wordDoc.close();

        // Create and work with a Text document
        System.out.println("\nWorking with Text document:");
        Document textDoc = factory.createDocument("text");
        textDoc.open();
        textDoc.setContent("This is a Text document");
        textDoc.addPage("Page 1 content");
        System.out.println("Document type: " + textDoc.getType());
        System.out.println("Page count: " + textDoc.getPageCount());
        textDoc.save();
        textDoc.close();
    }
} 