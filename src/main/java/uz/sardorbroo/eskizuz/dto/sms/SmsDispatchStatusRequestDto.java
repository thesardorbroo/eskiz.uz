package uz.sardorbroo.eskizuz.dto.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsDispatchStatusRequestDto {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("dispatch_id")
    private Long dispatchId;
}
