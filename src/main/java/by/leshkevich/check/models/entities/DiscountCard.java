package by.leshkevich.check.models.entities;

import by.leshkevich.check.models.interfases.DiscountCardBuilder;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class DiscountCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName;
    private int number;
    private double discountPercentage;

    public DiscountCard() {
    }

    public DiscountCard(Long id, String clientName, int number, double discountPercentage) {
        this.id = id;
        this.clientName = clientName;
        this.number = number;
        this.discountPercentage = discountPercentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public static DiscountCardBuilderImpl builder(){
        return new DiscountCardBuilderImpl();
    }

    public static class DiscountCardBuilderImpl implements DiscountCardBuilder {
        private Long id;
        private String clientName;
        private int number;
        private double discountPercentage;

        @Override
        public DiscountCardBuilderImpl setId(Long id) {
            this.id=id;
            return this;
        }

        @Override
        public DiscountCardBuilderImpl setClientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        @Override
        public DiscountCardBuilderImpl setNumber(int number) {
            this.number = number;
            return this;
        }

        @Override
        public DiscountCardBuilderImpl setDiscountPercentage(double discountPercentage) {
            this.discountPercentage=discountPercentage;
            return this;
        }

        @Override
        public DiscountCard build(){
            return new DiscountCard(id,clientName,number,discountPercentage);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        return number == that.number && Double.compare(that.discountPercentage, discountPercentage) == 0 && Objects.equals(id, that.id) && Objects.equals(clientName, that.clientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientName, number, discountPercentage);
    }
}
