package uz.sardorbroo.eskizuz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uz.sardorbroo.eskizuz.dto.sms.*;
import uz.sardorbroo.eskizuz.dto.sms.enumeration.SmsDetailStatus;
import uz.sardorbroo.eskizuz.service.Sms;
import uz.sardorbroo.eskizuz.service.client.SmsClient;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class SmsImpl implements Sms {

    private final SmsClient client;

    @Override
    public Optional<SendSmsResponseDto> send(SendSmsRequestDto dto) {
        log.debug("Send SMS. SendSmsRequestDto: {}", dto);

        if (Objects.isNull(dto)) {
            log.warn("Invalid argument is passed! SendSmsRequestDto must not be null!");
            return Optional.empty();
        }

        return client.send(dto);
    }

    @Override
    public Optional<SendBatchSmsResponseDto> sendBatch(SendBatchSmsRequestDto dto) {
        log.debug("Send batch SMS. SendBatchSmsRequestDto: {}", dto);

        if (Objects.isNull(dto)) {
            log.warn("Invalid argument is passed! SendBatchSmsRequestDto must not be null!");
            return Optional.empty();
        }

        return client.sendBatch(dto);
    }

    @Override
    public Optional<Boolean> sendGlobal(SendGlobalSmsRequestDto dto) {
        log.debug("Send global SMS. SendGlobalSmsRequestDto: {}", dto);

        if (Objects.isNull(dto)) {
            log.warn("Invalid argument is passed! SendGlobalSmsRequestDto must not be null!");
            return Optional.empty();
        }

        return client.sendGlobal(dto);
    }

    @Override
    public Optional<SmsDetailsRootDto> getDetails(SmsDetailStatus status, SmsDetailsRequestDto dto) {
        log.debug("Get SMS details. SmsDetailStatus: {}, SmsDetailsRequestDto: {}", status, dto);

        if (Objects.isNull(dto)) {
            log.warn("Invalid argument is passed! SmsDetailsRequestDto must not be null!");
            return Optional.empty();
        }

        return client.getDetails(status, dto);
    }

    @Override
    public Optional<SmsDispatchResponseDto> getUserMessagesByDispatch(SmsDetailStatus status, SmsDispatchRequestDto dto) {
        log.debug("Get user messages by dispatch. SmsDetailStatus: {}, SmsDispatchRequestDto: {}", status, dto);

        if (Objects.isNull(dto)) {
            log.warn("Invalid argument is passed! SmsDispatchRequestDto must not be null!");
            return Optional.empty();
        }

        return client.getUserMessagesByDispatch(status, dto);
    }

    @Override
    public Optional<SmsDispatchStatusResponseDto> getDispatchStatus(SmsDispatchStatusRequestDto dto) {
        log.debug("Get dispatch status. SmsDispatchStatusRequestDto: {}", dto);

        if (Objects.isNull(dto)) {
            log.warn("Invalid argument is passed! SmsDispatchStatusRequestDto must not be null!");
            return Optional.empty();
        }

        return client.getDispatchStatus(dto);
    }

    @Override
    public Collection<String> getNicks() {
        log.debug("Get nicks.");

        Collection<String> nicks = client.getNicks();

        log.debug("Nicks are fetched successfully. Nicks count: {}", nicks.size());
        return nicks;
    }
}
