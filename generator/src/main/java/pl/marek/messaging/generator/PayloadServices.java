package pl.marek.messaging.generator;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PayloadServices {

    private static final String JSON_TEMPLATE_FILE_NAME = "client-payloads.json";

    public PayloadServices() {
        super();
    }

    public String getClientPayloadsTemplate() {
        ClassLoader classLoader = PayloadServices.class.getClassLoader();
        String filePath = Objects.requireNonNull(classLoader.getResource(JSON_TEMPLATE_FILE_NAME)).getPath();
        String json = "{}";
        try (Stream<String> lines = Files.lines(Paths.get(filePath))){
            json = lines.collect( Collectors.joining( "" ) );
        } catch (IOException e) {
            // inner error if reourse not found
        }
        return json;
    }
}
