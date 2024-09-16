package uz.sardorbroo.eskiz.service.interceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import uz.sardorbroo.eskiz.factory.EskizPropertiesFactory;
import uz.sardorbroo.eskiz.factory.RealEskizPropertiesFactory;
import uz.sardorbroo.eskizuz.dto.authorization.TokenDto;
import uz.sardorbroo.eskizuz.properties.EskizProperties;
import uz.sardorbroo.eskizuz.service.client.retrofit.RetrofitClient;
import uz.sardorbroo.eskizuz.utils.RetrofitClientUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RetrofitTokenInterceptorTest {

    private static final EskizPropertiesFactory FACTORY = new RealEskizPropertiesFactory();
    private static final EskizProperties PROPERTIES = FACTORY.getProperties();

    @Test
    public void testGetUserInfoWithoutAuthorizingManually() throws IOException {
        log.info("Get userinfo without authorizing manually");

        var interceptor = uz.sardorbroo.eskizuz.utils.RetrofitClientUtils.getTokenInterceptor(PROPERTIES);

        var ok = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        var client = RetrofitClientUtils.getClient(RetrofitClient.class, ok);

        var call = client.getUserInfo();
        var response = call.execute();

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());

        log.debug("Userinfo has fetched successfully. Userinfo: {}", response.body());
    }

    @Test
    @Timeout(value = 30, unit = TimeUnit.MINUTES)
    public void testGetUserInfoWithoutAuthorizingManuallyButExpiredToken() throws IOException, InterruptedException {
        log.debug("Get userinfo without authorizing manually, but token should be refresh");

        // for refreshing token, token should be exists, token real or fake doesn't matter
        var token = getRealToken();
        String realToken = token.getToken();

        // thread sleeps 1 minute
        Thread.sleep(60 * 1000);

        // start initializing interceptor and client
        var interceptor = RetrofitClientUtils.getTokenInterceptor(PROPERTIES);
        interceptor.setToken(token);

        var ok = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        var client = RetrofitClientUtils.getClient(RetrofitClient.class, ok);

        var call = client.getUserInfo();
        var response = call.execute();

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());

        Assertions.assertNotNull(interceptor.getToken());
        Assertions.assertNotEquals(realToken, interceptor.getToken().getToken());

        log.debug("Userinfo has fetched successfully. Userinfo: {}", response.body());
    }

    private TokenDto getRealToken() throws IOException {

        var credentials = RetrofitClientUtils.buildCredentials(PROPERTIES);
        var tokenClient = RetrofitClientUtils.getClient(RetrofitClient.class);
        var call = tokenClient.getToken(credentials);
        var response = call.execute();

        return response.body().getData();
    }
}
