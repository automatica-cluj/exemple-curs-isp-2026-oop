package utcluj.aut.dp.structural.adapter;

public class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;
    
    @Override
    public void play(String audioType, String fileName) {
        // Suport nativ pentru mp3
        if(audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Redare fișier MP3: " + fileName);
        }
        // Suport pentru alte formate prin adapter
        else if(audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }
        else {
            System.out.println("Format " + audioType + " nesuportat");
        }
    }
}
