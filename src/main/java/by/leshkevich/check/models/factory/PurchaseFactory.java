package by.leshkevich.check.models.factory;

import by.leshkevich.check.models.entities.Product;
import by.leshkevich.check.models.entities.Purchase;
import by.leshkevich.check.models.exceptions.NullProductException;
import by.leshkevich.check.models.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PurchaseFactory {

    public static List<Purchase> getInstance(String str, ProductRepository productRepository) throws NullProductException {
        final String regex = "((\\d+)-(\\d+))";
        List<Purchase> list = new ArrayList<>();
        Product product = null;
        Purchase purchase = null;

        Matcher matcher = Pattern.compile(regex)
                .matcher(str);


            while (matcher.find()) {
                product = productRepository.findById(Long.parseLong(matcher.group(2)))
                        .stream().findAny().orElse(null);
                if (product != null) {
                    purchase = new Purchase.PurchaseBuilderImpl()
                            .setAmount(Integer.parseInt(matcher.group(3)))
                            .setProduct(product)
                            .build();
                    list.add(purchase);
                } else {
                   throw new NullProductException("продукт с id= "+matcher.group(2)+ " не найден", list);
                }
            }


        return list;
    }
}
