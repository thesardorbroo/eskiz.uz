package uz.sardorbroo.eskiz.service.client.retrofit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uz.sardorbroo.eskiz.utils.RetrofitClientUtils;
import uz.sardorbroo.eskizuz.service.client.retrofit.TemplateRetrofitClient;

public class TemplateRetrofitClientTest {

    private TemplateRetrofitClient client;

    @BeforeEach
    public void init() {
        this.client = RetrofitClientUtils.getTemplateClient();
    }

    @Test
    public void testCreateNewTemplate() {

    }
}
