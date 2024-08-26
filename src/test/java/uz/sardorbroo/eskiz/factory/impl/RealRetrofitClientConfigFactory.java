package uz.sardorbroo.eskiz.factory.impl;

import retrofit2.Retrofit;
import uz.sardorbroo.eskiz.factory.RetrofitClientConfigFactory;

public class RealRetrofitClientConfigFactory implements RetrofitClientConfigFactory {
    @Override
    public Retrofit init() {

        var retrofit = new Retrofit.Builder()
                .baseUrl("")
                .build();

        return null;
    }
}
