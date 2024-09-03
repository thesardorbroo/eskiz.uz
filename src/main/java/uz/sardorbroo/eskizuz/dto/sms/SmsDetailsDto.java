package uz.sardorbroo.eskizuz.dto.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsDetailsDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("country_id")
    private Long countryId;

    @JsonProperty("smsc_id")
    private Long smscId;

    @JsonProperty("dispatch_id")
    private Long dispatchId;

    @JsonProperty("user_sms_id")
    private Long userSmsId;

    @JsonProperty("request_id")
    private Long requestId;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("is_ad")
    private Boolean isAd;

    @JsonProperty("nick")
    private String nick;

    @JsonProperty("to")
    private String to;

    @JsonProperty("message")
    private String message;

    @JsonProperty("encoding")
    private Integer encoding;

    @JsonProperty("parts_count")
    private Integer partsCount;

    @JsonProperty("parts")
    private Map<String, Object> parts;

    @JsonProperty("msg_id")
    private String msgId;

    @JsonProperty("part_index")
    private Integer partIndex;

    @JsonProperty("accepted")
    private Boolean accepted;

    @JsonProperty("accept_time")
    private Instant acceptTime;

    @JsonProperty("accept_status")
    private String acceptStatus;

    @JsonProperty("dlr_state")
    private String dlrState;

    @JsonProperty("dlr_time")
    private Instant dlrTime;

    @JsonProperty("status")
    private String status;

    @JsonProperty("smsc_data")
    private Map<String, Object> smscData;

    @JsonProperty("sent_at")
    private Instant sentAt;

    @JsonProperty("submit_sm_resp_at")
    private Instant submitSmRespAt;

    @JsonProperty("delivery_sm_at")
    private Instant deliverySmAt;

    @JsonProperty("created_at")
    private Instant createdAt;

    @JsonProperty("updated_at")
    private Instant updatedAt;

}
