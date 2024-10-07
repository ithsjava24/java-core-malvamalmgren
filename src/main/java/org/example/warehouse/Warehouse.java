package org.example.warehouse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Warehouse {
    private String name;

    //    private String addedProduct;
    private List<ProductRecord> addedProducts;

    //    private Warehouse(UUID id, String name, String category, BigDecimal price) {
//        this.id = id;
//        this.name = name;
//        this.category = category;
//        this.price = price;
//    }
    private Warehouse() {
    }

    public static Warehouse createWarehouse() {
        return new Warehouse();
    }

    public static Warehouse createWarehouse(String name) {
        return new Warehouse();
    }


    public ProductRecord addProduct(UUID uuid, String name, Category category, BigDecimal price) {
        //exceptions
        if (addedProducts == null) {
            addedProducts = new ArrayList<>();
        }
        ProductRecord addedProducts = new ProductRecord(uuid, name, category, price);
        return addedProducts;
    }

//    public boolean isEmpty() {
//        return false;
//    }

    public void updateProductPrice(UUID id, BigDecimal price) {
    }

    public List<ProductRecord> getProducts() {
        return addedProducts;
    }

    public List<ProductRecord> getProductsBy(Category category) {
        return addedProducts;
    }

    public ProductRecord getProductById(UUID uuid) {
        return addedProducts.get(0);
    }


    public List<?> getProductsGroupedByCategories(Category category) {
        return addedProducts;
    }

    public List<?> getChangedProducts() {
        return addedProducts;
    }
}

