package uz.sardorbroo.eskizuz.dto.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsDetailsResponseDto {

    @JsonProperty("data")
    private SmsDetailsRootDto data;

    @JsonProperty("status")
    private String status;

}
