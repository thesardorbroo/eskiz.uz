package uz.sardorbroo.eskiz.factory;

import uz.sardorbroo.eskiz.constants.EskizCredentialsConstants;
import uz.sardorbroo.eskizuz.properties.EskizProperties;

public class RealEskizPropertiesFactory implements EskizPropertiesFactory {

    private static final String ESKIZ_EMAIL = EskizCredentialsConstants.ESKIZ_LOGIN;
    private static final String ESKIZ_PASSWORD = EskizCredentialsConstants.ESKIZ_PASSWORD;

    @Override
    public EskizProperties getProperties() {
        var properties = new EskizProperties();
        properties.setEmail(ESKIZ_EMAIL);
        properties.setPassword(ESKIZ_PASSWORD);

        return properties;
    }
}
