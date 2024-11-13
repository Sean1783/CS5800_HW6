import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.texteditor.character.Character;
import org.texteditor.document.Document;
import org.texteditor.flyweight.Flyweight;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DocumentTest {

    Document document;
    Character mockCharacter;
    Flyweight.CharProps mockCharProperties;

    @BeforeEach
    public void setUp() {
        document = new Document("TestDoc");
        mockCharProperties = mock(Flyweight.CharProps.class);
        when(mockCharProperties.getFont()).thenReturn("Arial");
        when(mockCharProperties.getColor()).thenReturn("Black");
        when(mockCharProperties.getSize()).thenReturn(16);
        mockCharacter = mock(Character.class);
        when(mockCharacter.getProperties()).thenReturn(mockCharProperties);
        when(mockCharacter.getValue()).thenReturn('x');
    }

    @Test
    public void setStylingTest() {
        document.setStyling(mockCharProperties);
        document.setRawText("This is raw text.");
        boolean isCorrect = true;
        for (Character character : document.getStyledCharacters()) {
            String font = character.getProperties().getFont();
            String color = character.getProperties().getColor();
            int size = character.getProperties().getSize();
            if (!Objects.equals(font, mockCharacter.getProperties().getFont())) {
                isCorrect = false;
                break;
            }
            if (!Objects.equals(color, mockCharacter.getProperties().getColor())) {
                isCorrect = false;
                break;
            }
            if (size != mockCharacter.getProperties().getSize()) {
                isCorrect = false;
                break;
            }
        }
        assertTrue(isCorrect);
    }

    @Test
    public void rangedSetStylingTest() {
        document.setStyling(mockCharProperties);
        document.setRawText("This is raw text.");
        Document.Range tooHigh = new Document.Range(0, 100);
        Document.Range tooLow = new Document.Range(-1, 100);
        assertThrows(IllegalArgumentException.class, () -> document.setStyling(mockCharProperties, tooHigh));
        assertThrows(IllegalArgumentException.class, () -> document.setStyling(mockCharProperties, tooLow));
    }


    @Test
    public void setStyledCharactersTest() {
        List<Character> styledCharacterList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Character mockedCharacter = mock(Character.class);
            styledCharacterList.add(mockedCharacter);
        }
        document.setStyledCharacters(styledCharacterList);
        assertEquals(styledCharacterList, document.getStyledCharacters());
        assertEquals(styledCharacterList.get(50), document.getStyledCharacters().get(50));
    }
}
