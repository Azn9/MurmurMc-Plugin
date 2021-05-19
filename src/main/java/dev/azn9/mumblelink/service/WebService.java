package dev.azn9.mumblelink.service;

import com.google.gson.JsonObject;
import dev.azn9.mumblelink.request.AbstractRequest;
import dev.azn9.mumblelink.request.AbstractResponse;
import dev.azn9.mumblelink.request.PingRequest;
import dev.azn9.mumblelink.request.PingRequest.PingResponse;
import reactor.core.publisher.Mono;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;

public class WebService {

    private final String serverUuid;
    private final String apiKey;
    private final String baseUrl = "https://mumble.azn9.dev";

    public WebService(String apiKey) {
        this.apiKey = apiKey;
        this.serverUuid = UUID.randomUUID().toString();
    }

    public boolean connect() throws IOException {
        Optional<PingResponse> pingResponse = this.sendRequest(new PingRequest()).blockOptional();
        return pingResponse.isPresent();
    }

    public <U extends AbstractResponse, T extends AbstractRequest<U>> Mono<U> sendRequest(T request) {
        return Mono.create(sink -> {
            try {
                URL url = new URL(this.baseUrl + request.getRequestType().getEndpoint());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(request.getRequestType().getMethod());
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(2000);
                connection.setReadTimeout(2000);
                connection.setDoOutput(true);

                JsonObject requestData = request.getRequestData();
                requestData.addProperty("server_uuid", this.serverUuid);
                requestData.addProperty("api_key", this.apiKey);

                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.writeBytes(requestData.toString());
                dataOutputStream.flush();
                dataOutputStream.close();

                int status = connection.getResponseCode();

                if (status != 201) {
                    if (status == 204) {
                        sink.success(request.newResponse());
                    } else {
                        try {
                            sink.error(new IllegalStateException(connection.getResponseMessage()));
                        } catch (IOException e) {
                            e.printStackTrace();

                            sink.error(e);
                        }
                    }
                } else {
                    sink.success(request.newResponse(connection.getInputStream()));
                }
            } catch (IOException e) {
                e.printStackTrace();

                sink.error(e);
            }
        });
    }

    public void shutdown() {

    }
}
