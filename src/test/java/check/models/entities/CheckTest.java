package check.models.entities;

import by.leshkevich.check.models.entities.Check;
import by.leshkevich.check.models.entities.DiscountCard;
import by.leshkevich.check.models.entities.Product;
import by.leshkevich.check.models.entities.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckTest {

    Check check;

    @BeforeEach
    public void creatCheck() {
        DiscountCard discountCard = new DiscountCard.DiscountCardBuilderImpl().setId(1L).setNumber(0001)
                .setClientName("Name").setDiscountPercentage(0.03).build();
        Product product = new Product.ProductBuilderImpl().setId(1L).setTitle("Title1")
                .setPrice(1.0).setIsPromotional((byte) 0).build();
        Product product1 = new Product.ProductBuilderImpl().setId(2L).setTitle("Title1")
                .setPrice(2.0).setIsPromotional((byte) 1).build();
        Purchase purchase = new Purchase.PurchaseBuilderImpl().setId(1L).setAmount(1).setProduct(product).build();
        Purchase purchase1 = new Purchase.PurchaseBuilderImpl().setId(2L).setAmount(6).setProduct(product1).build();

        List<Purchase> list = new ArrayList<>();
        list.add(purchase);
        list.add(purchase1);

        check = new Check.CheckBuilderImpl().setId(1L).setAddress("Address").setCashier("Cashier")
                .setDiscountCard(discountCard).setPurchases(list).build();
    }

    @Test
    void getId() {
        assertEquals(1L, check.getId());
    }

    @Test
    void setId() {
        check.setId(2L);
        assertEquals(2L, check.getId());
    }

    @Test
    void getAddress() {
        assertEquals("Address", check.getAddress());
    }

    @Test
    void setAddress() {
        check.setAddress("Address2");
        assertEquals("Address2", check.getAddress());
    }

    @Test
    void getDate() {
        assertEquals(new Date().toString(), check.getDate().toString());
    }

    @Test
    void getCashier() {
        assertEquals("Cashier", check.getCashier());
    }

    @Test
    void setCashier() {
        check.setCashier("Cashier2");
        assertEquals("Cashier2", check.getCashier());
    }

    @Test
    void getDiscountCard() {
        DiscountCard discountCard1 = new DiscountCard.DiscountCardBuilderImpl().setId(1L).setNumber(0001)
                .setClientName("Name").setDiscountPercentage(0.03).build();
        assertEquals(discountCard1, check.getDiscountCard());
    }

    @Test
    void setDiscountCard() {
        DiscountCard discountCard = new DiscountCard.DiscountCardBuilderImpl().setId(2L).setNumber(0001)
                .setClientName("Name2").setDiscountPercentage(0.05).build();
        check.setDiscountCard(discountCard);
        assertEquals(discountCard, check.getDiscountCard());
    }


    @Test
    void getTotalMoney() {
        assertEquals(11.41, check.getTotal());
    }

    @Test
    void getTotalDiscountMoneyForPromotionalPurchase() {
        assertEquals(1.2, check.getTotalDiscountMoneyForPromotionalPurchase());
    }

    @Test
    void getDiscountWithDiscountCardWhereCardIsNull() {
        check.setDiscountCard(null);
        assertEquals(0, check.getDiscountWithDiscountCard());
    }

    @Test
    void getDiscountWithDiscountCardWhereCardIsNotNull() {
        assertEquals(0.39, check.getDiscountWithDiscountCard());
    }

    @Test
    void getTotal() {
        assertEquals(11.41, check.getTotal());
    }

    @Test
    void toHeaderPrint() {
        String print = "                  CASH RECEIPT\n" +
                "              Supermarket 'Dva gusya'\n" +
                "           Address - Address\n" +
                "         Telefone number: +375232235565\n" +
                "\n" +
                "Cashier:Cashier          DATE: 20.12.2022\n" +
                "                                TIME: " + check.getStringTime() + "\n" +
                "________________________________________________";
        assertEquals(print, check.toHeaderPrint());
    }

    @Test
    void toBodyPrint() {
        String print = "\nQTY    DISCRIPTION         Price      Total\n" +
                "  1          Title1        1,00        1,00\n" +
                "  6          Title1        2,00       12,00\n" +
                "    disc. prod                         1,20\n";
        assertEquals(print, check.toBodyPrint());
    }

    @Test
    void toFooterPrint() {
        String print = "________________________________________________\n" +
                "________________________________________________\n" +
                "TAXABLE TOT.                          13,00\n" +
                "DISC.                                  1,59\n" +
                "TOTAL                                 11,41\n";
        assertEquals(print, check.toFooterPrint());
    }

//    @Test
//    void headerPrint() {
//        String consoleOutput;
//        PrintStream originalOut = System.out;
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(100);
//        PrintStream capture = new PrintStream(outputStream);
//        System.setOut(capture);
//        check.headerPrint();
//        capture.flush();
//        consoleOutput = outputStream.toString();
//        System.setOut(originalOut);
//        String print = "                  CASH RECEIPT\n" +
//                "              Supermarket 'Dva gusya'\n" +
//                "           Address - Address\n" +
//                "         Telefone number: +375232235565\n" +
//                "\n" +
//                "Cashier:Cashier          DATE: 20.12.2022\n" +
//                "                                TIME: " + check.getStringTime() +
//                "\n________________________________________________\n";
//        assertEquals(print, consoleOutput);
//    }
//
//    @Test
//    void bodyPrint() {
//        String consoleOutput;
//        PrintStream originalOut = System.out;
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(100);
//        PrintStream capture = new PrintStream(outputStream);
//        System.setOut(capture);
//        check.bodyPrint();
//        capture.flush();
//        consoleOutput = outputStream.toString();
//        System.setOut(originalOut);
//        String print = "\nQTY    DISCRIPTION         Price      Total\n" +
//                "\n" +
//                "  1           Title1        1,00$       1,00$\n" +
//                "  6           Title1        2,00$      12,00$\n" +
//                "    disc. prod                          1,20$\n";
//        assertEquals(print, consoleOutput);
//    }
//
//    @Test
//    void footerPrint() {
//        String consoleOutput;
//        PrintStream originalOut = System.out;
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(100);
//        PrintStream capture = new PrintStream(outputStream);
//        System.setOut(capture);
//        check.footerPrint();
//        capture.flush();
//        consoleOutput = outputStream.toString();
//        System.setOut(originalOut);
//        String print = "\n________________________________________________\n" +
//                "________________________________________________\n" +
//                "TAXABLE TOT.                          13,00\n" +
//                "DISC.                                  1,59\n" +
//                "TOTAL                                 11,41\n";
//        assertEquals(print, consoleOutput);
//    }
//
//    @Test
//    void printCheck() {
//        String consoleOutput;
//        PrintStream originalOut = System.out;
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(100);
//        PrintStream capture = new PrintStream(outputStream);
//        System.setOut(capture);
//        check.printCheck();
//        capture.flush();
//        consoleOutput = outputStream.toString();
//        System.setOut(originalOut);
//        String print = "                  CASH RECEIPT\n" +
//                "              Supermarket 'Dva gusya'\n" +
//                "           Address - Address\n" +
//                "         Telefone number: +375232235565\n" +
//                "\n" +
//                "Cashier:Cashier          DATE: 20.12.2022\n" +
//                "                                TIME: " + check.getStringTime() +
//                "\n________________________________________________\n" +
//                "\n" +
//                "QTY    DISCRIPTION         Price      Total\n" +
//                "\n" +
//                "  1           Title1        1,00$       1,00$\n" +
//                "  6           Title1        2,00$      12,00$\n" +
//                "    disc. prod                          1,20$\n" +
//                "\n" +
//                "________________________________________________\n" +
//                "________________________________________________\n" +
//                "TAXABLE TOT.                          13,00\n" +
//                "DISC.                                  1,59\n" +
//                "TOTAL                                 11,41\n";
//        assertEquals(print, consoleOutput);
//
//    }

    @Test
    void toStringForPrint() {
        String print = "                  CASH RECEIPT\n" +
                "              Supermarket 'Dva gusya'\n" +
                "           Address - Address\n" +
                "         Telefone number: +375232235565\n" +
                "\n" +
                "Cashier:Cashier          DATE: 20.12.2022\n" +
                "                                TIME: " + check.getStringTime() + "\n" +
                "________________________________________________"
                + "\nQTY    DISCRIPTION         Price      Total\n" +
                "  1          Title1        1,00        1,00\n" +
                "  6          Title1        2,00       12,00\n" +
                "    disc. prod                         1,20\n" +
                "________________________________________________\n" +
                "________________________________________________\n" +
                "TAXABLE TOT.                          13,00\n" +
                "DISC.                                  1,59\n" +
                "TOTAL                                 11,41\n";
        assertEquals(print, check.toStringForPrint());
    }
}