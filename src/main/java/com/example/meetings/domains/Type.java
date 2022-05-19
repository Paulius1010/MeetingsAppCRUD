package com.example.meetings.domains;

public enum Type {
    LIVE("Live"),
    INPERSON("InPerson");


    private final String value;

    Type(String type) {
        value = type;
    }

    public String getValue() {
        return value;
    }

    public static Type getTypeFromString(String text) {
        for (Type type : Type.values()) {
            if (type.value.equalsIgnoreCase(text)) {
                return type;
            }
        }
        return null;
    }
}
