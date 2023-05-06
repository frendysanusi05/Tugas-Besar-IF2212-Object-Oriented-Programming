package exceptions;

import java.util.InputMismatchException;

public class WrongInputsException extends InputMismatchException {
    public WrongInputsException(String errorMessage) {
        super(errorMessage);
    }
}
