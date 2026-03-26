package utcluj.aut.dp.structural.facade;

public class CodecFactory {
    public static Codec extract(VideoFile file) {
        String type = file.getCodecType();
        if (type.equals("mp4")) {
            System.out.println("CodecFactory: extragere codec mpeg4...");
            return new MPEG4CompressionCodec();
        } else {
            System.out.println("CodecFactory: extragere codec ogg...");
            return new OggCompressionCodec();
        }
    }
}
