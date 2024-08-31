package uz.sardorbroo.eskizuz.dto.template;

import lombok.Getter;

@Getter
public enum TemplateStatus {
    MODERATION("moderation"),
    IN_PROCESS("inproccess"),
    SERVICE("service"),
    REKLAMA("reklama"),
    REJECTED("rejected"),
    ;

    private final String status;

    TemplateStatus(String status) {
        this.status = status;
    }
}
