package com.cg.util;

import com.cg.model.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ProUtils {

    private static List<Product> products = new ArrayList<Product>();

    private static final int NUM_PRODUCTS = 3;

    private static final int MIN_PRODUCT_NUM = 10;

    public static List<Product> buildProducts() {
        if (products.isEmpty()) {
            IntStream.range(0, NUM_PRODUCTS).forEach(n -> {
                products.add(new Product(MIN_PRODUCT_NUM + n + 1, "Spring in Action"));
            });
        }
        return products;
    }

}