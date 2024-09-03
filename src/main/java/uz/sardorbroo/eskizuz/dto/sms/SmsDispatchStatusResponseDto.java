package uz.sardorbroo.eskizuz.dto.sms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsDispatchStatusResponseDto {

    private String status;

    private String id;

    private List<SmsDispatchResultDto> data;

}
