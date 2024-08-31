package uz.sardorbroo.eskizuz.service.client.retrofit;

import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;
import uz.sardorbroo.eskizuz.dto.template.TemplateDto;
import uz.sardorbroo.eskizuz.dto.template.TemplateResponseDto;
import uz.sardorbroo.eskizuz.service.client.TemplateClient;

import java.util.Optional;

@Slf4j
public class RetrofitTemplateClient implements TemplateClient {

    private final TemplateRetrofitClient client;

    public RetrofitTemplateClient(TemplateRetrofitClient client) {
        this.client = client;
    }

    @Override
    public Optional<TemplateDto> createTemplate() {
        log.info("Create template via retrofit client");

        Call<TemplateDto> call = client.createTemplate();

        try {

            Response<TemplateDto> response = call.execute();
            TemplateDto template = response.body();

            log.info("Template has created successfully. TemplateDto: {}", template);
            return Optional.ofNullable(template);

        } catch (Exception e) {
            log.warn("Error while creating template. Exception: {}", e.getMessage());
            log.debug("Exception stack trace: ", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<TemplateResponseDto> getAll() {
        log.info("Get all templates via retrofit client");

        Call<TemplateResponseDto> call = client.getAllTemplates();

        try {

            Response<TemplateResponseDto> response = call.execute();
            TemplateResponseDto templates = response.body();

            log.info("Templates are fetched successfully. Templates: {}", templates);
            return Optional.ofNullable(templates);

        } catch (Exception e) {
            log.error("Error while fetching all templates. Exception: {}", e.getMessage());
            log.debug("Exception stack trace: ", e);
            return Optional.empty();
        }
    }
}








