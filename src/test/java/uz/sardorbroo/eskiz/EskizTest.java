package uz.sardorbroo.eskiz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import uz.sardorbroo.eskiz.factory.EskizPropertiesFactory;
import uz.sardorbroo.eskiz.factory.RealEskizPropertiesFactory;
import uz.sardorbroo.eskizuz.Eskiz;
import uz.sardorbroo.eskizuz.dto.authorization.LoginRequestDto;
import uz.sardorbroo.eskizuz.properties.EskizProperties;

public class EskizTest {

    // private static final EskizPropertiesFactory PROPERTIES_FACTORY = new FakeEskizPropertiesFactory();
    private static final EskizPropertiesFactory PROPERTIES_FACTORY = new RealEskizPropertiesFactory();

    private EskizProperties properties = PROPERTIES_FACTORY.getProperties();

    @Test
    @Disabled
    public void testEskiz() {

        var eskiz = new Eskiz.Builder(properties)
                .build();

        var credentials = new LoginRequestDto();
        credentials.setEmail(properties.getEmail());
        credentials.setPassword(properties.getPassword());

        var response = eskiz.authorizer().login(credentials);
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isPresent());
        Assertions.assertNotNull(response.get().getData());

        System.out.println("Token: " + response.get().getData().getToken());

        var templates = eskiz.template().create();
        Assertions.assertNotNull(templates);
        Assertions.assertTrue(templates.isPresent());

        System.out.println("Templates: " + templates);
    }

    // let's test SMS API, because it's important for this project. It's core of the project
}
