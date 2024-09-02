package uz.sardorbroo.eskizuz.service;

import uz.sardorbroo.eskizuz.dto.sms.*;

import java.util.Optional;

public interface Sms {

    Optional<SendSmsResponseDto> send(SendSmsRequestDto dto);

    Optional<SendBatchSmsResponseDto> sendBatch(SendBatchSmsRequestDto dto);

    Optional<Boolean> sendGlobal(SendGlobalSmsRequestDto dto);


}
