package uz.sardorbroo.eskizuz.service.impl;

import lombok.extern.slf4j.Slf4j;
import uz.sardorbroo.eskizuz.dto.UserInfoResponseDto;
import uz.sardorbroo.eskizuz.dto.authorization.LoginRequestDto;
import uz.sardorbroo.eskizuz.dto.authorization.LoginResponseDto;
import uz.sardorbroo.eskizuz.dto.authorization.TokenDto;
import uz.sardorbroo.eskizuz.exception.InvalidArgumentException;
import uz.sardorbroo.eskizuz.service.Authorizer;
import uz.sardorbroo.eskizuz.service.client.AuthorizerClient;

import java.util.Objects;
import java.util.Optional;

@Slf4j
public class AuthorizerImpl implements Authorizer {

    private final AuthorizerClient client;

    // add interceptor.
    // initialize this authorizer through builder.
    // builder will have default client and interceptor.

    public AuthorizerImpl(AuthorizerClient client) {
        this.client = client;
    }

    @Override
    public Optional<LoginResponseDto> login(LoginRequestDto dto) {
        log.debug("Authorizing user by login and password. LoginRequestDto: {}", dto);

        if (Objects.isNull(dto)) {
            log.warn("Invalid argument is passed! LoginRequestDto must not be null!");
            throw new InvalidArgumentException("Invalid argument is passed! LoginRequestDto must not be null!");
        }

        Optional<LoginResponseDto> loginResponseOptional = client.getToken(dto);
        log.debug("Has token fetch successfully? Result: {}", loginResponseOptional.isPresent());
        return loginResponseOptional;
    }

    @Override
    public Optional<LoginResponseDto> refresh() {
        log.debug("Refresh user token");

        Optional<LoginResponseDto> refreshTokenOptional = client.refreshToken();
        log.debug("Has token refreshed successfully? Result: {}", refreshTokenOptional.isPresent());
        return refreshTokenOptional;
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
