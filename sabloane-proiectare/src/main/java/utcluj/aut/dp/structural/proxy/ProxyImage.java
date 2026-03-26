package utcluj.aut.dp.structural.proxy;

public class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;
    
    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public void display() {
        // Lazy initialization - creează obiectul real doar când este necesar
        if(realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
