package uz.sardorbroo.eskizuz.service.impl;

import lombok.extern.slf4j.Slf4j;
import uz.sardorbroo.eskizuz.dto.template.TemplateDto;
import uz.sardorbroo.eskizuz.dto.template.TemplateResponseDto;
import uz.sardorbroo.eskizuz.service.Template;
import uz.sardorbroo.eskizuz.service.client.TemplateClient;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
public class TemplateImpl implements Template {

    private final TemplateClient client;

    public TemplateImpl(TemplateClient client) {
        this.client = client;
    }

    @Override
    public Optional<TemplateDto> create() {
        log.debug("Start create template");

        Optional<TemplateDto> templateOptional = client.createTemplate();
        if (templateOptional.isEmpty()) {
            log.warn("Template is not created!");
            return templateOptional;
        }

        log.info("Template has created successfully");
        return templateOptional;
    }

    @Override
    public Collection<TemplateDto> getAll() {
        log.debug("Get all templates");

        Optional<TemplateResponseDto> templatesOptional = client.getAll();
        if (templatesOptional.isEmpty()) {
            log.warn("Something went wrong! Templates are not fetched successfully!");
            return Collections.emptyList();
        }

        List<TemplateDto> templates = templatesOptional.get().getResult();
        log.debug("Templates are fetched successfully. Templates count: {}", templates.size());
        return templates;
    }
}
