package translator.model;

import lombok.Data;

import java.util.List;

@Data
public class Translation {
    private int code;
    private String lang;
    private List<String> text;
}