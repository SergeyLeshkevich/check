package by.leshkevich.check.models.exceptions;

public class NullDiscountCardException extends Exception{

    public NullDiscountCardException() {
    }

    public NullDiscountCardException(String message) {
        super(message);
    }

    public NullDiscountCardException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullDiscountCardException(Throwable cause) {
        super(cause);
    }

    public NullDiscountCardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
