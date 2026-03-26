package utcluj.aut.dp.structural.facade;

public class FacadeDemo {
    public static void main(String[] args) {
        VideoConversionFacade converter = new VideoConversionFacade();
        File mp4Video = converter.convertVideo("youtubevideo.ogg", "mp4");
        // Clientul utilizează doar metoda simplă convertVideo()
    }
}
