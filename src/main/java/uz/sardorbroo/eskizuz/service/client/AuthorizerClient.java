package uz.sardorbroo.eskizuz.service.client;

import uz.sardorbroo.eskizuz.dto.UserInfoResponseDto;
import uz.sardorbroo.eskizuz.dto.authorization.LoginRequestDto;
import uz.sardorbroo.eskizuz.dto.authorization.LoginResponseDto;

import java.util.Optional;

public interface AuthorizerClient {

    Optional<LoginResponseDto> getToken(LoginRequestDto loginDto);

    Optional<LoginResponseDto> refreshToken();

    Optional<UserInfoResponseDto> getUserInfo();

}
