package uz.sardorbroo.eskizuz.service.client.retrofit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;
import uz.sardorbroo.eskizuz.dto.sms.*;
import uz.sardorbroo.eskizuz.dto.sms.enumeration.SmsDetailStatus;
import uz.sardorbroo.eskizuz.service.client.SmsClient;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class RetrofitSmsClient implements SmsClient {

    private final SmsRetrofitClient client;

    @Override
    public Optional<SendSmsResponseDto> send(SendSmsRequestDto dto) {
        log.debug("Send SMS with Retrofit client. SendSmsRequestDto: {}", dto);

        if (Objects.isNull(dto)) {
            log.warn("Invalid argument is passed! SendSmsRequestDto must not be null!");
            return Optional.empty();
        }

        Call<SendSmsResponseDto> call = client.send(dto);

        try {

            Response<SendSmsResponseDto> response = call.execute();

            log.debug("Send SMS with Retrofit client. Result: {}", response.isSuccessful());
            return Optional.ofNullable(response.body());

        } catch (IOException e) {
            log.error("Error while sending SMS with Retrofit client. Exception: {}", e.getMessage());
            log.debug("Exception stack trace: ", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<SendBatchSmsResponseDto> sendBatch(SendBatchSmsRequestDto dto) {
        log.debug("Send batch SMS with Retrofit client. SendBatchSmsRequestDto: {}", dto);

        if (Objects.isNull(dto)) {
            log.warn("Invalid argument is passed! SendBatchSmsRequestDto must not be null!");
            return Optional.empty();
        }

        Call<SendBatchSmsResponseDto> call = client.sendBatch(dto);

        try {
            Response<SendBatchSmsResponseDto> response = call.execute();
            log.debug("Send batch SMS with Retrofit client. Result: {}", response.isSuccessful());
            return Optional.ofNullable(response.body());
        } catch (IOException e) {
            log.error("Error while sending batch SMS with Retrofit client. Exception: {}", e.getMessage());
            log.debug("Exception stack trace: ", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> sendGlobal(SendGlobalSmsRequestDto dto) {
        log.debug("Send global SMS with Retrofit client. SendGlobalSmsRequestDto: {}", dto);

        if (Objects.isNull(dto)) {
            log.warn("Invalid argument is passed! SendGlobalSmsRequestDto must not be null!");
            return Optional.empty();
        }

        Call<Boolean> call = client.sendGlobal(dto);

        try {
            Response<Boolean> response = call.execute();
            log.debug("Send global SMS with Retrofit client. Result: {}", response.isSuccessful());
            return Optional.ofNullable(response.body());
        } catch (IOException e) {
            log.error("Error while sending global SMS with Retrofit client. Exception: {}", e.getMessage());
            log.debug("Exception stack trace: ", e);
            return Optional.empty();
        }
    }


    @Override
    public Optional<SmsDetailsRootDto> getDetails(SmsDetailStatus status, SmsDetailsRequestDto dto) {
        log.debug("Get SMS details with Retrofit client. SmsDetailStatus: {}, SmsDetailsRequestDto: {}", status, dto);

        if (Objects.isNull(dto)) {
            log.warn("Invalid argument is passed! SmsDetailsRequestDto must not be null!");
            return Optional.empty();
        }

        Call<SmsDetailsRootDto> call = client.getDetails(status, dto);

        try {
            Response<SmsDetailsRootDto> response = call.execute();
            log.debug("Get SMS details with Retrofit client. Result: {}", response.isSuccessful());
            return Optional.ofNullable(response.body());
        } catch (IOException e) {
            log.error("Error while getting SMS details with Retrofit client. Exception: {}", e.getMessage());
            log.debug("Exception stack trace: ", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<SmsDispatchResponseDto> getUserMessagesByDispatch(SmsDetailStatus status, SmsDispatchRequestDto dto) {
        log.debug("Get user messages by dispatch with Retrofit client. SmsDetailStatus: {}, SmsDispatchRequestDto: {}", status, dto);

        if (Objects.isNull(dto)) {
            log.warn("Invalid argument is passed! SmsDispatchRequestDto must not be null!");
            return Optional.empty();
        }

        Call<SmsDispatchResponseDto> call = client.getUserMessagesByDispatch(status, dto);

        try {
            Response<SmsDispatchResponseDto> response = call.execute();
            log.debug("Get user messages by dispatch with Retrofit client. Result: {}", response.isSuccessful());
            return Optional.ofNullable(response.body());
        } catch (IOException e) {
            log.error("Error while getting user messages by dispatch with Retrofit client. Exception: {}", e.getMessage());
            log.debug("Exception stack trace: ", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<SmsDispatchStatusResponseDto> getDispatchStatus(SmsDispatchStatusRequestDto dto) {
        log.debug("Get dispatch status with Retrofit client. SmsDispatchStatusRequestDto: {}", dto);

        if (Objects.isNull(dto)) {
            log.warn("Invalid argument is passed! SmsDispatchStatusRequestDto must not be null!");
            return Optional.empty();
        }

        Call<SmsDispatchStatusResponseDto> call = client.getDispatchStatus(dto);

        try {
            Response<SmsDispatchStatusResponseDto> response = call.execute();
            log.debug("Get dispatch status with Retrofit client. Result: {}", response.isSuccessful());
            return Optional.ofNullable(response.body());
        } catch (IOException e) {
            log.error("Error while getting dispatch status with Retrofit client. Exception: {}", e.getMessage());
            log.debug("Exception stack trace: ", e);
            return Optional.empty();
        }
    }

    @Override
    public Collection<String> getNicks() {
        log.debug("Get nicks with Retrofit client.");

        Call<Collection<String>> call = client.getNicks();

        try {
            Response<Collection<String>> response = call.execute();
            log.debug("Get nicks with Retrofit client. Result: {}", response.isSuccessful());
            return response.isSuccessful() ? response.body() : List.of();
        } catch (IOException e) {
            log.error("Error while getting nicks with Retrofit client. Exception: {}", e.getMessage());
            log.debug("Exception stack trace: ", e);
            return List.of();
        }
    }

}
