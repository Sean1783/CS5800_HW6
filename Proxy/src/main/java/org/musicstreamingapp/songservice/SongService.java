package org.musicstreamingapp.songservice;

import org.musicstreamingapp.song.Song;

import java.util.List;

public interface SongService {
    Song searchById(Integer songID);
    List<Song> searchByTitle(String songTitle);
    List<Song> searchByAlbum(String albumTitle);
    void addSongs(List<Song> songs);
    void addSong(Song song);
}
