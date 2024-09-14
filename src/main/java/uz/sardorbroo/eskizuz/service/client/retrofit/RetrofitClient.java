package uz.sardorbroo.eskizuz.service.client.retrofit;

import retrofit2.Call;
import retrofit2.http.*;
import uz.sardorbroo.eskizuz.dto.UserInfoResponseDto;
import uz.sardorbroo.eskizuz.dto.authorization.LoginRequestDto;
import uz.sardorbroo.eskizuz.dto.authorization.LoginResponseDto;

public interface RetrofitClient {

    @POST("/api/auth/login")
    Call<LoginResponseDto> getToken(@Body LoginRequestDto loginDto);

    @PATCH("/api/auth/refresh")
    Call<LoginResponseDto> refreshToken(@Header("Authorization") String token);

    @GET("/api/auth/user")
    Call<UserInfoResponseDto> getUserInfo(@Header("Authorization") String token);

    @GET("/api/auth/user")
    Call<UserInfoResponseDto> getUserInfo();

}
