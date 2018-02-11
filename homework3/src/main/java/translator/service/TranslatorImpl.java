package translator.service;

import org.springframework.util.StringUtils;
import translator.client.YaTranslatorClient;
import translator.exception.TranslationException;
import translator.model.Translation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TranslatorImpl implements Translator {

    private final String key;
    private final YaTranslatorClient yaTranslatorClient;
    private final String languages;

    TranslatorImpl(String key, String languages, YaTranslatorClient yaTranslatorClient) {
        this.key = key;
        this.languages = languages;
        this.yaTranslatorClient = yaTranslatorClient;
    }

    public List<String> translate(String input) {
        if (StringUtils.isEmpty(input)) {
            return new ArrayList<>();
        }

        Map<String, String> params = new HashMap<>();

        params.put("lang", languages);
        params.put("key", key);
        params.put("text", input);

        Translation translation;
        try {
            translation = yaTranslatorClient.getTranslation(params);
        } catch (IOException e) {
            throw new TranslationException(e);
        }

        return translation.getText();
    }
}
