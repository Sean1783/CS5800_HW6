import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        CompleteFlyweightUnitTest.class,
        CharacterTest.class,
        DocumentTest.class,
        FlyweightTest.class,
        FileManagerTest.class,
        DocumentTest.class}) public class CompleteFlyweightUnitTest {}

