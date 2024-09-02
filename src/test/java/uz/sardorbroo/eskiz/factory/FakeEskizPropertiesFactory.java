package uz.sardorbroo.eskiz.factory;

import uz.sardorbroo.eskizuz.properties.EskizProperties;

public class FakeEskizPropertiesFactory implements EskizPropertiesFactory {

    private static final String FAKE_ESKIZ_EMAIL = "fake@eskiz.com";
    private static final String FAKE_ESKIZ_PASSWORD = "password";

    @Override
    public EskizProperties getProperties() {
        var properties = new EskizProperties();
        properties.setEmail(FAKE_ESKIZ_EMAIL);
        properties.setPassword(FAKE_ESKIZ_PASSWORD);

        return properties;
    }
}
