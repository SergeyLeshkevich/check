package check.models.entities;

import by.leshkevich.check.models.entities.Product;
import by.leshkevich.check.models.entities.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PurchaseTest {
    private Purchase purchase;
    private Product product;


    @BeforeEach
    public void creatPurchaseAndProduct() {
        product = new Product.ProductBuilderImpl().setId(1L).setTitle("Title1")
                .setPrice(1.0).setIsPromotional((byte) 0).build();
        purchase = new Purchase.PurchaseBuilderImpl().setId(1L).setAmount(1).setProduct(product).build();
    }

    @Test
    void getId() {
        assertEquals(1L, purchase.getId());
    }

    @Test
    void setId() {
        purchase.setId(2L);
        assertEquals(2L, purchase.getId());
    }

    @Test
    void getAmount() {
        assertEquals(1, purchase.getAmount());
    }

    @Test
    void setAmount() {
        purchase.setAmount(2);
        assertEquals(2, purchase.getAmount());
    }

    @Test
    void getProduct() {
        assertEquals(product, purchase.getProduct());
    }

    @Test
    void setProduct() {
        Product product1 = new Product.ProductBuilderImpl().setId(1L).setTitle("Title1")
                .setPrice(1.0).setIsPromotional((byte) 0).build();
        purchase.setProduct(product1);
        assertEquals(product1, purchase.getProduct());
    }

    @Test
    void getTotalMoney() {
        assertEquals(1.0,purchase.getTotalMoney());
    }

    @Test
    void getDiscMoneyWhereProductPromotionalAmountMoreFive() {
        product.setIsPromotional((byte)1);
        purchase.setAmount(6);
        assertEquals(0.6,purchase.getDiscountMoney());
    }

    @Test
    void getDiscMoneyWhereProductNotPromotionalAmountMoreFive() {
        purchase.setAmount(6);
        assertEquals(0.0,purchase.getDiscountMoney());
    }

    @Test
    void getDiscMoneyWhereProductPromotionalAmountLessFive() {
        product.setIsPromotional((byte)1);
        assertEquals(0.0,purchase.getDiscountMoney());
    }

    @Test
    void getDiscMoneyWhereProductNotPromotionalAmountLessFive() {
        assertEquals(0.0,purchase.getDiscountMoney());
    }


}