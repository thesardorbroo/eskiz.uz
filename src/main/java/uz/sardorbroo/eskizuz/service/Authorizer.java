package uz.sardorbroo.eskizuz.service;

import uz.sardorbroo.eskizuz.dto.authorization.LoginRequestDto;
import uz.sardorbroo.eskizuz.dto.authorization.LoginResponseDto;
import uz.sardorbroo.eskizuz.dto.authorization.TokenDto;
import uz.sardorbroo.eskizuz.dto.UserInfoResponseDto;

import java.util.Optional;

public interface Authorizer {

    Optional<LoginResponseDto> login(LoginRequestDto dto);

    Optional<LoginResponseDto> refresh(LoginRequestDto dto);

    Optional<UserInfoResponseDto> getCurrentUserInfo();

    Optional<UserInfoResponseDto> getUserInfo(TokenDto dto);

}
