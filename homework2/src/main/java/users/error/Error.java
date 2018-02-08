package users.error;

import lombok.Data;

@Data
public class Error {
    private String description;
    private ErrorCode code;

    public Error(ErrorCode code, String description) {
        this.code = code;
        this.description = description;
    }
}