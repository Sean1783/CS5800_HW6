package org.texteditor.document;

import org.texteditor.character.Character;
import org.texteditor.flyweight.Flyweight;

import java.util.ArrayList;
import java.util.List;

public class Document {

    private final String docName;
    private String rawText;
    private final List<Character> styledCharacters;

    public static class Range {
        private final int start;
        private final int end;
        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public int getStart() {
            return start;
        }
        public int getEnd() {
            return end;
        }
    }

    public Document(String docName) {
        this.docName = docName;
        styledCharacters = new ArrayList<>();
    }

    public String getDocName() {
        return docName;
    }

    public List<Character> getStyledCharacters() {
        return styledCharacters;
    }

    public void setStyledCharacters(List<Character> styledCharacters) {
       this.styledCharacters.clear();
       this.styledCharacters.addAll(styledCharacters);
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public void setStyling (Flyweight.CharProps characterProperty) {
        styledCharacters.clear();
        if (rawText != null) {
            for (int i = 0; i < rawText.length(); i++) {
                Character styledCharacter = new Character(rawText.charAt(i), characterProperty);
                this.styledCharacters.add(styledCharacter);
            }
        }
    }

    public void setStyling (Flyweight.CharProps characterProperty, Document.Range range) {
        int start = range.getStart();
        int end = range.getEnd();
        if (rawText != null) {
            if (start < 0 || end > rawText.length() || start > end) {
                throw new IllegalArgumentException("Invalid start or end indices.");
            }
            for (int i = start; i < end; i++) {
                Character styledCharacter = new Character(rawText.charAt(i), characterProperty);
                styledCharacters.set(i, styledCharacter);
            }
        }
    }

    public void outputCharacterDetails() {
        for (Character ch : styledCharacters) {
            System.out.println(ch.toString());
        }
    }

}
