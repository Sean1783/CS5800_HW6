package org.texteditor.document;

import org.texteditor.character.CharacterProperty;
import org.texteditor.character.Character;
import org.texteditor.flyweight.CharacterPropertyFlyweight;

import java.util.ArrayList;
import java.util.List;

public class Document {

    private final String docName;
    private String rawText;
    private final List<Character> styledCharacters;

    public Document(String docName) {
        this.docName = docName;
        styledCharacters = new ArrayList<>();
    }

    public String getDocName() {
        return docName;
    }

    public List<Character> getStyledCharacters() {
//        return new ArrayList<>(styledCharacters);
        return styledCharacters;
    }

    public void setStyledCharacters(List<Character> styledCharacters) {
       this.styledCharacters.clear();
       this.styledCharacters.addAll(styledCharacters);
    //       for (Character character : styledCharacters) {
    //           String font = character.getProperties().getFont();
    //           String color = character.getProperties().getColor();
    //           int size = character.getProperties().getSize();
    //           CharacterProperty property = CharacterPropertyFlyweight.getProperty(font, color, size);
    //           Character ch = new Character(character.getValue(), property);
    //           this.styledCharacters.add(ch);
    //       }
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

        public void setStyling (CharacterProperty characterProperty) {
            styledCharacters.clear();
            for (int i = 0; i < rawText.length(); i++) {
                Character styledCharacter = new Character(rawText.charAt(i), characterProperty);
                this.styledCharacters.add(styledCharacter);
            }
        }


//    public void setStyling (CharacterPropertyFlyweight.CharProperty characterProperty) {
//        styledCharacters.clear();
//        for (int i = 0; i < rawText.length(); i++) {
//            Character styledCharacter = new Character(rawText.charAt(i), characterProperty);
//            System.out.println(styledCharacter.toString());
//            this.styledCharacters.add(styledCharacter);
//
//        }
//    }

        public void setStyling (CharacterProperty characterProperty, int start, int end) {
            if (start < 0 || end > rawText.length() || start > end) {
                throw new IllegalArgumentException("Invalid start or end indices.");
            }
            for (int i = start; i < end; i++) {
                Character styledCharacter = new Character(rawText.charAt(i), characterProperty);
                styledCharacters.set(i, styledCharacter);
            }
        }


//    public void setStyling (CharacterPropertyFlyweight.CharProperty characterProperty, int start, int end) {
//        if (start < 0 || end > rawText.length() || start > end) {
//            throw new IllegalArgumentException("Invalid start or end indices.");
//        }
//        for (int i = start; i < end; i++) {
//            Character styledCharacter = new Character(rawText.charAt(i), characterProperty);
//            styledCharacters.set(i, styledCharacter);
//        }
//    }




    public void outputCharacterDetails() {
        for (Character ch : styledCharacters) {
            System.out.println(ch.toString());
        }
    }

}
