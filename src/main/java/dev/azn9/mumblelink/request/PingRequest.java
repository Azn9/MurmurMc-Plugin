package dev.azn9.mumblelink.request;

public class PingRequest extends AbstractRequest<PingRequest.PingResponse> {

    public PingRequest() {
        super(RequestType.PING);
    }

    @Override
    public PingResponse newResponse(String responseMessage) {
        return new PingResponse(responseMessage);
    }

    public static class PingResponse extends AbstractResponse {

        public PingResponse(String responseMessage) {
            super(responseMessage);
        }

    }

}
