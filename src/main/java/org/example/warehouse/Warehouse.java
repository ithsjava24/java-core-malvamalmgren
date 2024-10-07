package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;

public class Warehouse {
    private static Map<String, Warehouse> instances = new HashMap<>();
    private String name;
    private List<ProductRecord> addedProducts = new ArrayList<>();
    private Map<UUID, ProductRecord> productMap = new HashMap<>();
    private List<ProductRecord> changedProducts = new ArrayList<>();

    private Warehouse(String name) {
        this.name = name;
    }

    public static Warehouse getInstance() {
        return getInstance("MyStore");
    }

    public static Warehouse getInstance(String name) {
        return instances.computeIfAbsent(name, k -> new Warehouse(name));
    }

public ProductRecord addProduct(UUID uuid, String name, Category category, BigDecimal price) {
    if (name == null || name.isBlank()) {
        throw new IllegalArgumentException("Product name can't be null or empty.");
    }
    if (category == null) {
        throw new IllegalArgumentException("Category can't be null.");
    }

    if (uuid == null) {
        uuid = UUID.randomUUID();
    }

    if (price == null) {
        price = BigDecimal.ZERO;
    }

    if (productMap.containsKey(uuid)) {
        throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
    }
    ProductRecord product = new ProductRecord(uuid, name, category, price);

    productMap.put(uuid, product);
    addedProducts.add(product);

    return product;
}

    public boolean isEmpty() {
        return addedProducts.isEmpty();
    }

    public void updateProductPrice(UUID id, BigDecimal price) {
    }

    public List<ProductRecord> getAddedProducts() {
        return addedProducts;
    }

    public List<ProductRecord> getProductsBy(Category category) {
        return addedProducts;
    }

    public Optional<ProductRecord> getProductById(UUID uuid) {
        return Optional.ofNullable(productMap.get(uuid));
    }


    public List<?> getProductsGroupedByCategories(Category category) {
        return addedProducts;
    }

    public List<?> getChangedProducts() {
        return addedProducts;
    }
}

