package uz.sardorbroo.eskizuz.exception;

public class InvalidArgumentException extends EskizException {
    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(String message, Integer code) {
        super(message, code);
    }
}
