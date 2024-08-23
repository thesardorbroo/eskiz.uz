package uz.sardorbroo.eskizuz.service.client.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import uz.sardorbroo.eskizuz.dto.UserInfoResponseDto;
import uz.sardorbroo.eskizuz.dto.authorization.LoginRequestDto;
import uz.sardorbroo.eskizuz.dto.authorization.LoginResponseDto;

public interface RetrofitClient {

    @POST("/api/auth/login")
    Call<LoginResponseDto> getToken(@Body LoginRequestDto loginDto);

    @PATCH("/api/auth/refresh")
    Call<LoginResponseDto> refreshToken();

    @GET("/api/auth/user")
    Call<UserInfoResponseDto> getUserInfo();

}
