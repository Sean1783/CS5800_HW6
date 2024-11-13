package org.musicstreamingapp.realserver;

import org.musicstreamingapp.song.Song;
import org.musicstreamingapp.songservice.SongService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RealServer implements SongService {

    private HashMap<Integer, Song> songIdMap;
    private HashMap<String, List<Song>> songTitleMap;
    private HashMap<String, List<Song>> songAlbumMap;

    public RealServer() {
        songTitleMap = new HashMap<>();
        songAlbumMap = new HashMap<>();
        songIdMap = new HashMap<>();
    }

    public HashMap<Integer, Song> getSongIdMap() {
        return songIdMap;
    }

    public HashMap<String, List<Song>> getSongTitleMap() {
        return songTitleMap;
    }

    public HashMap<String, List<Song>> getSongAlbumMap() {
        return songAlbumMap;
    }

    @Override
    public void addSongs(List<Song> songs) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Song song : songs) {
            addSong(song);
        }
    }

    @Override
    public void addSong(Song song) {
        if (!songIdMap.containsKey(song.getSongId())) {
            songIdMap.put(song.getSongId(), song);
        }
        addToSongTitleMap(song);
        addToSongAlbumMap(song);
    }

    private void addToSongTitleMap(Song song) {
        if (!songTitleMap.containsKey(song.getTitle())) {
            List<Song> songList = new ArrayList<>();
            songList.add(song);
            songTitleMap.put(song.getTitle(), songList);
        }
        else {
            songTitleMap.get(song.getTitle()).add(song);
        }
    }

    private void addToSongAlbumMap(Song song) {
        if (!songAlbumMap.containsKey(song.getAlbum())) {
            List<Song> songList = new ArrayList<>();
            songList.add(song);
            songAlbumMap.put(song.getAlbum(), songList);
        }
        else {
            songAlbumMap.get(song.getAlbum()).add(song);
        }
    }

    @Override
    public Song searchById(Integer songID) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
         return songIdMap.get(songID);
    }

    @Override
    public List<Song> searchByTitle(String songTitle) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<> (songTitleMap.get(songTitle));
    }

    @Override
    public List<Song> searchByAlbum(String albumTitle) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(songAlbumMap.get(albumTitle));
    }
}
