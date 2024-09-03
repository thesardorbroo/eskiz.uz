package uz.sardorbroo.eskizuz.dto.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsDetailsRequestDto {

    @JsonProperty("start_date")
    private Instant startDate;

    @JsonProperty("to_date")
    private Instant toDate;

    @JsonProperty("page_size")
    private Integer pageSize;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("is_ad")
    private Integer isAd;

}
