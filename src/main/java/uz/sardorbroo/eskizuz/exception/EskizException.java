package uz.sardorbroo.eskizuz.exception;

public class EskizException extends RuntimeException {

    private Integer code;

    public EskizException(String message) {
        super(message);
    }

    public EskizException(String message, Integer code) {
        super(message);
        this.code = code;
    }

}
