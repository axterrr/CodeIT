package codeit.exceptions;

public class ServiceException extends RuntimeException {

    public ServiceException(String messageKey) {
        super(messageKey);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String messageKey, Throwable cause) {
        super(messageKey, cause);
    }
}
