package org.texteditor.flyweight;

import java.util.HashMap;

public class Flyweight {

    private static final HashMap<String, CharProps> properties = new HashMap<>();

    public static CharProps getProperty(String font, String color, int size) {
        String key = font + "." + color + "." + size;
        if (!properties.containsKey(key)) {
            CharProps property = new CharProps(font, color, size);
            properties.put(key, property);
        }
        return properties.get(key);
    }

    public static class CharProps {
        private String font;
        private String color;
        private int size;

        private CharProps(){}

        private CharProps(String font, String color, int size) {
            this.font = font;
            this.color = color;
            this.size = size;
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

        public String dump() {
            return "Font: " + font + " Color: " + color + " Size: " + size;
        }
    }

    public static String showAllProperties() {
        return "CharacterPropertyFlyweight [properties=" + properties + "]";
    }
}
