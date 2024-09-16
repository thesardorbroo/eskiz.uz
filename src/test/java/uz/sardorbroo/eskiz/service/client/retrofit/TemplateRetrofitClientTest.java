package uz.sardorbroo.eskiz.service.client.retrofit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uz.sardorbroo.eskiz.factory.EskizPropertiesFactory;
import uz.sardorbroo.eskiz.factory.RealEskizPropertiesFactory;
import uz.sardorbroo.eskizuz.service.client.retrofit.TemplateRetrofitClient;
import uz.sardorbroo.eskizuz.utils.RetrofitClientUtils;

public class TemplateRetrofitClientTest {

    private static final EskizPropertiesFactory FACTORY = new RealEskizPropertiesFactory();

    private TemplateRetrofitClient client;

    @BeforeEach
    public void init() {
        var ok = RetrofitClientUtils.buildOkHttpClientWithInterceptor(FACTORY.getProperties());
        this.client = RetrofitClientUtils.getClient(TemplateRetrofitClient.class, ok);
    }

    @Test
    public void testCreateNewTemplate() {

    }
}
