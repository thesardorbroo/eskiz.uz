package uz.sardorbroo.eskizuz.dto.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsDispatchRequestDto {

    @JsonProperty("dispatch_id")
    private Long dispatchId;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("is_ad")
    private Integer isAd;
}
