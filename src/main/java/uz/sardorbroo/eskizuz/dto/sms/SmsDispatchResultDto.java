package uz.sardorbroo.eskizuz.dto.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.sardorbroo.eskizuz.dto.sms.enumeration.SmsDetailStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsDispatchResultDto {

    @JsonProperty("status")
    private SmsDetailStatus status;

    @JsonProperty("total")
    private Integer total;
}
