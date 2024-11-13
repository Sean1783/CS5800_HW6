import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import org.texteditor.document.Document;
import org.texteditor.filemanager.FileManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;

public class FileManagerTest {

    @TempDir
    Path tempDir;

    @Test
    public void saveUnnamedFileTest() {
        Document document = new Document("");
        assertThrows(FileNotFoundException.class, () -> FileManager.saveFile(document));
    }

    @Test
    public void saveFileTest() {
        Document document = new Document(tempDir.resolve("UnitTestDoc").toString());
        assertDoesNotThrow(() -> FileManager.saveFile(document));
        File file = tempDir.resolve("UnitTestDoc").toFile();
        assertTrue(file.exists(), "File should be created by saveFile method");
    }

    @Test
    public void loadFileToDocTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("value", "x");
        ObjectNode propertiesNode = mapper.createObjectNode();
        propertiesNode.put("font", "Arial");
        propertiesNode.put("color", "Red");
        propertiesNode.put("size", 12);
        rootNode.set("properties", propertiesNode);

        File file = tempDir.resolve("UnitTestDoc.json").toFile();
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, rootNode);
        assertDoesNotThrow(() -> FileManager.loadFileToDoc(file.getAbsolutePath()));
    }


}
