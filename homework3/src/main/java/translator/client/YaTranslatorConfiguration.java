package translator.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YaTranslatorConfiguration {

    private static final String RESOURCE_URL =
            "https://translate.yandex.net/api/v1.5/tr.json/translate?lang={lang}&key={key}&text={text}";

    @Bean
    public YaTranslatorClient yaTranslatorClient(){
       return new YaTranslatorClientImpl(RESOURCE_URL);
    }
}
