package uz.sardorbroo.eskizuz.service.client.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import uz.sardorbroo.eskizuz.dto.template.TemplateDto;
import uz.sardorbroo.eskizuz.dto.template.TemplateResponseDto;

public interface TemplateRetrofitClient {

    @POST("/api/users/template")
    Call<TemplateDto> createTemplate();

    @GET("/api/template/")
    Call<TemplateResponseDto> getAllTemplates();

}
