package translator.client;

import translator.model.Translation;

import java.io.IOException;
import java.util.Map;

public interface YaTranslatorClient {

    Translation getTranslation(Map<String, String> params) throws IOException;
}