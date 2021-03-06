package com.baeldung.cache2k;

import java.util.Objects;

import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductHelper {

    final Logger LOGGER = LoggerFactory.getLogger(ProductHelper.class);

    private Cache<String, Integer> cachedDiscounts;

    public ProductHelper() {
        cachedDiscounts = Cache2kBuilder.of(String.class, Integer.class)
            .name("discount")
            .eternal(true)
            .entryCapacity(100)
            .build();

        initDiscountCache("Sports", 20);
    }

    public void initDiscountCache(String productType, Integer value) {
        cachedDiscounts.put(productType, value);
    }

    public Integer getDiscount(String productType) {
        Integer discount = cachedDiscounts.get(productType);
        if (Objects.isNull(discount)) {
            LOGGER.info("Discount for {} not found.", productType);
            discount = 0;
        } else {
            LOGGER.info("Discount for {} found.", productType);
        }
        return discount;
    }

}
