package uz.sardorbroo.eskizuz.service.client;

import uz.sardorbroo.eskizuz.dto.template.TemplateDto;
import uz.sardorbroo.eskizuz.dto.template.TemplateResponseDto;

import java.util.Optional;

public interface TemplateClient {

    Optional<TemplateDto> createTemplate();

    Optional<TemplateResponseDto> getAll();

}
