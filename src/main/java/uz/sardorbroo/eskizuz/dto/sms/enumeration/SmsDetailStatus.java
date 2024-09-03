package uz.sardorbroo.eskizuz.dto.sms.enumeration;

public enum SmsDetailStatus {
    ALL(""),
    DELIVERED("delivered"),
    REJECTED("rejected"),
    ;

    private final String mask;

    SmsDetailStatus(String mask) {
        this.mask = mask;
    }
}
