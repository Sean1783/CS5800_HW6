import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.texteditor.character.Character;
import org.texteditor.flyweight.Flyweight;

public class CharacterTest {

    Character character;
    Flyweight.CharProps mockCharProperties;
    Flyweight.CharProps altMockCharProperties;

    @BeforeEach
    public void setUp() {
        mockCharProperties = mock(Flyweight.CharProps.class);
        when(mockCharProperties.getFont()).thenReturn("Arial");
        when(mockCharProperties.getColor()).thenReturn("Black");
        when(mockCharProperties.getSize()).thenReturn(16);

        altMockCharProperties = mock(Flyweight.CharProps.class);
        when(altMockCharProperties.getFont()).thenReturn("Times");
        when(altMockCharProperties.getColor()).thenReturn("Red");
        when(altMockCharProperties.getSize()).thenReturn(12);
    }

    @Test
    public void characterInitTest() {
        character = new Character();
        Character c2 = new Character('x', mockCharProperties);
        assertNotNull(character);
        assertNotNull(c2);
    }

    @Test
    public void initCharPropsTest() {
        character = new Character('x', mockCharProperties);
        assertEquals("Arial", character.getProperties().getFont());
        assertEquals("Black", character.getProperties().getColor());
        assertEquals(16, character.getProperties().getSize());
    }

    @Test
    public void setCharPropsTest() {
        character = new Character('x', mockCharProperties);
        character.setProperties(altMockCharProperties);
        assertEquals("Times", character.getProperties().getFont());
        assertEquals("Red", character.getProperties().getColor());
        assertEquals(12, character.getProperties().getSize());
    }

}
