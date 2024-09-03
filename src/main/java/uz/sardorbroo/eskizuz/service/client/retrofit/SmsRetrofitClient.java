package uz.sardorbroo.eskizuz.service.client.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import uz.sardorbroo.eskizuz.dto.sms.*;
import uz.sardorbroo.eskizuz.dto.sms.enumeration.SmsDetailStatus;

import java.util.Collection;

public interface SmsRetrofitClient {

    @POST("/api/message/sms/send")
    Call<SendSmsResponseDto> send(@Body SendSmsRequestDto dto);

    @POST("/api/message/sms/send-batch")
    Call<SendBatchSmsResponseDto> sendBatch(@Body SendBatchSmsRequestDto dto);

    @POST("/api/message/sms/send-global")
    Call<Boolean> sendGlobal(@Body SendGlobalSmsRequestDto dto);

    @POST("/api/message/sms/get-user-messages")
    Call<SmsDetailsRootDto> getDetails(@Query("status") SmsDetailStatus status, @Body SmsDetailsRequestDto dto);

    @POST("/api/message/sms/get-user-messages-by-dispatch")
    Call<SmsDispatchResponseDto> getUserMessagesByDispatch(@Query("status") SmsDetailStatus status, @Body SmsDispatchRequestDto dto);

    @POST("/api/message/sms/get-dispatch-status")
    Call<SmsDispatchStatusResponseDto> getDispatchStatus(@Body SmsDispatchStatusRequestDto dto);

    @GET("/api/nick/me")
    Call<Collection<String>> getNicks();

}
