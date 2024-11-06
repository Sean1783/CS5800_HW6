package org.texteditor.character;

import org.texteditor.flyweight.Flyweight;

public class Character {

    private char value;
    private Flyweight.CharProps properties;

    public Character() {}

    public Character(char value, Flyweight.CharProps properties) {
        this.value = value;
        this.properties = properties;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public void setProperties(Flyweight.CharProps properties) {
        this.properties = properties;
    }

    public char getValue() {
        return value;
    }

    public Flyweight.CharProps getProperties() {
        return properties;
    }

    public String dump() {
        return "Character [value=" + value + ", properties=" + properties.dump() + "]";
    }

    @Override
    public String toString() {
        return "Character [value=" + value + ", properties=" + properties + "]";
    }

}
