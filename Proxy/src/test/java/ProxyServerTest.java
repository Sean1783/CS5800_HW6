import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.musicstreamingapp.proxyserver.ProxyServer;
import org.musicstreamingapp.realserver.RealServer;
import org.musicstreamingapp.song.Song;
import org.musicstreamingapp.songservice.SongService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProxyServerTest {

    SongService proxyServer;
    Song mockSong;
    Song altMockSong;

    @BeforeEach
    public void setUp() {
        proxyServer = new ProxyServer(new RealServer());
        mockSong = mock(Song.class);
        when(mockSong.getSongId()).thenReturn(1);
        when(mockSong.getAlbum()).thenReturn("Test Album");
        when(mockSong.getTitle()).thenReturn("Test Title");
        proxyServer.addSong(mockSong);
        altMockSong = mock(Song.class);
        when(altMockSong.getSongId()).thenReturn(2);
        when(altMockSong.getAlbum()).thenReturn("Test Album");
        when(altMockSong.getTitle()).thenReturn("Alternate Test Title");
        proxyServer.addSong(altMockSong);
    }

    @Test
    public void addSingleSongTest() {
        ProxyServer altProxyServer = new ProxyServer(new RealServer());
        altProxyServer.addSong(mockSong);
        altProxyServer.addSong(altMockSong);
        HashMap<Integer, Song> songIdMap = altProxyServer.getSongIdMap();
        HashMap<String, List<Song>> songTitleMap = altProxyServer.getSongTitleMap();
        HashMap<String, List<Song>> songAlbumMap = altProxyServer.getSongAlbumMap();
        Song songFromIdMap = songIdMap.get(1);
        Song songFromTitleMap = songTitleMap.get("Test Title").get(0);
        Song songFromAlbumMap = songAlbumMap.get("Test Album").get(0);
        assertEquals(2, songIdMap.size());
        assertEquals("Test Title", songFromIdMap.getTitle());
        assertEquals("Test Title", songFromTitleMap.getTitle());
        assertEquals("Test Album", songFromAlbumMap.getAlbum());
    }

    @Test
    public void addSongsTest() {
        ProxyServer auxProxyServer = new ProxyServer(new RealServer());
        List<Song> songList = new ArrayList<Song>();
        for (int i = 0; i < 10; i++) {
            String value = String.valueOf(i);
            Song song = new Song("title" + value, "album" + value, "artist" + value, i);
            songList.add(song);
        }
        auxProxyServer.addSongs(songList);
        Song song = auxProxyServer.getSongIdMap().get(5);
        assertEquals("title5", song.getTitle());
        assertEquals(10, auxProxyServer.getSongIdMap().size());
    }

    @Test
    public void searchByIdTest() {
        Song searchResult = proxyServer.searchById(1);
        Song altSearchResult = proxyServer.searchById(2);
        assertEquals("Test Title", searchResult.getTitle());
        assertEquals("Test Album", searchResult.getAlbum());
        assertEquals("Alternate Test Title", altSearchResult.getTitle());
        assertEquals("Test Album", altSearchResult.getAlbum());
    }

    @Test
    public void searchByTitleTest() {
        List<Song> searchResult = proxyServer.searchByTitle("Test Title");
        assertEquals(1, searchResult.size());
        assertEquals("Test Title", searchResult.get(0).getTitle());
    }

    @Test
    public void searchByAlbumTest() {
        List<Song> searchResult = proxyServer.searchByAlbum("Test Album");
        assertEquals(2, searchResult.size());
        assertEquals("Test Title", searchResult.get(0).getTitle());
        assertEquals("Test Album", searchResult.get(0).getAlbum());
    }
}
