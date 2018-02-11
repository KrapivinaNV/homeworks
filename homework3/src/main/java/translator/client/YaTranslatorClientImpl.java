package translator.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import translator.model.Translation;

import java.io.IOException;
import java.util.Map;

class YaTranslatorClientImpl implements YaTranslatorClient {

    private final String resourceUrl;

    YaTranslatorClientImpl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    @Override
    public Translation getTranslation(Map<String, String> params) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(resourceUrl, String.class, params);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseEntity.getBody(), Translation.class);
    }
}