package org.example.warehouse;

import java.util.HashMap;
import java.util.Map;

public class Category {
    private final String name;
    private static final Map<String, Category> categories = new HashMap<>();

    private Category(String name) {
        this.name = capitalize(name);
    }

    public static Category of(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Category name can't be null");
        else
            return categories.computeIfAbsent(name, Category::new);
    }

    public String getName() {
        return this.name;
    }

    private String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("String cannot be null or empty.");
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
