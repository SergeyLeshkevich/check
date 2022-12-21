package check.models.entities;

import by.leshkevich.check.models.entities.DiscountCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountCardTest {
    private DiscountCard discountCard;

    @BeforeEach
    public void creatDiscountCard() {
        discountCard = new DiscountCard.DiscountCardBuilderImpl().setId(1L).setClientName("Igor")
                .setDiscountPercentage(0.1).setNumber(0001).build();
    }

    @Test
    void getId() {
        assertEquals(1L,discountCard.getId());
    }

    @Test
    void setId() {
        discountCard.setId(2L);
        assertEquals(2L,discountCard.getId());
    }


    @Test
    void getDiscountPercentage() {
        assertEquals(0.1,discountCard.getDiscountPercentage());
    }

    @Test
    void setDiscountPercentage() {
        discountCard.setDiscountPercentage(0.2);
        assertEquals(0.2,discountCard.getDiscountPercentage());
    }

    @Test
    void getClientName() {
        assertEquals("Igor", discountCard.getClientName());
    }

    @Test
    void setClientName() {
        discountCard.setClientName("Ivan");
        assertEquals("Ivan", discountCard.getClientName());
    }

    @Test
    void getNumber() {
        assertEquals(0001, discountCard.getNumber());
    }

    @Test
    void setNumber() {
        discountCard.setNumber(0002);
        assertEquals(0002, discountCard.getNumber());
    }

}