package org.musicstreamingapp.song;

public class Song {
    private Integer songId;
    private String title;
    private String album;
    private String artist;
    private int duration;
    private static Integer counter = 0;

    private Song () {}

    public Song(String title, String album, String artist, int duration) {
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.duration = duration;
        this.songId = counter++;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public Integer getSongId() {
        return songId;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public static Integer getCounter() {
        return counter;
    }

    public void dump() {
        System.out.println("{\n\tSong ID: " + songId +
                ",\n\tArtist: " + artist +
                ",\n\tTitle: " + title +
                ",\n\tDuration: " + duration +
                ",\n\tAlbum: " + album + "\n}");
    }
}
