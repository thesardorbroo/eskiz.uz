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
public class LoginRequestDto {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

}
