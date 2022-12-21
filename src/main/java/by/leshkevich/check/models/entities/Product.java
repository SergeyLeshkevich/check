package by.leshkevich.check.models.entities;

import by.leshkevich.check.models.interfases.ProductBuilder;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private double price;
    @Column(name = "is_Promotional")
    private byte isPromotional;

    public Product() {
    }

    public Product(Long id, String title, double price, byte isPromotional) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.isPromotional = isPromotional;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte getIsPromotional() {
        return isPromotional;
    }

    public void setIsPromotional(byte action) {
        this.isPromotional = action;
    }

    public static ProductBuilderImpl builder() {
        return new ProductBuilderImpl();
    }

    public static class ProductBuilderImpl implements ProductBuilder {
        private Long id;
        private String title;
        private double price;
        private byte isPromotional;

        @Override
        public ProductBuilderImpl setId(Long id) {
            this.id=id;
            return this;
        }

        @Override
        public ProductBuilderImpl setTitle(final String title) {
            this.title = title;
            return this;
        }

        @Override
        public ProductBuilderImpl setPrice(double price) {
            this.price = price;
            return this;
        }

        @Override
        public ProductBuilderImpl setIsPromotional(byte isPromotional) {
            this.isPromotional = isPromotional;
            return this;
        }

        @Override
        public Product build() {
            return new Product(id,title, price, isPromotional);
        }
    }
}
