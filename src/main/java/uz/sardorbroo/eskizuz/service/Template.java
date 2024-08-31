package uz.sardorbroo.eskizuz.service;

import uz.sardorbroo.eskizuz.dto.template.TemplateDto;

import java.util.Collection;
import java.util.Optional;

public interface Template {

    Optional<TemplateDto> create();

    Collection<TemplateDto> getAll();

}
