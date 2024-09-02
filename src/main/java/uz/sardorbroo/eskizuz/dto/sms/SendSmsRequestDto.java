package uz.sardorbroo.eskizuz.dto.sms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendSmsRequestDto {

    @JsonProperty("mobile_phone")
    private String mobilePhone;

    @JsonProperty("message")
    private String message;

    @JsonProperty("from")
    private Long from;

    @JsonProperty("callback_url")
    private URL callbackUrl;

}
