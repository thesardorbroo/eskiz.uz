package uz.sardorbroo.eskiz.service.client.retrofit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import uz.sardorbroo.eskiz.factory.EskizPropertiesFactory;
import uz.sardorbroo.eskiz.factory.RealEskizPropertiesFactory;
import uz.sardorbroo.eskizuz.constants.EskizClientConstants;
import uz.sardorbroo.eskizuz.dto.sms.SendBatchSmsMessageRequestDto;
import uz.sardorbroo.eskizuz.dto.sms.SendBatchSmsRequestDto;
import uz.sardorbroo.eskizuz.dto.sms.SendSmsRequestDto;
import uz.sardorbroo.eskizuz.service.client.retrofit.SmsRetrofitClient;
import uz.sardorbroo.eskizuz.utils.RetrofitClientUtils;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Disabled
public class SmsRetrofitClientTest {

    private static final EskizPropertiesFactory FACTORY = new RealEskizPropertiesFactory();

    private SmsRetrofitClient client;

    @BeforeEach
    public void init() {

        var ok = RetrofitClientUtils.buildOkHttpClientWithInterceptor(FACTORY.getProperties());
        this.client = RetrofitClientUtils.getClient(SmsRetrofitClient.class, ok);

    }

    @Test
    public void testSendSmsWithNotValidMessage() throws IOException {
        log.debug("Send sms message with invalid message");

        var body = new SendSmsRequestDto();
        body.setFrom(FACTORY.getProperties().getFrom());
        body.setMessage("This message should not be sent!");
        body.setMobilePhone(EskizClientConstants.ESKIZ_TEST_MOBILE_PHONE_NUMBER);

        var call = client.send(body);
        var response = call.execute();

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isSuccessful());
        Assertions.assertEquals(400, response.code());
        Assertions.assertNull(response.body());

        log.debug("Response: {}", response);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "         ", "123", "111111111111", "11111999124625", "+998999124625" })
    public void testSendSmsWithInvalidPhoneNumber(String phoneNumber) throws IOException {
        log.debug("Send sms message with invalid phone number! Phone number: {}", phoneNumber);

        var body = new SendSmsRequestDto();
        body.setFrom(FACTORY.getProperties().getFrom());
        body.setMessage(EskizClientConstants.ESKIZ_TEST_MESSAGE_EN);
        body.setMobilePhone(phoneNumber);

        var call = client.send(body);
        var response = call.execute();

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isSuccessful());
        Assertions.assertEquals(400, response.code());
        Assertions.assertNull(response.body());

        log.debug("Response: {}", response);
    }

    @Test
    public void testSendSmsWithValidParams() throws IOException {
        log.debug("Send sms message with valid params");

        var body = new SendSmsRequestDto();
        body.setFrom(FACTORY.getProperties().getFrom());
        body.setMessage(EskizClientConstants.ESKIZ_TEST_MESSAGE_UZ);
        body.setMobilePhone(EskizClientConstants.ESKIZ_TEST_MOBILE_PHONE_NUMBER);

        var call = client.send(body);
        var response = call.execute();

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());

        Assertions.assertEquals("waiting", response.body().getStatus());
        log.debug("Response: {}", response.body());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            EskizClientConstants.ESKIZ_TEST_MESSAGE_EN,
            EskizClientConstants.ESKIZ_TEST_MESSAGE_RU,
            EskizClientConstants.ESKIZ_TEST_MESSAGE_UZ
    })
    public void testSendSmsWithAllTestMessages(String message) throws IOException {
        log.debug("Send sms message with all test messages");

        var body = new SendSmsRequestDto();
        body.setFrom(FACTORY.getProperties().getFrom());
        body.setMessage(message);
        body.setMobilePhone(EskizClientConstants.ESKIZ_TEST_MOBILE_PHONE_NUMBER);

        var call = client.send(body);
        var response = call.execute();

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());

        Assertions.assertEquals("waiting", response.body().getStatus());
        log.debug("Response: {}", response.body());
    }

    @Test
    @Disabled("should fix, throws 403")
    public void testSentBatchSmsMessageWithValidParams() throws IOException {
        log.debug("Send batch sms message with valid params");

        var recipient1 = new SendBatchSmsMessageRequestDto();
        recipient1.setId(String.valueOf(UUID.randomUUID()));
        recipient1.setTo(EskizClientConstants.ESKIZ_TEST_MOBILE_PHONE_NUMBER);
        recipient1.setTo(EskizClientConstants.ESKIZ_TEST_MESSAGE_UZ);

        var recipient2 = new SendBatchSmsMessageRequestDto();
        recipient2.setId(String.valueOf(UUID.randomUUID()));
        recipient2.setTo(EskizClientConstants.ESKIZ_TEST_MOBILE_PHONE_NUMBER_2);
        recipient2.setTo(EskizClientConstants.ESKIZ_TEST_MESSAGE_RU);

        var body = new SendBatchSmsRequestDto();
        body.setFrom(FACTORY.getProperties().getFrom());
        body.setMessages(List.of(recipient1, recipient2));
        body.setDispatchId(EskizClientConstants.ESKIZ_DISPATCH_ID);

        var call = client.sendBatch(body);
        var response = call.execute();

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertNotNull(response.body());

        log.debug("Response: {}", response);
    }

}
