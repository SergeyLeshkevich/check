package by.leshkevich.check.models.factory;

import by.leshkevich.check.models.entities.DiscountCard;
import by.leshkevich.check.models.entities.Purchase;
import by.leshkevich.check.models.exceptions.NullDiscountCardException;
import by.leshkevich.check.models.exceptions.NullProductException;
import by.leshkevich.check.models.repository.DiscountCardRepository;
import by.leshkevich.check.models.repository.ProductRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    public static List<Purchase> readFile(final String filename, ProductRepository productRepository)
            throws FileNotFoundException, NullProductException {

        List<Purchase> purchases = null;

        try (Scanner sc = new Scanner(new FileReader(filename))) {
            purchases = new ArrayList<>();

            while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    purchases = PurchaseFactory.getInstance(line, productRepository);
            }
        }

        return purchases;
    }

    public static DiscountCard readFile(final String filename, DiscountCardRepository discountCardRepository)
            throws NullDiscountCardException, FileNotFoundException {

        DiscountCard discountCard = null;

        try (Scanner sc = new Scanner(new FileReader(filename))) {

            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                discountCard = DiscountCardFactory.getInstance(line, discountCardRepository);

            }
        }

        return discountCard;
    }

    public static void writeFile(final String filename, String str){
        try(FileWriter writer = new FileWriter(filename, false))
        {
            writer.write(str);
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

}
