# [Eskiz.uz](https://eskiz.uz/sms) SMS Java SDK

## Documentation
 - **Quick start**
   - [Installation package](#installation-package)
   - [First SMS](#send-first-sms)
 - **Main API**
   - Add APIs 
 - **Examples**
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
