package uz.sardorbroo.eskizuz.service.client.retrofit;

import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;
import uz.sardorbroo.eskizuz.dto.UserInfoResponseDto;
import uz.sardorbroo.eskizuz.dto.authorization.LoginRequestDto;
import uz.sardorbroo.eskizuz.dto.authorization.LoginResponseDto;
import uz.sardorbroo.eskizuz.service.client.AuthorizerClient;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class RetrofitAuthorizerClient implements AuthorizerClient {

    private final RetrofitClient client;

    public RetrofitAuthorizerClient(RetrofitClient client) {
        this.client = client;
    }

    @Override
    public Optional<LoginResponseDto> getToken(LoginRequestDto loginDto) {
        log.debug("Get token with retrofit client. LoginRequestDto: {}", loginDto);

        Call<LoginResponseDto> loginRequest = client.getToken(loginDto);
        try {

            Response<LoginResponseDto> response = loginRequest.execute();
            LoginResponseDto token = response.body();

            log.debug("Token has got successfully. Token: {}", token);
            return Optional.ofNullable(token);

        } catch (IOException ex) {
            log.error("Error while getting token with retrofit client! Exception: {}", ex.getMessage());
            log.debug("Get token exception:", ex);
            return Optional.empty();
        }
    }

    @Override
    public Optional<LoginResponseDto> refreshToken() {
        log.debug("Refresh token with retrofit client");

        // todo get token from JWT storage or interceptor makes automatically
        Call<LoginResponseDto> refreshTokenRequest = client.refreshToken("" /* todo set token */);
        try {

            Response<LoginResponseDto> response = refreshTokenRequest.execute();
            LoginResponseDto refreshedToken = response.body();

            log.debug("Token has refreshed successfully. Refreshed token: {}", refreshedToken);
            return Optional.ofNullable(refreshedToken);

        } catch (IOException ex) {
            log.error("Error while refreshing token! Exception: {}", ex.getMessage());
            log.error("Refresh token exception:", ex);
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserInfoResponseDto> getUserInfo() {
        log.debug("Get user info with retrofit client");

        // todo get token from JWT storage or interceptor makes automatically
        Call<UserInfoResponseDto> userInfoRequest = client.getUserInfo("" /* todo set token */);
        try {

            Response<UserInfoResponseDto> response = userInfoRequest.execute();
            UserInfoResponseDto userInfo = response.body();

            log.debug("User info has fetched successfully. User info: {}", userInfo);
            return Optional.ofNullable(userInfo);

        } catch (IOException ex) {
            log.error("Error while getting user info! Exception: {}", ex.getMessage());
            log.debug("User info exception:", ex);
            return Optional.empty();
        }
    }
}
