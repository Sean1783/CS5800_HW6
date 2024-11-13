import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        ProxyServerTest.class,
        RealServerTest.class,
        SongTest.class
})public class CompleteProxyTest {}
