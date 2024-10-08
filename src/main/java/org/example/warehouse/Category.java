package org.example.warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Category {
    private String name;
    private static Map<String, Category> categories = new HashMap<>();

    private Category(String name){
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static Category of(String name) {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Category name can't be null");
        else
        return categories.computeIfAbsent(name, Category::new);
    }

    public String getName() {
        return this.name;
    }
}
