package org.texteditor.filemanager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.texteditor.document.Document;
import org.texteditor.character.Character;
import org.texteditor.flyweight.Flyweight;

public class FileManager {

    private static class CharacterDetails {
        String font;
        String color;
        int size;
    }

    public static void saveFile(Document document) throws FileNotFoundException {

        String filename = document.getDocName();
        if (Objects.equals(filename, "")) {
            throw new FileNotFoundException("File name cannot be empty");
        }
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
        StringBuilder rawText = new StringBuilder();
        if (root.isArray()) {
            for (JsonNode node : root) {
                char value = node.get("value").asText().charAt(0);
                rawText.append(value);
                Character character = makeCharacter(value, node);
                styledCharacters.add(character);
            }
        } else if (root.isObject()) {
            JsonNode node = root;
            char value = node.get("value").asText().charAt(0);
            rawText.append(value);
            Character character = makeCharacter(value, node);
            styledCharacters.add(character);
        }
        doc.setRawText(rawText.toString());
        doc.setStyledCharacters(styledCharacters);
        return doc;
    }

    private static Character makeCharacter(char value, JsonNode node) {
        CharacterDetails details = makeCharacterDetails(node);
        Flyweight.CharProps properties = getCharProps(details);
        return new Character(value, properties);
    }

    private static CharacterDetails makeCharacterDetails(JsonNode node) {
        CharacterDetails details = new CharacterDetails();
        JsonNode propertiesNode = node.get("properties");
        details.font = propertiesNode.get("font").asText();
        details.color = propertiesNode.get("color").asText();
        details.size = propertiesNode.get("size").asInt();
        return details;
    }

    private static Flyweight.CharProps getCharProps(CharacterDetails characterDetails) {
        String font = characterDetails.font;
        String color = characterDetails.color;
        int size = characterDetails.size;
        return Flyweight.getProperty(font, color, size);
    }

}
