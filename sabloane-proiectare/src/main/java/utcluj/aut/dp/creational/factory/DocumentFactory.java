package utcluj.aut.dp.creational.factory;

public class DocumentFactory {
    public Document createDocument(String type) {
        switch (type.toLowerCase()) {
            case "pdf":
                return new PDFDocument();
            case "word":
                return new WordDocument();
            case "text":
                return new TextDocument();
            default:
                throw new IllegalArgumentException("Unknown document type: " + type);
        }
    }
} 