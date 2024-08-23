package uz.sardorbroo.eskizuz.service.client.not.implemented;

import uz.sardorbroo.eskizuz.dto.UserInfoResponseDto;
import uz.sardorbroo.eskizuz.dto.authorization.LoginRequestDto;
import uz.sardorbroo.eskizuz.dto.authorization.LoginResponseDto;
import uz.sardorbroo.eskizuz.service.client.AuthorizerClient;

import java.util.Optional;

public class NotImplementedClient implements AuthorizerClient {

    @Override
    public Optional<LoginResponseDto> getToken(LoginRequestDto loginDto) {
        throw new RuntimeException("Authorizer client not implemented");
    }

    @Override
    public Optional<LoginResponseDto> refreshToken() {
        throw new RuntimeException("Authorizer client not implemented");
    }

    @Override
    public Optional<UserInfoResponseDto> getUserInfo() {
        throw new RuntimeException("Authorizer client not implemented");
    }
}
