package dev.azn9.mumblelink.request;

public enum RequestType {

    PING("/api/ping", "GET"),
    CREATE_SERVER("/api/createserver", "POST");


    private final String endpoint;
    private final String method;

    RequestType(String endpoint, String method) {
        this.endpoint = endpoint;
        this.method = method;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public String getMethod() {
        return this.method;
    }
}
