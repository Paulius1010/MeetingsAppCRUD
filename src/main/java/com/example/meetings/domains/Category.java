package com.example.meetings.domains;

public enum Category {
    CODEMONKEY("CodeMonkey"),
    HUB("Hub"),
    SHORT("Short"),
    TEAMBUILDING("TeamBuilding");

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
