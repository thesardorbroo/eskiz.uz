package uz.sardorbroo.eskizuz.dto.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsDetailsLinkDto {

    @JsonProperty("url")
    private URL url;

    @JsonProperty("label")
    private String label;

    @JsonProperty("active")
    private boolean active;
}
