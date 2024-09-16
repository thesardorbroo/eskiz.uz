package uz.sardorbroo.eskizuz.dto.sms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class SendBatchSmsMessageRequestDto {

    @JsonProperty("user_sms_id")
    private String id;

    @JsonProperty("to")
    private String to;

    @JsonProperty("text")
    private String text;

}
