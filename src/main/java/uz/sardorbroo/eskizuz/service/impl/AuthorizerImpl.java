package uz.sardorbroo.eskizuz.service.impl;

import uz.sardorbroo.eskizuz.dto.UserInfoResponseDto;
import uz.sardorbroo.eskizuz.dto.authorization.LoginRequestDto;
import uz.sardorbroo.eskizuz.dto.authorization.LoginResponseDto;
import uz.sardorbroo.eskizuz.dto.authorization.TokenDto;
import uz.sardorbroo.eskizuz.service.Authorizer;

import java.util.Optional;

public class AuthorizerImpl implements Authorizer {


    @Override
    public Optional<LoginResponseDto> login(LoginRequestDto dto) {
        return Optional.empty();
    }

    @Override
    public Optional<LoginResponseDto> refresh(LoginRequestDto dto) {
        return Optional.empty();
    }

    @Override
    public Optional<UserInfoResponseDto> getCurrentUserInfo() {
        return Optional.empty();
    }

    @Override
    public Optional<UserInfoResponseDto> getUserInfo(TokenDto dto) {
        return Optional.empty();
    }
}
