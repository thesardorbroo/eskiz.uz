package uz.sardorbroo.eskiz.service.client.retrofit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uz.sardorbroo.eskiz.factory.EskizPropertiesFactory;
import uz.sardorbroo.eskiz.factory.RealEskizPropertiesFactory;
import uz.sardorbroo.eskiz.utils.RetrofitClientUtils;
import uz.sardorbroo.eskizuz.dto.authorization.LoginRequestDto;
import uz.sardorbroo.eskizuz.properties.EskizProperties;
import uz.sardorbroo.eskizuz.service.client.retrofit.RetrofitClient;

import java.io.IOException;
import java.util.ResourceBundle;

@Slf4j
public class TokenRetrofitClientTest {

    private static final EskizPropertiesFactory FACTORY = new RealEskizPropertiesFactory();
    private static final EskizProperties PROPERTIES = FACTORY.getProperties();

    private RetrofitClient client;

    @BeforeEach
    public void init() {

        this.client = RetrofitClientUtils.getTokenClient();

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
        Assertions.assertNotNull(response);
        Assertions.assertNull(response.body());
        Assertions.assertFalse(response.isSuccessful());

        log.debug("Token has not fetched successfully. Response: {}", response);
    }

    @Test
    public void testGetTokenWithValidCredentials() throws IOException {
        log.debug("Get token with valid credentials");

        var credentials = RetrofitClientUtils.buildCredentials(PROPERTIES);

        var call = client.getToken(credentials);

        var response = call.execute();
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isSuccessful());

        var token = response.body();
        Assertions.assertNotNull(token);
        Assertions.assertNotNull(token.getData());
        Assertions.assertNotNull(token.getData().getToken());

        log.debug("Token has fetched successfully. Token: {}", token.getData().getToken());
    }

    @Test
    public void testRefreshTokenWithInvalidParams() throws IOException {

        // todo should add interceptor
        client.refreshToken();

    }
}
