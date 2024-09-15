package uz.sardorbroo.eskizuz;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import uz.sardorbroo.eskizuz.constants.EskizClientConstants;
import uz.sardorbroo.eskizuz.dto.authorization.LoginRequestDto;
import uz.sardorbroo.eskizuz.properties.EskizProperties;
import uz.sardorbroo.eskizuz.service.Authorizer;
import uz.sardorbroo.eskizuz.service.Sms;
import uz.sardorbroo.eskizuz.service.Template;
import uz.sardorbroo.eskizuz.service.client.AuthorizerClient;
import uz.sardorbroo.eskizuz.service.client.SmsClient;
import uz.sardorbroo.eskizuz.service.client.TemplateClient;
import uz.sardorbroo.eskizuz.service.client.retrofit.*;
import uz.sardorbroo.eskizuz.service.impl.AuthorizerImpl;
import uz.sardorbroo.eskizuz.service.impl.TemplateImpl;
import uz.sardorbroo.eskizuz.utils.RetrofitClientUtils;

public class Eskiz {

    private Authorizer authorizer;

    private Template template;

    private Sms sms;

    public Eskiz(Authorizer authorizer, Template template, Sms sms) {
        this.authorizer = authorizer;
        this.template = template;
        this.sms = sms;
    }

    public Authorizer authorizer() {
        return this.authorizer;
    }

    public Template template() {
        return this.template;
    }

    public Sms sms() {
        return this.sms;
    }

    public static class Builder {
        private static final String ESKIZ_BASE_URL = EskizClientConstants.BASE_URL;
        private final EskizProperties properties;

        private Authorizer authorizer;
        private AuthorizerClient authorizerClient;

        private Template template;
        private TemplateClient templateClient;

        private Sms sms;
        private SmsClient smsClient;

        public Builder(EskizProperties properties) {
            this.properties = properties;
            this.authorizerClient = new RetrofitAuthorizerClient(initRetrofitClient());
            this.authorizer = new AuthorizerImpl(authorizerClient);
            this.templateClient = new RetrofitTemplateClient(initTemplateRetrofitClient(properties));
            this.template = new TemplateImpl(templateClient);
            this.smsClient = new RetrofitSmsClient(null); // todo
        }

        public Eskiz build() {
            return new Eskiz(authorizer, template, sms);
        }

        public Eskiz.Builder authorizer(Authorizer authorizer) {
            this.authorizer = authorizer;
            return this;
        }

        public Eskiz.Builder template(Template template) {
            this.template = template;
            return this;
        }

        private TemplateRetrofitClient initTemplateRetrofitClient(EskizProperties properties) {

            LoginRequestDto credentials = new LoginRequestDto();
            credentials.setEmail(properties.getEmail());
            credentials.setPassword(properties.getPassword());

            Interceptor tokenInterceptor = new RetrofitTokenInterceptor(initRetrofitClient(), credentials);

            OkHttpClient okClient = new OkHttpClient.Builder()
                    .addInterceptor(tokenInterceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(okClient)
                    .baseUrl(ESKIZ_BASE_URL)
                    .build();

            return retrofit.create(TemplateRetrofitClient.class);
        }

        private SmsRetrofitClient initSmsRetrofitClient(EskizProperties properties) {

            RetrofitTokenInterceptor interceptor = RetrofitClientUtils.getTokenInterceptor(properties);
            // todo init sms retrofit client
            return null;
        }

        private RetrofitClient initRetrofitClient() {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(JacksonConverterFactory.create())
                    .baseUrl(ESKIZ_BASE_URL)
                    .build();

            return retrofit.create(RetrofitClient.class);
        }

    }
}
