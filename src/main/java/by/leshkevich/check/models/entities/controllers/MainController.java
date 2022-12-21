package by.leshkevich.check.models.entities.controllers;

import by.leshkevich.check.models.entities.Check;
import by.leshkevich.check.models.entities.Purchase;
import by.leshkevich.check.models.repository.DiscountCardRepository;
import by.leshkevich.check.models.repository.ProductRepository;
import by.leshkevich.check.models.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private static ProductRepository productRepository;
    private static DiscountCardRepository discountCardRepository;
    private static PurchaseRepository purchaseRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setDiscountCardRepository(DiscountCardRepository discountCardRepository) {
        this.discountCardRepository = discountCardRepository;
    }

    @Autowired
    public void setPurchaseRepository(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @GetMapping("/check")
    public String greeting(Model model, @RequestParam(name = "itemId") long[] itemId) {
        Check check = null;
        List<Purchase> purchases = new ArrayList<>();
        Purchase purchase = null;
        for (long id : itemId) {
            purchase = purchaseRepository.findById(id).stream().findAny().orElse(null);
            if (purchase != null) {
                purchases.add(purchase);
                purchase = null;
            }
        }
        check = new Check.CheckBuilderImpl().setAddress("Kirova 16").setCashier("Ivanov").setPurchases(purchases).build();

        model.addAttribute("check", check);
        return "index";
    }

}
