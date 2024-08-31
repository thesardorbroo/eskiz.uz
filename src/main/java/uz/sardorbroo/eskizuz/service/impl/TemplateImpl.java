package uz.sardorbroo.eskizuz.service.impl;

import uz.sardorbroo.eskizuz.dto.template.TemplateDto;
import uz.sardorbroo.eskizuz.service.Template;
import uz.sardorbroo.eskizuz.service.client.TemplateClient;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class TemplateImpl implements Template {

    private final TemplateClient client;

    public TemplateImpl(TemplateClient client) {
        this.client = client;
    }

    @Override
    public Optional<TemplateDto> create() {
        return Optional.empty();
    }

    @Override
    public Collection<TemplateDto> getAll() {
        return List.of();
    }
}
