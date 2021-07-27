package com.skjilygao.design.patterns;

/**
 * 适配器模式
 * @author skyjilygao
 */
public class AdapterPattern {

public static void main(String[] args) {
    AdapterPattern pattern = new AdapterPattern();
    pattern.adapter();
}

private void adapter() {
    AudioPlayer audioPlayer = new AudioPlayer();
    audioPlayer.play("mp3", "aa.mp3");
    audioPlayer.play("mp4", "aa.mp4");
    audioPlayer.play("vlc", "aa.vlc");
    audioPlayer.play("mov", "aa.mov");
}

/**
 * 媒体播放器
 */
public interface MediaPlayer{
    void play(String mediaType, String filename);
}

/**
 * 高级播放器
 */
public interface AdvancedMediaPlayer{
    void playVlc(String filename);
    void playMp4(String filename);
}

/**
 * vlc播放器
 */
public class VlcPlayer implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String filename) {
        System.out.println("playing vlc file.");
    }

    @Override
    public void playMp4(String filename) {

    }
}


/**
 * MP4播放器
 */
public class Mp4Player implements AdvancedMediaPlayer{
    @Override
    public void playVlc(String filename) {

    }

    @Override
    public void playMp4(String filename) {
        System.out.println("playing mp4 file.");
    }
}

/**
 * 媒体播放适配器
 */
public class MediaAdapter implements MediaPlayer{
    private AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String type) {
        switch (type){
            case "vlc": advancedMediaPlayer = new VlcPlayer(); break;
            case "mp4": advancedMediaPlayer = new Mp4Player(); break;
            default: throw new IllegalArgumentException(type + " is not supported");
        }
    }

    @Override
    public void play(String mediaType, String filename) {
        switch (mediaType){
            case "vlc": advancedMediaPlayer.playVlc(filename); break;
            case "mp4": advancedMediaPlayer.playMp4(filename); break;
            default: break;
        }
    }
}

/**
 * 音频播放器
 */
public class AudioPlayer implements MediaPlayer{
    MediaAdapter mediaAdapter;
    @Override
    public void play(String mediaType, String filename) {
        switch (mediaType){
            case "mp3":
                System.out.println("playing mp3 file.");
                break;
            case "mp4":
            case "vlc":
                mediaAdapter = new MediaAdapter(mediaType);
                mediaAdapter.play(mediaType, filename);
                break;
            default:
                System.out.println("not supported");
                break;
        }
    }
}

}
