# [Eskiz.uz](https://eskiz.uz/sms) SMS Java SDK

## Should do

- [x] Implement `Authorizer` builder
- [x] Add `Retrofit` interceptor
- [ ] Publish the package to GitHub package registry
- [ ] Write documentation
- [ ] Complete send SMS tests
- [ ] Write tests
- [ ] `@Slf4j` doesn't work while working junit tests

## Quick start

### Installation package

Follow these steps for installing package:

1. **Add repository tag to your `pom.xml`:**
   ```xml
   <repositories>
        <!-- Other repositories -->
        <repository>
           <id>override repository id</id>
           <url>override url of package</url>
        </repository>
   </repositories>
   ```

2. **Add dependency tag to `pom.xml`:**
   ```xml
   <dependencyManagement>
       <!-- Other dependencies -->
       <dependency>
           <groupId>uz.sardorbroo</groupId>
           <artifactId>eskizuz</artifactId>
           <version>1.0.0</version>
       </dependency>
   </dependencyManagement>
   ```

3. **Verify package**
   ```
   Install the packge by following command:
   
   mvn clean install
   ```

### Send first SMS

```java
import uz.sardorbroo.eskizuz.Eskiz;
import uz.sardorbroo.eskizuz.constants.EskizClientConstants;
import uz.sardorbroo.eskizuz.dto.sms.SendSmsRequestDto;
import uz.sardorbroo.eskizuz.dto.sms.SendSmsResponseDto;
import uz.sardorbroo.eskizuz.properties.EskizProperties;

import java.util.Optional;

public static void main(String[] args) {

   EskizProperties properties = new EskizProperties();
   properties.setEmail(/* Your email */);
   properties.setPassword(/* Your password */);

   Eskiz eskiz = new Eskiz.Builder(properties)
           .build();

   SendSmsRequestDto request = new SendSmsRequestDto();
   request.setFrom(properties.getFrom());
   request.setCallbackUrl(/* Your callback URL. It is optional */);

   // If you bought tariff from Eskiz.uz you can set any message
   request.setMessage(EskizClientConstants.ESKIZ_TEST_MESSAGE_UZ);

   // be sure phone number doesn't contain symbol '+'
   request.setMobilePhone(/* Your phone number */);

   Optional<SendSmsResponseDto> responseOptional = eskiz.sms(request).send();
   
   System.out.println(responseOptional);
}
```