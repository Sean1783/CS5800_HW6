package org.texteditor.filemanager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.texteditor.character.CharacterProperty;
import org.texteditor.document.Document;
import org.texteditor.character.Character;
import org.texteditor.flyweight.CharacterPropertyFlyweight;

import javax.print.Doc;

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
//        System.out.println("Document loaded from JSON file " + filename);
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
//            CharacterProperty properties = new CharacterProperty(font, color, size);
            CharacterProperty properties = CharacterPropertyFlyweight.getProperty(font, color, size);
//            CharacterPropertyFlyweight.CharProperty properties = CharacterPropertyFlyweight.getProperty(font, color, size);
//            Character character = new Character(value, properties);
            Character character = new Character(value, properties);
            styledCharacters.add(character);
        }
        doc.setStyledCharacters(styledCharacters);
        return doc;
    }

}
