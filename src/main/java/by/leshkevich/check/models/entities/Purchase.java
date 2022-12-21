package by.leshkevich.check.models.entities;

import by.leshkevich.check.models.interfases.PurchaseBuilder;
import by.leshkevich.check.constants.AppConstant;
import jakarta.persistence.*;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;
    @OneToOne
    private Product product;

    public Purchase() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getTotalMoney() {
        return (double) Math.round(amount * product.getPrice()*100d)/100d ;
    }

    public double getDiscountMoney() {
        double discount = 0;
        if (product.getIsPromotional() == 1 && amount > AppConstant.STOCK_QUANTITY) {
            discount = getTotalMoney() * AppConstant.DISCOUNT_PERCENTAGE;
        }
        return (double) Math.round(discount*100d)/100d;
    }


    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", amount=" + amount +
                ", product=" + product +
                '}';
    }


    public static class PurchaseBuilderImpl implements PurchaseBuilder {
        Purchase purchase=new Purchase();

        @Override
        public PurchaseBuilder setId(Long id) {
            purchase.setId(id);
            return this;
        }

        @Override
        public PurchaseBuilder setAmount(int amount) {
            purchase.setAmount(amount);
            return this;
        }

        @Override
        public PurchaseBuilder setProduct(Product product) {
            purchase.setProduct(product);
            return this;
        }

        @Override
        public Purchase build() {
            return purchase;
        }
    }
}
