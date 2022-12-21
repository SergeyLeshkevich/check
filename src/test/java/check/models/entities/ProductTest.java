package check.models.entities;

import by.leshkevich.check.models.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {
    private Product product;

    @BeforeEach
    public void creatProduct() {
        product = new Product.ProductBuilderImpl().setId(1L).setTitle("Title1").setPrice(1.0).setIsPromotional((byte) 0).build();
    }

    @Test
    void getId() {
        assertEquals(1L,product.getId());
    }

    @Test
    void setId() {
        product.setId(2L);
        assertEquals(2L,product.getId());
    }

    @Test
    void getTitle() {
        assertEquals("Title1", product.getTitle());
    }

    @Test
    void setTitle() {
        product.setTitle("Title2");
        assertEquals("Title2", product.getTitle());
    }

    @Test
    void getPrice() {
        assertEquals(1.0, product.getPrice());
    }

    @Test
    void setPrice() {
        product.setPrice(2.0);
        assertEquals(2.0, product.getPrice());
    }

    @Test
    void getIsPromotional() {
        assertEquals((byte) 0, product.getIsPromotional());
    }

    @Test
    void setIsPromotional() {
        product.setIsPromotional((byte) 1);
        assertEquals((byte) 1, product.getIsPromotional());
    }
}