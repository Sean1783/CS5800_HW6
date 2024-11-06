package org.texteditor.filemanager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.texteditor.document.Document;
import org.texteditor.character.Character;
import org.texteditor.flyweight.Flyweight;

public class FileManager {

    public static void saveFile(Document document) {

        String filename = document.getDocName();
        List<Character> characterList = document.getStyledCharacters();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(new File(filename), characterList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Character> loadFile(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Character> characters = mapper.readValue(new File(filename),
                mapper.getTypeFactory().constructCollectionType(List.class, Character.class));
        return characters;
    }

    public static Document loadFileToDoc(String filename) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Document doc = new Document(filename);
        List<Character> styledCharacters = new ArrayList<>();
        JsonNode root = mapper.readTree(new File(filename));
        for (JsonNode node : root) {
            char value = node.get("value").asText().charAt(0);
            JsonNode propertiesNode = node.get("properties");
            String font = propertiesNode.get("font").asText();
            String color = propertiesNode.get("color").asText();
            int size = propertiesNode.get("size").asInt();
            Flyweight.CharProps properties = Flyweight.getProperty(font, color, size);
            Character character = new Character(value, properties);
            styledCharacters.add(character);
        }
        doc.setStyledCharacters(styledCharacters);
        return doc;
    }

}
