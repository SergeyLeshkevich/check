package check.models.entities;

import by.leshkevich.check.models.entities.Check;
import by.leshkevich.check.models.entities.DiscountCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckBuilderImplTest {

    Check check;

    @BeforeEach
    public void creatCheck() {
        DiscountCard discountCard = new DiscountCard.DiscountCardBuilderImpl().setId(1L).setNumber(0001)
                .setClientName("Name").setDiscountPercentage(0.03).build();

        check = new Check.CheckBuilderImpl().setId(1L).setAddress("Address").setCashier("Cashier")
                .setDiscountCard(discountCard).build();
    }

    @Test
    void setId() {
        assertEquals(1L, check.getId());
    }

    @Test
    void setAddress() {
        assertEquals("Address", check.getAddress());
    }

    @Test
    void setCashier() {
        assertEquals("Cashier", check.getCashier());
    }

    @Test
    void setDiscountCard() {
        DiscountCard discountCard1 = new DiscountCard.DiscountCardBuilderImpl().setId(1L).setNumber(0001)
                .setClientName("Name").setDiscountPercentage(0.03).build();
        assertEquals(discountCard1,check.getDiscountCard());
    }

    @Test
    void build() {
    }
}