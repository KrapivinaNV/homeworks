package users.controller;

import lombok.Data;
import users.error.Error;

@Data
public class Response<T> {
    private T value;
    private Error error;

    Response(){
    }

    Response(Error error) {
        this.error = error;
    }

    Response(T value){
        this.value = value;
    }
}
