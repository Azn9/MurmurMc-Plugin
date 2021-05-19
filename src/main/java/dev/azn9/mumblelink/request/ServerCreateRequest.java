package dev.azn9.mumblelink.request;

public class ServerCreateRequest extends AbstractRequest<AbstractResponse> {

    protected ServerCreateRequest() {
        super(RequestType.CREATE_SERVER);
    }

    @Override
    public AbstractResponse newResponse(String responseMessage) {
        return AbstractResponse.empty();
    }

}
