package uz.sardorbroo.eskizuz.utils;

import uz.sardorbroo.eskizuz.dto.template.TemplateStatus;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TemplateUtils {

    private static final List<TemplateStatus> STATUSES = List.of(TemplateStatus.values());

    public static Optional<TemplateStatus> findByStatus(String status) {
        return STATUSES.stream()
                .filter(s -> Objects.equals(status, s.getStatus()))
                .findFirst();
    }
}
