package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private static final Map<String, Warehouse> instances = new HashMap<>();
    private final String name;
    private final List<ProductRecord> products = new ArrayList<>();
    private final List<ProductRecord> changedProducts = new ArrayList<>();

    private Warehouse(String name) {
        this.name = name;
    }

    public static Warehouse getInstance() {
        String baseName = "myStore";
        String storeName = baseName;

        int counter = 1;

        while (instances.containsKey(storeName)) {
            storeName = baseName + counter;
            counter++;
        }

        return getInstance(storeName);
    }

    public static Warehouse getInstance(String name) {
        return instances.computeIfAbsent(name, _ -> new Warehouse(name));
    }

    public ProductRecord addProduct(UUID uuid, String name, Category category, BigDecimal price) {
        getProductById(uuid).ifPresent(_ -> {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        });

        ProductRecord product = new ProductRecord(uuid, name, category, price);
        products.add(product);

        return product;
    }

    public void updateProductPrice(UUID uuid, BigDecimal newPrice) {
        if (uuid == null) throw new IllegalArgumentException("Product id can't be null.");
        if (newPrice == null) throw new IllegalArgumentException("Product price can't be null.");
        if (newPrice.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Product price can't be negative.");

        getProductById(uuid).ifPresentOrElse(product -> {
                    ProductRecord updatedProduct = new ProductRecord(product.uuid(),
                            product.name(),
                            product.category(),
                            newPrice);

                    products.set(products.indexOf(product), updatedProduct);
                    changedProducts.add(product);
                },
                () -> {
                    throw new IllegalArgumentException("Product with that id doesn't exist.");
                });
    }

    public List<ProductRecord> getAddedProducts() {
        return List.copyOf(products);
    }

    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        return products.stream().collect(Collectors.groupingBy(ProductRecord::category));
    }

    public Optional<ProductRecord> getProductById(UUID uuid) {
        return products.stream()
                .filter(productRecord -> productRecord.uuid().equals(uuid))
                .findFirst();
    }

    public List<ProductRecord> getProductsBy(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null.");
        }

        return products.stream()
                .filter(product -> product.category().equals(category))
                .toList();
    }

    public List<ProductRecord> getChangedProducts() {
        return changedProducts;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}
