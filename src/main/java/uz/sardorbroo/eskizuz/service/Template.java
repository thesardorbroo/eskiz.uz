package uz.sardorbroo.eskizuz.service;

import uz.sardorbroo.eskizuz.dto.template.TemplateDto;

import java.util.Collection;
import java.util.Optional;

/**
 *
 * Don't use, because APIs throws 404, which is written in postman
 *
 */
@Deprecated
public interface Template {

    Optional<TemplateDto> create();

    Collection<TemplateDto> getAll();

}
