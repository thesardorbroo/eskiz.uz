package uz.sardorbroo.eskiz.service.client;

import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import uz.sardorbroo.eskiz.constants.EskizCredentialsConstants;
import uz.sardorbroo.eskizuz.constants.EskizClientConstants;
import uz.sardorbroo.eskizuz.dto.authorization.LoginRequestDto;
import uz.sardorbroo.eskizuz.service.client.retrofit.RetrofitAuthorizerClient;
import uz.sardorbroo.eskizuz.service.client.retrofit.RetrofitClient;

public class AuthorizerClientTest {

    @Test
    public void testGetToken() {

        var retrofit = new Retrofit.Builder()
                .baseUrl(EskizClientConstants.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        var client = retrofit.create(RetrofitClient.class);
        var retrofitAuthorizerClient = new RetrofitAuthorizerClient(client);

        var dto = new LoginRequestDto();
        dto.setEmail(EskizCredentialsConstants.ESKIZ_LOGIN);
        dto.setPassword(EskizCredentialsConstants.ESKIZ_PASSWORD);

        var responseOptional = retrofitAuthorizerClient.getToken(dto);
        System.out.println(responseOptional);
    }
}
