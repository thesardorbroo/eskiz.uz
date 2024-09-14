package uz.sardorbroo.eskizuz.service.client.retrofit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;
import uz.sardorbroo.eskizuz.dto.authorization.LoginRequestDto;
import uz.sardorbroo.eskizuz.dto.authorization.LoginResponseDto;
import uz.sardorbroo.eskizuz.dto.authorization.TokenDto;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
public class RetrofitTokenInterceptor implements Interceptor {
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private static final String AUTHORIZATION_HEADER_VALUE_PREFIX = "Bearer ";

    // uses for getting token
    private final RetrofitClient client;

    private final LoginRequestDto credentials;

    private TokenDto token;

    @Override
    // todo add JWT storage
    // todo should refresh token if token not expired
    public Response intercept(Chain chain) throws IOException {
        log.debug("Retrofit token interceptor works");

        if (Objects.isNull(token)) {
            Call<LoginResponseDto> loginResponse = client.getToken(credentials);
            retrofit2.Response<LoginResponseDto> response = loginResponse.execute();

            if (Objects.isNull(response.body())
                            || Objects.isNull(response.body().getData())
                            || StringUtils.isBlank(response.body().getData().getToken())
            ) {
                log.warn("Token are not fetched successfully! Response: {}", response);
                throw new RuntimeException("Token are not fetched successfully");
            }

            this.token = response.body().getData();
        } else {
            // send request to refresh token
        }


        Request request = chain.request()
                .newBuilder()
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE_PREFIX + token)
                .build();

        log.debug("Access token has added successfully to current request");
        return chain.proceed(request);
    }
}
