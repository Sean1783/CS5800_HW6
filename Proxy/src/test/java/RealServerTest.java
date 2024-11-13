import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.musicstreamingapp.realserver.RealServer;
import org.musicstreamingapp.song.Song;
import org.musicstreamingapp.songservice.SongService;

import java.util.HashMap;
import java.util.List;

public class RealServerTest {

    SongService realServer;
    Song mockSong;

    @BeforeEach
    public void setUp() {
        realServer = new RealServer();
        mockSong = mock(Song.class);
        when(mockSong.getSongId()).thenReturn(1);
        when(mockSong.getAlbum()).thenReturn("Test Album");
        when(mockSong.getTitle()).thenReturn("Test Title");
        realServer.addSong(mockSong);
    }

    @Test
    public void addSingleSongTest() {
        RealServer altRealServer = new RealServer();
        altRealServer.addSong(mockSong);
        HashMap<Integer, Song> songIdMap = altRealServer.getSongIdMap();
        HashMap<String, List<Song>> songTitleMap = altRealServer.getSongTitleMap();
        HashMap<String, List<Song>> songAlbumMap = altRealServer.getSongAlbumMap();
        Song songFromIdMap = songIdMap.get(1);
        Song songFromTitleMap = songTitleMap.get("Test Title").get(0);
        Song songFromAlbumMap = songAlbumMap.get("Test Album").get(0);
        assertEquals(1, songIdMap.size());
        assertEquals("Test Title", songFromIdMap.getTitle());
        assertEquals("Test Album", songFromIdMap.getAlbum());
        assertEquals("Test Title", songFromTitleMap.getTitle());
        assertEquals("Test Album", songFromAlbumMap.getAlbum());
    }

    @Test
    public void searchByIdTest() {
        Song searchResult = realServer.searchById(1);
        assertEquals("Test Title", searchResult.getTitle());
        assertEquals("Test Album", searchResult.getAlbum());
    }

    @Test
    public void searchByTitleTest() {
        List<Song> searchResult = realServer.searchByTitle("Test Title");
        assertEquals("Test Title", searchResult.get(0).getTitle());
        assertEquals("Test Album", searchResult.get(0).getAlbum());
    }

    @Test
    public void searchByAlbumTest() {
        List<Song> searchResult = realServer.searchByAlbum("Test Album");
        assertEquals("Test Title", searchResult.get(0).getTitle());
        assertEquals("Test Album", searchResult.get(0).getAlbum());
    }


}
