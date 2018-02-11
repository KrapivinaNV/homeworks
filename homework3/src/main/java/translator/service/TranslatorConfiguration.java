package translator.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import translator.client.YaTranslatorClient;

@Configuration
public class TranslatorConfiguration {

    @Bean
    Translator translation(YaTranslatorClient yaTranslatorClient) {
        return new TranslatorImpl(
                "trnsl.1.1.20180211T102507Z.baf0fbcb574133fd.5d9c11b96b948a95d877ed87bae567c11406a2e1",
                "en-ru",
                yaTranslatorClient
        );
    }
}
