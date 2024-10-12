package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private static Map<String, Warehouse> instances = new HashMap<>();
    private String name;
    private List<ProductRecord> addedProducts = new ArrayList<>();
    private List<ProductRecord> changedProducts = new ArrayList<>();

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
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Product name can't be null or empty.");
        if (category == null)
            throw new IllegalArgumentException("Category can't be null.");

        if (uuid == null)
            uuid = UUID.randomUUID();
        if (price == null)
            price = BigDecimal.ZERO;


        for (ProductRecord pr : addedProducts) {
            if (pr.uuid().equals(uuid))
                throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }

        ProductRecord product = new ProductRecord(uuid, name, category, price);
        addedProducts.add(product);
        return product;
    }

    public boolean isEmpty() {
        return addedProducts.isEmpty();
    }

    public ProductRecord updateProductPrice(UUID id, BigDecimal newPrice) {
        if (id == null) throw new IllegalArgumentException("Product id can't be null.");
        if (newPrice == null) throw new IllegalArgumentException("Product price can't be null.");
        if (newPrice.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Product price can't be negative.");

        ProductRecord product = addedProducts.stream().filter(productRecord -> productRecord.uuid().equals(id)).findFirst().orElse(null);
        if (product == null) throw new IllegalArgumentException("Product with that id doesn't exist.");

        if (!product.price().equals(newPrice)) {
            ProductRecord updatedProduct = new ProductRecord(product.uuid(), product.name(), product.category(), newPrice);

//            productMap.put(id, updatedProduct);

            for (int i = 0; i < addedProducts.size(); i++) {
                if (addedProducts.get(i).uuid().equals(id)) {
                    addedProducts.set(i, updatedProduct);
                    break;
                }
            }

            changedProducts.add(product);

            return updatedProduct;
        }
        return product;
    }

    public List<ProductRecord> getAddedProducts() {
        return List.copyOf(addedProducts);
    }

    public Optional<ProductRecord> getProductById(UUID uuid) {
        return Optional.ofNullable(addedProducts.stream().filter(productRecord -> productRecord.uuid().equals(uuid)).findFirst().orElse(null));
    }

    public HashMap<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        HashMap<Category, List<ProductRecord>> productsByCategory = new HashMap<>();
        List<Category> categories = new ArrayList<>();

        for (int i = 0; i < addedProducts.size(); i++) {
            ProductRecord product = addedProducts.get(i);
            Category category = product.category();
            if (categories.contains(category)) continue;
            productsByCategory.put(category, addedProducts.stream()
                            .filter(productRecord -> productRecord.category().equals(category)).toList());
            categories.add(category);
        }
        return productsByCategory;
    }

    public List<ProductRecord> getChangedProducts() {
        return changedProducts;
    }

    public List<ProductRecord> getProductsBy(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null.");
        }

        return addedProducts.stream()
                .filter(product -> product.category().equals(category))
                .toList();
    }
}
