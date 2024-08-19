package uz.sardorbroo.eskizuz.dto.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class LoginResponseDto {

    @JsonProperty("message")
    private String message;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("data")
    private TokenDto data;

}
