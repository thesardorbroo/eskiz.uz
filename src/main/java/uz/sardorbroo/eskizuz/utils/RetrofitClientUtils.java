package uz.sardorbroo.eskizuz.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import uz.sardorbroo.eskizuz.constants.EskizClientConstants;
import uz.sardorbroo.eskizuz.dto.authorization.LoginRequestDto;
import uz.sardorbroo.eskizuz.properties.EskizProperties;
import uz.sardorbroo.eskizuz.service.client.retrofit.RetrofitClient;
import uz.sardorbroo.eskizuz.service.client.retrofit.RetrofitTokenInterceptor;
import uz.sardorbroo.eskizuz.service.client.retrofit.TemplateRetrofitClient;

public class RetrofitClientUtils {

    public static <T> T getClient(Class<T> clazz, OkHttpClient ok) {

        var retrofit = new Retrofit.Builder()
                .client(ok)
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl(EskizClientConstants.BASE_URL)
                .build();

        return retrofit.create(clazz);
    }

    public static <T> T getClient(Class<T> clazz) {

        var retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl(EskizClientConstants.BASE_URL)
                .build();

        return retrofit.create(clazz);
    }

    public static RetrofitClient getTokenClient() {
        return getClient(RetrofitClient.class);
    }

    public static RetrofitTokenInterceptor getTokenInterceptor(EskizProperties properties) {

        var credentials = buildCredentials(properties);
        var tokenClient = getTokenClient();

        return new RetrofitTokenInterceptor(tokenClient, credentials);
    }

    public static TemplateRetrofitClient getTemplateClient(EskizProperties properties) {

        var interceptor = getTokenInterceptor(properties);

        var ok = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        return getClient(TemplateRetrofitClient.class, ok);
    }

    public static LoginRequestDto buildCredentials(EskizProperties properties) {

        var credentials = new LoginRequestDto();
        credentials.setEmail(properties.getEmail());
        credentials.setPassword(properties.getPassword());

        return credentials;
    }
}
