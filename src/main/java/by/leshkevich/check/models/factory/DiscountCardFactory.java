package by.leshkevich.check.models.factory;



import by.leshkevich.check.models.entities.DiscountCard;
import by.leshkevich.check.models.exceptions.NullDiscountCardException;
import by.leshkevich.check.models.repository.DiscountCardRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiscountCardFactory {

    public static DiscountCard getInstance(String str, DiscountCardRepository discountCardRepository) throws NullDiscountCardException {
        final String regex = "(Card)-(\\d+)";
        DiscountCard discountCard=null;

        Matcher matcher = Pattern.compile(regex)
                .matcher(str);

            while (matcher.find()) {
                discountCard = discountCardRepository.findById(Long.parseLong(matcher.group(2)))
                        .stream().findAny().orElse(null);

                if(discountCard==null){
                    throw new NullDiscountCardException("Карта с id= "+ matcher.group(2)+ " не найдена");
                }
            }


        return discountCard;
    }
}
