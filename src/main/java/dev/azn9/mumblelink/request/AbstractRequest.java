package dev.azn9.mumblelink.request;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.InputStream;

public abstract class AbstractRequest<U extends AbstractResponse> {

    protected static final Gson gson = new Gson();

    private final RequestType requestType;

    protected AbstractRequest(RequestType requestType) {
        this.requestType = requestType;
    }

    public RequestType getRequestType() {
        return this.requestType;
    }

    public JsonObject getRequestData() {
        return new JsonObject();
    }

    public U newResponse(InputStream inputStream) {
        return null;
    }

    public abstract U newResponse(String responseMessage);

    public U newResponse() {
        return null;
    }

}
