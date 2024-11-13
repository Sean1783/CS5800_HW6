package org.musicstreamingapp.proxyserver;

import org.musicstreamingapp.song.Song;
import org.musicstreamingapp.songservice.SongService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProxyServer implements SongService {

    private SongService realServer;
    private HashMap<Integer, Song> songIdMap;
    private HashMap<String, List<Song>> songTitleMap;
    private HashMap<String, List<Song>> songAlbumMap;
    private List<Song> addedSongsBuffer;
    private final int BUFFER_SIZE = 3; // For simulating asynchronous song add to real server.

    private ProxyServer() {}

    public ProxyServer(SongService realServer) {
        this.realServer = realServer;
        songIdMap = new HashMap<>();
        songTitleMap = new HashMap<>();
        songAlbumMap = new HashMap<>();
        addedSongsBuffer = new ArrayList<>();
    }

    public HashMap<Integer, Song> getSongIdMap() {
        return new HashMap<>(songIdMap);
    }

    public HashMap<String, List<Song>> getSongTitleMap() {
        return new HashMap<>(songTitleMap);
    }

    public HashMap<String, List<Song>> getSongAlbumMap() {
        return new HashMap<>(songAlbumMap);
    }

    @Override
    public void addSong(Song song) {
        if (!songIdMap.containsKey(song.getSongId())) {
            internalAddSong(song);
            addedSongsBuffer.add(song);
            if (addedSongsBuffer.size() == BUFFER_SIZE) {
                realServer.addSongs(addedSongsBuffer);
                addedSongsBuffer.clear();
            }
        }
    }

    private void internalAddSong(Song song) {
        songIdMap.put(song.getSongId(), song);
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
    public void addSongs(List<Song> songs) {
        for (Song song : songs) {
            addSong(song);
        }
    }

    @Override
    public Song searchById(Integer songID) {
        if (songIdMap.containsKey(songID)) {
            return songIdMap.get(songID);
        }
        Song song = realServer.searchById(songID);
        internalAddSong(song);
        return song;
    }

    @Override
    public List<Song> searchByTitle(String songTitle) {
        List<Song> songList;
        if (songTitleMap.containsKey(songTitle)) {
            songList = songTitleMap.get(songTitle);
        }
        else {
            songList = realServer.searchByTitle(songTitle);
            addSongs(songList);
        }
        return songList;
    }

    @Override
    public List<Song> searchByAlbum(String albumTitle) {
        List<Song> songList;
        if (songAlbumMap.containsKey(albumTitle)) {
            songList = songAlbumMap.get(albumTitle);
        }
        else {
            songList = realServer.searchByAlbum(albumTitle);
            addSongs(songList);
        }
        return songList;
    }

}
