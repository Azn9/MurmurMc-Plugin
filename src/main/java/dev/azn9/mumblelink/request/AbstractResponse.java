package dev.azn9.mumblelink.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class AbstractResponse {

    protected JsonObject jsonObject;

    public AbstractResponse(String responseMessage) {
        this.jsonObject = new JsonParser().parse(responseMessage).getAsJsonObject();
    }

    public static AbstractResponse empty() {
        return new AbstractResponse("{}") {};
    }

}
