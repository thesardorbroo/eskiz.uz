package uz.sardorbroo.eskiz.service.client;

import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import uz.sardorbroo.eskiz.constants.EskizCredentialsConstants;
import uz.sardorbroo.eskizuz.constants.EskizClientConstants;
import uz.sardorbroo.eskizuz.dto.authorization.LoginRequestDto;
import uz.sardorbroo.eskizuz.service.client.retrofit.RetrofitAuthorizerClient;
import uz.sardorbroo.eskizuz.service.client.retrofit.RetrofitClient;
import uz.sardorbroo.eskizuz.service.client.retrofit.RetrofitTokenInterceptor;

public class AuthorizerClientTest {

    @Test
    public void testGetToken() {

        var credentials = new LoginRequestDto();
        credentials.setEmail(EskizCredentialsConstants.ESKIZ_LOGIN);
        credentials.setPassword(EskizCredentialsConstants.ESKIZ_PASSWORD);

        var retrofit = new Retrofit.Builder()
                .baseUrl(EskizClientConstants.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        var client = retrofit.create(RetrofitClient.class);

        var tokenInterceptor = new RetrofitTokenInterceptor(client, credentials);

        var okClient = new OkHttpClient.Builder()
                .addInterceptor(tokenInterceptor)
                .build();

        var retrofitAuthorizerClient = new RetrofitAuthorizerClient(client);
    }
}
