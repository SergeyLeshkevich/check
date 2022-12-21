package by.leshkevich.check.models.entities;

import by.leshkevich.check.constants.AppConstant;
import by.leshkevich.check.models.exceptions.NullDiscountCardException;
import by.leshkevich.check.models.exceptions.NullProductException;
import by.leshkevich.check.models.factory.FileHandler;
import by.leshkevich.check.models.interfases.CheckBuilder;
import by.leshkevich.check.models.repository.DiscountCardRepository;
import by.leshkevich.check.models.repository.ProductRepository;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Check {

    private static SimpleDateFormat formatterDate = new SimpleDateFormat(AppConstant.OUTPUT_DATE_PATTERN);
    private static SimpleDateFormat formatterTime = new SimpleDateFormat(AppConstant.OUTPUT_TIME_PATTERN);

    private Long id;

    private String address;

    private Date date;

    private String cashier;

    private List<Purchase> purchases;

    private DiscountCard discountCard;

    public static void setFormatterDate(SimpleDateFormat formatterDate) {
        Check.formatterDate = formatterDate;
    }

    public static Check checkFactory(String fileName, String address, String cashier,
                                     DiscountCardRepository discountCardRepository, ProductRepository productRepository)
    throws FileNotFoundException, NullDiscountCardException {

        DiscountCard discountCard = null;
        List<Purchase> list = null;

        try {
            list = FileHandler.readFile(fileName, productRepository);
        } catch (NullProductException e) {
            e.printStackTrace();
            list = e.getPurchaseList();
        }

        discountCard = FileHandler.readFile(fileName, discountCardRepository);

        Check check = new CheckBuilderImpl()
                .setAddress(address)
                .setCashier(cashier)
                .setPurchases(list)
                .setDiscountCard(discountCard)
                .build();
        return check;
    }
    public Check() {
        this.date = new Date();
    }

    public Check(String address, String cashier, List<Purchase> purchases, DiscountCard discountCard) {
        this.address = address;
        this.date = new Date();
        this.cashier = cashier;
        this.purchases = purchases;
        this.discountCard = discountCard;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    public String getStringDate() {
        return formatterDate.format(date);
    }

    public String getStringTime() {
        return formatterTime.format(date);
    }

    public double getTotalMoney() {
        double totalMoney = 0;
        for (Purchase purchase : purchases) {
            totalMoney += purchase.getTotalMoney();
        }
        return (double) Math.round(totalMoney*100d)/100d ;
    }

    public double getTotalDiscountMoneyForPromotionalPurchase() {
        double totalDiscountMoney = 0;
        for (Purchase purchase : purchases) {
            totalDiscountMoney += purchase.getDiscountMoney();
        }
        return (double) Math.round(totalDiscountMoney*100d)/100d;
    }

    public double getDiscountWithDiscountCard() {
        double discountWithDiscountCard = 0;
        if (discountCard != null) {
            discountWithDiscountCard = getTotalMoney() * discountCard.getDiscountPercentage();
        }
        return (double) Math.round(discountWithDiscountCard*100d)/100d;
    }

    public double getTotal() {
        double total = getTotalMoney() - getTotalDiscountMoneyForPromotionalPurchase() - getDiscountWithDiscountCard();
        return (double) Math.round(total*100d)/100d;
    }


    public void headerPrint() {
        System.out.println(
                "                  CASH RECEIPT\n" +
                        "              Supermarket 'Dva gusya'\n" +
                        "           Address - " + address +
                        "\n         Telefone number: +375232235565" +
                        "\n\nCashier:" + cashier + "          DATE: " + getStringDate() +
                        "\n                                TIME: " + getStringTime() +
                        "\n________________________________________________");
    }

    public void bodyPrint() {
        System.out.println(
                "\nQTY    DISCRIPTION         Price      Total\n");

        for (Purchase purchase : purchases) {

            System.out.printf("%3d  %15s  %10.2f$  %9.2f$",
                    purchase.getAmount(),
                    purchase.getProduct().getTitle(),
                    purchase.getProduct().getPrice(),
                    purchase.getTotalMoney());
            if (purchase.getProduct().getIsPromotional() == 1)
                System.out.printf("\n    disc. prod                         %5.2f$",
                        purchase.getDiscountMoney());
            System.out.println();

        }
    }

    public void footerPrint() {
        System.out.println(
                "\n________________________________________________" +
                        "\n________________________________________________");
        System.out.printf("TAXABLE TOT.                      %9.2f\n", getTotalMoney());
        System.out.printf("DISC.                             %9.2f\n", getDiscountWithDiscountCard() +
                getTotalDiscountMoneyForPromotionalPurchase());
        System.out.printf("TOTAL                             %9.2f\n", getTotal());
    }

    public String toHeaderPrint() {
        return "                  CASH RECEIPT\n" +
                "              Supermarket 'Dva gusya'\n" +
                "           Address - " + address +
                "\n         Telefone number: +375232235565" +
                "\n\nCashier:" + cashier + "          DATE: " + getStringDate() +
                "\n                                TIME: " + getStringTime() +
                "\n________________________________________________";
    }

    public String toBodyPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nQTY    DISCRIPTION         Price      Total\n");


        for (Purchase purchase : purchases) {
            sb.append(String.format("%3d%16s%12.2f%12.2f\n",
                    purchase.getAmount(),
                    purchase.getProduct().getTitle(),
                    purchase.getProduct().getPrice(),
                    purchase.getTotalMoney()));
            if (purchase.getProduct().getIsPromotional() == 1)
                sb.append(String.format("    disc. prod                        %5.2f\n",
                        purchase.getDiscountMoney()));
            System.out.println();
        }
        return sb.toString();
    }

    public String toFooterPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append("________________________________________________");
        sb.append("\n________________________________________________\n");
        sb.append(String.format("TAXABLE TOT.                      %9.2f\n", getTotalMoney()));
        sb.append(String.format("DISC.                             %9.2f\n", getDiscountWithDiscountCard() +
                getTotalDiscountMoneyForPromotionalPurchase()));
        sb.append(String.format("TOTAL                             %9.2f\n", getTotal()));
        return sb.toString();
    }

    public void printCheck() {
        headerPrint();
        bodyPrint();
        footerPrint();
    }

    public String toStringForPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append(toHeaderPrint());
        sb.append(toBodyPrint());
        sb.append(toFooterPrint());
        return sb.toString();
    }

    public static class CheckBuilderImpl implements CheckBuilder {

        Check check = new Check();

        @Override
        public CheckBuilder setId(Long id) {
            check.setId(id);
            return this;
        }

        @Override
        public CheckBuilder setAddress(String address) {
            check.setAddress(address);
            return this;
        }

        @Override
        public CheckBuilder setCashier(String cashier) {
            check.setCashier(cashier);
            return this;
        }

        @Override
        public CheckBuilder setPurchases(List<Purchase> purchases) {
            check.setPurchases(purchases);
            return this;
        }

        @Override
        public CheckBuilder setDiscountCard(DiscountCard discountCard) {
            check.setDiscountCard(discountCard);
            return this;
        }

        @Override
        public Check build() {
            return check;
        }
    }
}