package org.texteditor.flyweight;

import org.texteditor.character.CharacterProperty;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class CharacterPropertyFlyweight {

    private static final HashMap<String, CharacterProperty> properties = new HashMap<>();
//    private static final HashMap<String, CharProperty> properties = new HashMap<>();

//    public static CharProperty getProperty(String font, String color, int size) {
    public static CharacterProperty getProperty(String font, String color, int size) {
        String key = font + "." + color + "." + size;
        if (!properties.containsKey(key)) {
            CharacterProperty property = new CharacterProperty(font, color, size);
//            CharProperty property = new CharProperty(font, color, size);
            properties.put(key, property);
        }
        return properties.get(key);
    }

//    public static class CharProperty {
//        private String font;
//        private String color;
//        private int size;
//
//        private CharProperty(){}
//
//        private CharProperty(String font, String color, int size) {
//            this.font = font;
//            this.color = color;
//            this.size = size;
//        }
//
//        public String dump() {
//            return "Font: " + font + " Color: " + color + " Size: " + size;
//        }
//    }

//    public static boolean keyExists(String font, String color, int size) {
//        String key = font + "." + color + "." + size;
//        return properties.containsKey(key);
//    }

//    public static CharacterProperty getRandomProperty() {
//        List<String> keys = new ArrayList<>(properties.keySet());
//        Random random = new Random();
//        int randomInt = random.nextInt(keys.size());
//        return properties.get(keys.get(randomInt));
//    }

    public static String showAllProperties() {
        return "CharacterPropertyFlyweight [properties=" + properties + "]";
    }
}
