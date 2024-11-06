package org.texteditor;

import org.texteditor.character.Character;
import org.texteditor.character.CharacterProperty;
import org.texteditor.document.Document;
import org.texteditor.filemanager.FileManager;
import org.texteditor.flyweight.CharacterPropertyFlyweight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        CharacterProperty p1 = CharacterPropertyFlyweight.getProperty("Arial", "Red", 12);
        CharacterProperty p2 = CharacterPropertyFlyweight.getProperty("Veranda", "Blue", 14);
        CharacterProperty p3 = CharacterPropertyFlyweight.getProperty("Calibri", "Black", 16);
        CharacterProperty p4 = CharacterPropertyFlyweight.getProperty("Wingdings", "Pink", 99);

//        CharacterPropertyFlyweight.CharProperty p1 = CharacterPropertyFlyweight.getProperty("Arial", "Red", 12);
//        CharacterPropertyFlyweight.CharProperty p2 = CharacterPropertyFlyweight.getProperty("Veranda", "Blue", 14);
//        CharacterPropertyFlyweight.CharProperty p3 = CharacterPropertyFlyweight.getProperty("Calibri", "Black", 16);
//        CharacterPropertyFlyweight.CharProperty p4 = CharacterPropertyFlyweight.getProperty("Wingdings", "Pink", 99);

        Document documentOne = new Document("TestDoc.json");
        documentOne.setRawText("HelloWorldCS5800");
        documentOne.setStyling(p1);
        documentOne.setStyling(p2, 3, 5);
        documentOne.setStyling(p3, 5, 7);
        documentOne.setStyling(p4, 7, 9);
        documentOne.outputCharacterDetails();

        FileManager.saveFile(documentOne);

        try {
            Document documentTwo = FileManager.loadFileToDoc("TestDoc.json");
            documentTwo.outputCharacterDetails();
        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }

    }
}