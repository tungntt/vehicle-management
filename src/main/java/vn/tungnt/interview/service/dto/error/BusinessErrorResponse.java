package vn.tungnt.interview.service.dto.error;

public class BusinessErrorResponse {

    private final String message;

    private final String code;

    public BusinessErrorResponse(final String message, final String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
