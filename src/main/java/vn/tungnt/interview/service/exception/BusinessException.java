package vn.tungnt.interview.service.exception;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1035183647220754238L;

    public BusinessException(final String message) {
        super(message);
    }
}
