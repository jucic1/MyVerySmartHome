package com.example.myverysmarthome.model.devices;

import java.util.Arrays;
import java.util.List;

public enum Level {
    OFF, LIGHT, MEDIUM, INTENSE;

    public static List<String> getAllInPolish() {
        return Arrays.asList("WYŁĄCZ", "LEKKI", "ŚREDNI", "MOCNY");
    }

    public String toPolish() {
        switch (this) {
            case OFF:
                return "WYŁĄCZ";
            case LIGHT:
                return "LEKKI";
            case MEDIUM:
                return "ŚREDNI";
            case INTENSE:
                return "MOCNY";
            default:
                throw new IllegalArgumentException();
        }
    }

    public static Level toEnglish(String level) {
        switch (level) {
            case "WYŁĄCZ":
                return OFF;
            case "LEKKI":
                return LIGHT;
            case "ŚREDNI":
                return MEDIUM;
            case "MOCNY":
                return INTENSE;
            default:
                throw new IllegalArgumentException();
        }
    }
}
