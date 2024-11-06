package org.texteditor;

import org.texteditor.document.Document;
import org.texteditor.filemanager.FileManager;
import org.texteditor.flyweight.Flyweight;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Flyweight.CharProps p1 = Flyweight.getProperty("Arial", "Red", 12);
        Flyweight.CharProps p2 = Flyweight.getProperty("Veranda", "Blue", 14);
        Flyweight.CharProps p3 = Flyweight.getProperty("Calibri", "Black", 16);
        Flyweight.CharProps p4 = Flyweight.getProperty("Wingdings", "Pink", 99);

        Document documentOne = new Document("TestDoc.json");
        documentOne.setRawText("HelloWorldCS5800");
        documentOne.setStyling(p1);
        documentOne.setStyling(p2, 3, 5);
        documentOne.setStyling(p3, 5, 7);
        documentOne.setStyling(p4, 7, 9);
        documentOne.setStyling(p2, new Document.Range(9, 12));
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