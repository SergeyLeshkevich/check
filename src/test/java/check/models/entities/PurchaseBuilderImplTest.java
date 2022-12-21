package check.models.entities;

import by.leshkevich.check.models.entities.Product;
import by.leshkevich.check.models.entities.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PurchaseBuilderImplTest {
    private Purchase purchase;
    private Product product;


    @BeforeEach
    public void creatPurchaseAndProduct() {
        product = new Product.ProductBuilderImpl().setId(1L).setTitle("Title1")
                .setPrice(1.0).setIsPromotional((byte) 0).build();
        purchase = new Purchase.PurchaseBuilderImpl().setId(1L).setAmount(1).setProduct(product).build();
    }

    @Test
    void setId() {
        assertEquals(1L, purchase.getId());
    }

    @Test
    void setAmount() {
        assertEquals(1, purchase.getAmount());
    }

    @Test
    void setProduct() {
        Product product1 = new Product.ProductBuilderImpl().setId(1L).setTitle("Title1")
                .setPrice(1.0).setIsPromotional((byte) 0).build();
        purchase.setProduct(product1);
        assertEquals(product1, purchase.getProduct());
    }

}