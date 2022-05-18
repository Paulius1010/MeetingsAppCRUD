package com.example.meetings.domains;

public enum Category {
    CodeMonkey("codeMonkey"),
    Hub("hub"),
    Short("short"),
    TeamBuilding("teamBuilding");

    private final String value;

    Category(String category) {
        value = category;
    }

    public String getValue() {
        return value;
    }

    public static Category getCategoryFromString(String text) {
        for (Category category : Category.values()) {
            if (category.value.equalsIgnoreCase(text)) {
                return category;
            }
        }
        return null;
    }
}
