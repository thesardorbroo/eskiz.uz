package uz.sardorbroo.eskiz.service.interceptor;

import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import uz.sardorbroo.eskiz.factory.EskizPropertiesFactory;
import uz.sardorbroo.eskiz.factory.RealEskizPropertiesFactory;
import uz.sardorbroo.eskiz.utils.RetrofitClientUtils;
import uz.sardorbroo.eskizuz.dto.UserInfoResponseDto;
import uz.sardorbroo.eskizuz.dto.authorization.TokenDto;
import uz.sardorbroo.eskizuz.properties.EskizProperties;
import uz.sardorbroo.eskizuz.service.client.retrofit.RetrofitClient;
import uz.sardorbroo.eskizuz.service.client.retrofit.RetrofitTokenInterceptor;

import java.io.IOException;

public class RetrofitTokenInterceptorTest {

    private static final EskizPropertiesFactory FACTORY = new RealEskizPropertiesFactory();
    private static final EskizProperties PROPERTIES = FACTORY.getProperties();

    @Test
    public void testGetUserInfoWithoutAuthorizingManually() throws IOException {

        var interceptor = RetrofitClientUtils.getTokenInterceptor(PROPERTIES);

        var ok = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        var client = RetrofitClientUtils.getClient(RetrofitClient.class, ok);

        var call = client.getUserInfo();
        var response = call.execute();

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());

        System.out.println(response.body());
    }

    @Test
    public void testGetUserInfoWithoutAuthorizingManuallyButExpiredToken() throws IOException {

        // for refreshing token, token should be exists, token real or fake doesn't matter
        TokenDto fakeToken = new TokenDto();
        fakeToken.setToken("fakeToken");

        var interceptor = RetrofitClientUtils.getTokenInterceptor(PROPERTIES);
        interceptor.setToken(fakeToken);

        var ok = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        var client = RetrofitClientUtils.getClient(RetrofitClient.class, ok);

        var call = client.getUserInfo();
        var response = call.execute();

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());

        System.out.println(response.body());
    }
}
