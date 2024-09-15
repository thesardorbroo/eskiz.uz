package uz.sardorbroo.eskizuz.service.client.retrofit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
@RequiredArgsConstructor
public class RetrofitTokenInterceptor implements Interceptor {

    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private static final String AUTHORIZATION_HEADER_VALUE_PREFIX = "Bearer";

    // used for getting token
    private final RetrofitClient client;

    private final LoginRequestDto credentials;

    @Setter
    @Getter
    private TokenDto token;

    @Override
    public Response intercept(Chain chain) throws IOException {
        log.debug("Retrofit token interceptor works");

        if (isTokenNullOrExpired()) {
            log.debug("Token has not found, authorize and get token");

            Call<LoginResponseDto> loginResponse = client.getToken(credentials);
            retrofit2.Response<LoginResponseDto> response = loginResponse.execute();

            if (!hasTokenInResponse(response)) {
                log.warn("Token are not fetched successfully while authorizing! Response: {}", response);
                throw new RuntimeException("Token are not fetched successfully");
            }

            this.setToken(response.body().getData());
            log.debug("Token has fetched successfully");
        } else {
            log.debug("Refresh expired token");

            Call<LoginResponseDto> call = client.refreshToken(buildAuthorizationHeader());
            retrofit2.Response<LoginResponseDto> response = call.execute();

            if (!hasTokenInResponse(response)) {
                log.warn("Token are not fetched successfully while refreshing token! Response: {}", response);
                throw new RuntimeException("Token are not fetched successfully");
            }

            this.setToken(response.body().getData());
            log.debug("Token has refreshed successfully");
        }

        Request request = chain.request()
                .newBuilder()
                .header(AUTHORIZATION_HEADER_NAME, buildAuthorizationHeader())
                .build();

        log.debug("Access token has added successfully to current request");
        return chain.proceed(request);
    }

    private boolean isTokenNullOrExpired() {

        if (Objects.isNull(this.token)) {
            log.debug("Token is null!");
            return true;
        }

        return false;
    }

    private boolean hasTokenInResponse(retrofit2.Response<LoginResponseDto> response) {

        if (Objects.isNull(response.body())
                || Objects.isNull(response.body().getData())
                || StringUtils.isBlank(response.body().getData().getToken())
        ) {
            log.warn("Token are not fetched successfully! Response: {}", response);
            return false;
        }

        return true;
    }

    private String buildAuthorizationHeader() {
        return String.join(" ", AUTHORIZATION_HEADER_VALUE_PREFIX, this.token.getToken());
    }
}