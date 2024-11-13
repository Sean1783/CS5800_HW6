import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Field;

import org.musicstreamingapp.song.Song;

public class SongTest {

    @BeforeEach
    public void setUp() throws Exception {
        Field counterField = Song.class.getDeclaredField("counter");
        counterField.setAccessible(true);
        counterField.set(null, 0);
    }

    @Test
    public void testSongConstructor() {
        Song song = new Song("Title1", "Album1", "Artist1", 300);
        assertNotNull(song);
        assertEquals("Title1", song.getTitle());
        assertEquals("Album1", song.getAlbum());
        assertEquals("Artist1", song.getArtist());
        assertEquals(300, song.getDuration());
    }

    @Test
    public void testSongId() {
        Song song1 = new Song("Title1", "Album1", "Artist1", 300);
        Song song2 = new Song("Title2", "Album2", "Artist2", 250);
        assertEquals(0, song1.getSongId()); // First song ID should be 0
        assertEquals(1, song2.getSongId()); // Second song ID should be 1
    }

    @Test
    public void testIncrement() {
        Song song1 = new Song("Song1", "Album1", "Artist1", 200);
        Song song2 = new Song("Song2", "Album2", "Artist2", 210);
        Song song3 = new Song("Song3", "Album3", "Artist3", 180);
        assertEquals(3, Song.getCounter());
    }
}
