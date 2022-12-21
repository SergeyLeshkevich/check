package by.leshkevich.check.models.interfases;

import by.leshkevich.check.models.entities.Product;
import by.leshkevich.check.models.entities.Purchase;

public interface PurchaseBuilder {
    PurchaseBuilder setId(Long id);
    PurchaseBuilder setAmount(int amount);
    PurchaseBuilder setProduct(Product product);
    Purchase build();
}
