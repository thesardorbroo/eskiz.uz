package uz.sardorbroo.eskizuz.service.client.builder;

import retrofit2.Retrofit;
import uz.sardorbroo.eskizuz.service.Authorizer;
import uz.sardorbroo.eskizuz.service.client.AuthorizerClient;
import uz.sardorbroo.eskizuz.service.impl.AuthorizerImpl;

import java.util.Objects;

public class AuthorizerBuilder {

    private AuthorizerClient client;

    // field for authorizer client interceptor

    public AuthorizerBuilder client(Retrofit retrofit) {
        Objects.requireNonNull(retrofit, "Invalid argument is passed! Retrofit must not be null!");
        this.client = retrofit.create(AuthorizerClient.class);
        return this;
    }

    public AuthorizerBuilder client(AuthorizerClient client) {
        Objects.requireNonNull(client, "Invalid argument is passed! AuthorizerClient must not be null!");
        this.client = client;
        return this;
    }

    public Authorizer build() {
        return new AuthorizerImpl(client);
    }

}
