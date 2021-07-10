package com.example.myverysmarthome.model.devices;

public enum Level {
    LIGHT, MEDIUM, INTENSE;

    public String toPolish() {
        switch (this) {
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
