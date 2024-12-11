package pl.dodo.eLunchApp.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;

public class HandleResult {
    private static final ObjectWriter ow = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .registerModule(new JavaTimeModule())
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
            .writerWithDefaultPrettyPrinter();

    @SneakyThrows
    public static ResponseEntity<String> handleResult(Result<?> toHandle, HttpStatus onSuccess, String uri) {
        if (toHandle.isSuccess())
            return new ResponseEntity<>(ow.writeValueAsString(toHandle.getData()), onSuccess);

        Error error = toHandle.getError();
        ErrorWrapper errorWrapper = getInfoByError(error, uri, onSuccess);

        return new ResponseEntity<>(ow.writeValueAsString(errorWrapper), errorWrapper.occurredStatus());
    }

    private static ErrorWrapper getInfoByError(Error error, String uri, HttpStatus onSuccess) {
        String message;
        HttpStatus httpStatus;
        switch (error) {
            //todo
        };

        return new ErrorWrapper(message, onSuccess, uri, httpStatus);
    }
}
