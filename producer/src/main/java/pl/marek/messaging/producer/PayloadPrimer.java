package pl.marek.messaging.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.marek.messaging.producer.model.json.Clients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Component
public class PayloadPrimer implements CommandLineRunner {

    @Value("${messaging.app.generator.url}")
    private String urlOfGenerator;

    @Value("${messaging.app.consumer.url}")
    private String urlOfConsumer;

    private static final Logger LOGGER = LoggerFactory.getLogger(PayloadPrimer.class);

    private final ConfigurableApplicationContext context;

    @Autowired
    public PayloadPrimer(ConfigurableApplicationContext context, ObjectMapper objectMapper) {
        super();
        this.context = context;
    }

    @Override
    public void run(String... strings) throws Exception {

        String json = fetchRequestToSend();
        String response = sendToConsumer(json);
        LOGGER.info(">>> Response: {}", response);
        System.exit(SpringApplication.exit(context));
    }

    // https://stackoverflow.com/questions/21404252/post-request-send-json-data-java-httpurlconnection
    private String sendToConsumer(String json) throws IOException {
        URL url = new URL(urlOfConsumer);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("data", json);

        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes(StandardCharsets.UTF_8));
        os.close();

        // read the response
        InputStream in = new BufferedInputStream(conn.getInputStream());
        String result = org.apache.commons.io.IOUtils.toString(in, StandardCharsets.UTF_8);
//        JSONObject jsonObject = new JSONObject(result);

        in.close();
        conn.disconnect();

        return result;
    }

    private String fetchRequestToSend() throws IOException, ParseException {
        URL url = new URL(urlOfGenerator);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestMethod("GET");
        conn.connect();
        int responsecode = conn.getResponseCode();
        if(responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        }
        String json = readJson(url);
        LOGGER.info(json);
        try {
            // check if source with pl.marek.messaging.producer.model.json is OK
            parseJson(json);
        } catch (ParseException e) {
            LOGGER.error("Wrong JSON format {}", json);
            throw new ParseException(ParseException.ERROR_UNEXPECTED_EXCEPTION);
        }
        return json;
    }

    private Clients parseJson(String inline) throws ParseException {
        JSONParser parse = new JSONParser();
        JSONObject jobj = (JSONObject)parse.parse(inline);
        JSONObject jclients = (JSONObject) jobj.get("clients");
        return Clients.build(jclients);
    }

    private String readJson(URL url) throws IOException {
        StringBuilder inline = new StringBuilder();
        Scanner sc = new Scanner(url.openStream());
        while(sc.hasNext())
        {
            inline.append(sc.nextLine());
        }
        sc.close();
        return inline.toString();
    }
}