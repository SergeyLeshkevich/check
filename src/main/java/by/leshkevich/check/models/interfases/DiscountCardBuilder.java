package by.leshkevich.check.models.interfases;

import by.leshkevich.check.models.entities.DiscountCard;

public interface DiscountCardBuilder {
    DiscountCard.DiscountCardBuilderImpl setId(Long id);
    DiscountCard.DiscountCardBuilderImpl setClientName(String clientName);

    DiscountCard.DiscountCardBuilderImpl setNumber(int number);
    DiscountCard.DiscountCardBuilderImpl setDiscountPercentage(double discountPercentage);

    DiscountCard build();
}
