package by.leshkevich.check.models.interfases;

import by.leshkevich.check.models.entities.Product;

public interface ProductBuilder {
    Product.ProductBuilderImpl setId(Long id);
    Product.ProductBuilderImpl setTitle(final String title);

    Product.ProductBuilderImpl setPrice(double price);

    Product.ProductBuilderImpl setIsPromotional(byte isPromotional);

    Product build();
}
