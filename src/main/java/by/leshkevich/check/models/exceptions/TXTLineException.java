package by.leshkevich.check.models.exceptions;

public class TXTLineException extends Exception {
    private String errLine;

    public TXTLineException() {

    }

    public TXTLineException(String errLine, Throwable cause) {
        super(cause);
        this.errLine = errLine;
    }

    public String getErrLine() {
        return errLine;
    }
}
