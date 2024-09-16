package uz.sardorbroo.eskizuz;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import uz.sardorbroo.eskizuz.constants.EskizClientConstants;
import uz.sardorbroo.eskizuz.properties.EskizProperties;
import uz.sardorbroo.eskizuz.service.Authorizer;
import uz.sardorbroo.eskizuz.service.Sms;
import uz.sardorbroo.eskizuz.service.Template;
import uz.sardorbroo.eskizuz.service.client.AuthorizerClient;
import uz.sardorbroo.eskizuz.service.client.SmsClient;
import uz.sardorbroo.eskizuz.service.client.TemplateClient;
import uz.sardorbroo.eskizuz.service.client.retrofit.*;
import uz.sardorbroo.eskizuz.service.impl.AuthorizerImpl;
import uz.sardorbroo.eskizuz.service.impl.SmsImpl;
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

        private RetrofitTokenInterceptor interceptor;

        private Authorizer authorizer;
        private AuthorizerClient authorizerClient;

        private Template template;
        private TemplateClient templateClient;

        private Sms sms;
        private SmsClient smsClient;

        public Builder(EskizProperties properties) {
            this.properties = properties;
            this.interceptor = initRetrofitTokenInterceptor();

            this.authorizerClient = initAuthorizerClient();
            this.authorizer = new AuthorizerImpl(authorizerClient);

            this.templateClient = initTemplateClient();
            this.template = new TemplateImpl(templateClient);

            this.smsClient = initSmsClient();
            this.sms = new SmsImpl(smsClient);
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

        private RetrofitTokenInterceptor initRetrofitTokenInterceptor() {
            return RetrofitClientUtils.getTokenInterceptor(properties);
        }

        private AuthorizerClient initAuthorizerClient() {
            return new RetrofitAuthorizerClient(initRetrofitClient());
        }

        private TemplateClient initTemplateClient() {
            return new RetrofitTemplateClient(initTemplateRetrofitClient());
        }

        private TemplateRetrofitClient initTemplateRetrofitClient() {
            OkHttpClient ok = getBaseOkHttpClient();
            return RetrofitClientUtils.getClient(TemplateRetrofitClient.class, ok);
        }

        private SmsRetrofitClient initSmsRetrofitClient() {
            OkHttpClient ok = getBaseOkHttpClient();
            return RetrofitClientUtils.getClient(SmsRetrofitClient.class, ok);
        }

        private SmsClient initSmsClient() {
            return new RetrofitSmsClient(initSmsRetrofitClient());
        }

        private OkHttpClient getBaseOkHttpClient() {
            return new OkHttpClient.Builder()
                    .addInterceptor(this.interceptor)
                    .build();
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
