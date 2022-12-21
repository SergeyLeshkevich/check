package by.leshkevich.check;


import by.leshkevich.check.models.exceptions.NullDiscountCardException;
import by.leshkevich.check.models.factory.FileHandler;
import by.leshkevich.check.constants.AppConstant;
import by.leshkevich.check.models.entities.Check;
import by.leshkevich.check.models.repository.DiscountCardRepository;
import by.leshkevich.check.models.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class CheckRunner {

    private static ProductRepository productRepository;
    private static DiscountCardRepository discountCardRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setDiscountCardRepository(DiscountCardRepository discountCardRepository) {
        this.discountCardRepository = discountCardRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CheckRunner.class, args);

        Check check = null;
        try {
//            String fileAddress=String.valueOf(Thread.currentThread().getContextClassLoader()
//                            .getResource(AppConstant.FILE_NAME_DATA).toURI());
//            System.out.println(fileAddress);

            check = Check.checkFactory(AppConstant.FILE_NAME_DATA,"Kirova 16", "Ivanov",
                    discountCardRepository, productRepository);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NullDiscountCardException e) {
            e.printStackTrace();
        }


        FileHandler.writeFile(AppConstant.FILE_NAME_CHECK, check.toStringForPrint());
        check.printCheck();

    }
}


