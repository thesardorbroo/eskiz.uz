package uz.sardorbroo.eskiz.service.client.retrofit;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import uz.sardorbroo.eskiz.factory.EskizPropertiesFactory;
import uz.sardorbroo.eskiz.factory.RealEskizPropertiesFactory;
import uz.sardorbroo.eskizuz.dto.authorization.LoginRequestDto;
import uz.sardorbroo.eskizuz.properties.EskizProperties;
import uz.sardorbroo.eskizuz.service.client.retrofit.RetrofitClient;
import uz.sardorbroo.eskizuz.utils.RetrofitClientUtils;

import java.io.IOException;

@Slf4j
public class TokenRetrofitClientTest {

    private static final String AUTHORIZATION_TOKEN_HEADER_VALUE_PREFIX = "Bearer";
    private static final String AUTHORIZATION_TOKEN_HEADER_SPLITERATOR = " ";
    private static final EskizPropertiesFactory FACTORY = new RealEskizPropertiesFactory();
    private static final EskizProperties PROPERTIES = FACTORY.getProperties();

    private RetrofitClient client;

    @BeforeEach
    public void init() {

        this.client = RetrofitClientUtils.getClient(RetrofitClient.class);

    }

    @Test
    public void testGetTokenWithInvalidDto() throws IOException {
        log.debug("Get token with invalid dto");

        var call = client.getToken(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> call.execute());

        log.debug("Get token method has thrown exception");
    }

    @Test
    public void testGetTokenWithInvalidCredentials() throws IOException {
        log.debug("Get token with invalid credentials");

        var credentials = new LoginRequestDto();
        credentials.setEmail("invalid@email.com");
        credentials.setPassword("invalid");

        var call = client.getToken(credentials);
        var response = call.execute();

        log.debug("Get token response: {}", response);

        Assertions.assertNotNull(response);
        Assertions.assertNull(response.body());
        Assertions.assertFalse(response.isSuccessful());

        log.debug("Token has not fetched successfully. Response: {}", response);
    }

    @Test
    public void testGetTokenWithValidCredentials() throws IOException {
        log.debug("Get token with valid credentials");

        var credentials = RetrofitClientUtils.buildCredentials(PROPERTIES);

        var token = getToken(credentials);
        Assertions.assertTrue(StringUtils.isNotBlank(token));

        log.debug("Token has fetched successfully. Token: {}", token);
    }

    @Test
    public void testRefreshTokenWithValidParams() throws IOException {
        log.debug("Refresh token with invalid params");

        var credentials = RetrofitClientUtils.buildCredentials(PROPERTIES);
        var token = getTokenWithBearer(credentials);

        var call = client.refreshToken(token);
        var response = call.execute();

        log.debug("Refresh token response: {}", response);

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isSuccessful());

        var refreshedToken = response.body();
        Assertions.assertNotNull(refreshedToken);
        Assertions.assertNotNull(refreshedToken.getData());
        Assertions.assertNotNull(refreshedToken.getData().getToken());

        Assertions.assertNotEquals(token, refreshedToken.getData().getToken());

        log.debug("Token has refreshed successfully. Token: {}", refreshedToken.getData().getToken());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "", "          ", "invalid.jwt.token" })
    public void testGetUserInfoWithInvalidParams(String invalidToken) throws IOException {
        log.debug("Get user info with invalid params");

        var call = client.getUserInfo(invalidToken);
        var response = call.execute();

        log.debug("Get user info response: {}", response);

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isSuccessful());
    }

    @Test
    public void testGetUserInfoWithValidParams() throws IOException {
        log.debug("Get user info with valid params");

        var credentials = RetrofitClientUtils.buildCredentials(PROPERTIES);
        var token = getTokenWithBearer(credentials);

        var call = client.getUserInfo(token);
        var response = call.execute();

        log.debug("Get user info response: {}", response);

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isSuccessful());

        System.out.println(response.body());
    }

    private String getTokenWithBearer(LoginRequestDto loginRequestDto) throws IOException {

        var token = getToken(loginRequestDto);

        return String.join(AUTHORIZATION_TOKEN_HEADER_SPLITERATOR, AUTHORIZATION_TOKEN_HEADER_VALUE_PREFIX, token);
    }

    private String getToken(LoginRequestDto credentials) throws IOException {

        var call = client.getToken(credentials);
        var response = call.execute();

        log.debug("Get token response: {}", response);

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isSuccessful());

        var token = response.body();
        Assertions.assertNotNull(token);
        Assertions.assertNotNull(token.getData());
        Assertions.assertTrue(StringUtils.isNotBlank(token.getData().getToken()));

        return token.getData().getToken();
    }
}
