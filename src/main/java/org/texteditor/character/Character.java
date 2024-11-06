package org.texteditor.character;

import org.texteditor.flyweight.CharacterPropertyFlyweight;

public class Character {

    private char value;
    private CharacterProperty properties;
//    private CharacterPropertyFlyweight.CharProperty properties;

    public Character() {}

    public Character(char value, CharacterProperty properties) {
//    public Character(char value, CharacterPropertyFlyweight.CharProperty properties) {
        this.value = value;
        this.properties = properties;
//        String font = properties.getFont();
//        String color = properties.getColor();
//        int size = properties.getSize();
//        this.properties = CharacterPropertyFlyweight.getProperty(font, color, size);

    }

//    public Character(char value, CharacterProperty properties) {
//        this.value = value;
//        this.properties = properties;
//
//
//
////        String font = properties.getFont();
////        String color = properties.getColor();
////        int size = properties.getSize();
////        this.properties = CharacterPropertyFlyweight.getProperty(font, color, size);
//
//    }

    public void setValue(char value) {
        this.value = value;
    }

//    public void setProperties(CharacterProperty properties) {
//        this.properties = properties;
//    }

    public char getValue() {
        return value;
    }

    public CharacterProperty getProperties() {
        return properties;
    }

//    public CharacterPropertyFlyweight.CharProperty getProperties() {
//        return properties;
//    }

//    @Override
//    public String toString() {
//        return "Character [value=" + value + ", properties=" + properties.dump() + "]";
//    }

@Override
public String toString() {
    return "Character [value=" + value + ", properties=" + properties + "]";
}

}
