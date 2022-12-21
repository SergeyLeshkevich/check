package by.leshkevich.check.models.interfases;

import by.leshkevich.check.models.entities.Check;
import by.leshkevich.check.models.entities.DiscountCard;
import by.leshkevich.check.models.entities.Purchase;

import java.util.List;

public interface CheckBuilder {
    CheckBuilder setId(Long Id);
    CheckBuilder setAddress(String address);
    CheckBuilder setCashier(String cashier);
    CheckBuilder setPurchases(List<Purchase> purchases);
    CheckBuilder setDiscountCard(DiscountCard discountCard);
    Check build();
}
