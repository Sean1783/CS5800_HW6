import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.texteditor.flyweight.Flyweight;

public class FlyweightTest {

    @Test
    public void getPropertyTest() {
        Flyweight.CharProps mockCharProperties = Flyweight.getProperty("Arial", "Black", 16);
        Flyweight.CharProps secondaryMockCharProperties = Flyweight.getProperty("Arial", "Black", 16);
        Flyweight.CharProps tertiaryMockCharProperties = Flyweight.getProperty("Arial", "Red", 16);
        Flyweight.CharProps quaternaryMockCharProperties = Flyweight.getProperty("", "", -1);
        assertEquals(mockCharProperties, secondaryMockCharProperties);
        assertNotEquals(mockCharProperties, tertiaryMockCharProperties);
        assertNotNull(quaternaryMockCharProperties);
    }
}
