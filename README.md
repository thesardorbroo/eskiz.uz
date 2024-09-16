# [Eskiz.uz](https://eskiz.uz/sms) SMS Java SDK

## Introduction
Hi. This is small repository just Java SDK(client) for `SMS` service of [Eskiz.uz](https://eskiz.uz/sms) provider. 
Eskiz has many services like hosting, domain, SMS etc. Plugin has integrated to only SMS service. 
Plugin has built for not implementing clients(RestTemplate, OpenFeign, etc.),
DTOs, Interfaces again and again. And see [otp-plugin](https://github.com/thesardorbroo/otp-plugin.git) repository. 
This plugin will use in there, because this plugin is part of sending otp. This plugin has built separately for providing more abstraction and reusability.
I was wondering if you could add your attention and efforts, let's scale plugin together.

Thank you for your attention.

> [!IMPORTANT]
> If you want to see how really works plugin, follow these steps:
> - Override fields of [`EskizCredentialsConstants`](/src/test/java/uz/sardorbroo/eskiz/constants/EskizCredentialsConstants.java)
> - Run all tests of `/src/java/test` package

## Documentation
 - **Quick start**
   - [Installation package](#installation-package)
   - [First SMS](#send-first-sms)
 - **Main API**
   - [Authorizer](/src/main/java/uz/sardorbroo/eskizuz/service/Authorizer.java)
   - [SMS](/src/main/java/uz/sardorbroo/eskizuz/service/Sms.java)
   - [~~Template~~](/src/main/java/uz/sardorbroo/eskizuz/service/Template.java)
   - Contact <sup>not implemented yet</sup>
   - Reports <sup>not implemented yet</sup>
 - **Examples**
   - [Customizing](#customizing-implementation)
   - [Integrating with Spring Boot](#integrating-to-spring-boot)
   - Add examples 
 - **Publish package**
   - Add instruction about how to publish new package
 - **Developers**
   - [Contacts](#developers)
 - **Todo**
   - [Todo](#should-do)

### Installation package

Follow these steps for installing package:

1. **Override `settings.xml`, add credentials for reading package**
   ```xml
       <servers>
        <server>
            <id>github-sardorbroo-eskizuz</id>
            <username>${ username }</username>
            <password>${ password or token }</password>
            <configuration>
                <authenticationInfo>
                    <userName>${ username }</userName>
                    <password>${ password or token }</password>
                </authenticationInfo>
            </configuration>
        </server>
    </servers>
   ```
> [!NOTE]
> Token for only read package, you cannot publish something new with this token. 
> If you want to add something add connect to owner([thesardorbroo](https://t.me/Sardorbro11))

> [!IMPORTANT]
> - Get public credentials from [settings.xml](/settings.xml)
> - If you don't want to edit global `settings.xml`, use our already configured `settings.xml`.
     You just need copy/paste and change your **Intellij Idea** maven user settings. See [guide](https://link-to-guide-which-is-teaches-how-to-change-maven-settings.xml)

3. **Add repository tag to your `pom.xml`:**
   ```xml
   <repositories>
        <!-- Other repositories -->
        <repository>
           <id>sardorbroo-github-eskizuz</id>
           <url>https://maven.pkg.github.com/thesardorbroo/eskiz.uz</url>
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
   Install the package by following command:

   `mvn clean install`

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
> [!TIP]
> Use `Eskiz` and `Eskiz.Builder`, because they have default implementations. 
> If you want to use own implementation, it is so easy. See guide.


### Customizing 
This section includes, customizing and integrating SDK to Spring Boot easily

#### Customizing implementation
In this example we will customize `SmsClient` 
and use it with builder of `Eskiz`, and straightly. Examples:
- **Customizing `SmsClient`:**
  ```java
    import java.lang.String;
    import java.util.Optional;
    import uz.sardorbroo.eskizuz.dto.sms.SendSmsResponseDto;   
    import uz.sardorbroo.eskizuz.service.client.SmsClient;
    import uz.sardorbroo.eskizuz.constants.EskizClientConstants;
    import org.springframework.web.client.RestTemplate;
    import org.springframework.http.HttpMethod;
    import org.springframework.http.HttpEntity;
    import org.springframework.http.HttpHeaders;
    
    public class RestTemplateSmsClient implements SmsClient { 
      
      private static final String SEND_SMS_URL = EskizClientConstants.BASE_URL + "/api/message/sms/send";
         
      private final RestTemplate client = new RestTemplate();
    
      public Optional<SendSmsResponseDto> send(SendSmsRequestDto dto) {
          
          // some code like log, validation and etc
    
          HttpHeaders headers = new HttpHeaders();
          headers.setBearerAuth(/* You token */);
          ResponseEntity<SendSmsResponseDto> response = 
              client.exchange(SEND_SMS_URL, HttpMethod.POST, new HttpEntity<>(headers), SendSmsResponseDto.class);  
          
          // check or validate response
          return Optional.of(response.getBody());
      }
    }
  ```
- **Add custom client to `Eskiz.Builder`:**
  ```java
  
    import uz.sardorbroo.eskizuz.Eskiz;
    import uz.sardorbroo.eskizuz.properties.EskizProperties;import uz.sardorbroo.eskizuz.service.Sms;import uz.sardorbroo.eskizuz.service.client.SmsClient;import uz.sardorbroo.eskizuz.service.impl.SmsImpl;
    
    public static void main(String[] args){
      EskizProperties properties = new EskizProperties();
      properties.setEmail(/* Your email */);
      properties.setPassword(/* Your password */);
    
      SmsClient client = new RestTemplateSmsClient();
    
      Sms sms = new SmsImpl(client);
    
      Eskiz eskiz = new Eskiz.Builder(properties)
          .sms(sms)
          .build();
    
      eskiz.sms().send(/* DTO */);
    }

  ```

#### Integrating to Spring Boot

- **Create bean of customized client implementation:**
  ```java
  
    import uz.sardorbroo.eskizuz.service.Sms;
    import uz.sardorbroo.eskizuz.service.client.SmsClient;
    
    @Configuration
    public static SmsConfig() {
      
      @Bean("RestTemplate")
      public SmsClient configureRestTemplateClient() {
        return new RestTemplateSmsClient();
      }
    
      @Bean
      public Sms configureSms(@Qualifier("RestTemplate") SmsClient client) {
          return new Sms(client);
      }
    }
  ```

### Publish package

## Developers
| Name | Nick name  | Stack | Contact                               |
|:-----|:-----------|:------|:--------------------------------------|
|Sardor Shorahimov| Sardorbroo | Java backend| [Telegram](https://t.me/Sardorbro11/) |

## Should do

- [x] Implement `Authorizer` builder
- [x] Add `Retrofit` interceptor
- [ ] Publish the package to GitHub package registry
- [ ] Write documentation
- [ ] Complete send SMS tests
- [ ] Write tests
- [ ] `@Slf4j` doesn't work while working junit tests
