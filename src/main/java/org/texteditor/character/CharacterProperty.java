package org.texteditor.character;

import org.texteditor.flyweight.CharacterPropertyFlyweight;

public class CharacterProperty {

    private String font;
    private String color;
    private int size;

    public CharacterProperty(String font, String color, int size) {
        this.font = font;
        this.color = color;
        this.size = size;
//        if (CharacterPropertyFlyweight.keyExists(font, color, size)) {
//
//        }
//        CharacterPropertyFlyweight.getProperty(font, color, size);
    }

    public String getFont() {
        return font;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public String propertyKey() {
        return font + "." + color + "." + size;
    }

}
