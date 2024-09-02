package uz.sardorbroo.eskizuz.dto.sms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendBatchSmsRequestDto {

    @JsonProperty("from")
    private Long from;

    @JsonProperty("dispatch_id")
    private Long dispatchId;

    @JsonProperty("callback_url")
    private URL callbackUrl;

    @JsonProperty("messages")
    private List<SendBatchSmsMessageResponseDto> messages;

}
