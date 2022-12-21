package by.leshkevich.check.models.exceptions;


import by.leshkevich.check.models.entities.Purchase;

import java.util.List;

public class NullProductException extends Exception{
    private List<Purchase> purchaseList;

    public NullProductException() {
    }

    public NullProductException(String message) {
        super(message);
    }
    public NullProductException(String message, List<Purchase> purchaseList) {
        super(message);
        this.purchaseList=purchaseList;
    }

    public NullProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullProductException(Throwable cause) {
        super(cause);
    }

    public NullProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }
}
