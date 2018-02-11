package translator;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import translator.client.YaTranslatorConfiguration;
import translator.service.TranslatorConfiguration;

@Configuration
@Import({
        YaTranslatorConfiguration.class,
        TranslatorConfiguration.class
})
class MainConfiguration {
}
