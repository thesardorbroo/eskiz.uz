package uz.sardorbroo.eskizuz.service.client;

import uz.sardorbroo.eskizuz.dto.sms.*;
import uz.sardorbroo.eskizuz.dto.sms.enumeration.SmsDetailStatus;

import java.util.Collection;
import java.util.Optional;

public interface SmsClient {

    Optional<SendSmsResponseDto> send(SendSmsRequestDto dto);

    Optional<SendBatchSmsResponseDto> sendBatch(SendBatchSmsRequestDto dto);

    Optional<Boolean> sendGlobal(SendGlobalSmsRequestDto dto);

    Optional<SmsDetailsRootDto> getDetails(SmsDetailStatus status, SmsDetailsRequestDto dto);

    Optional<SmsDispatchResponseDto> getUserMessagesByDispatch(SmsDetailStatus status, SmsDispatchRequestDto dto);

    Optional<SmsDispatchStatusResponseDto> getDispatchStatus(SmsDispatchStatusRequestDto dto);

    Collection<String> getNicks();

}
