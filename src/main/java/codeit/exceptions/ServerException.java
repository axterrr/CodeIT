package codeit.exceptions;

public class ServerException extends RuntimeException {

    public ServerException(String messageKey) {
        super(messageKey);
    }

    public ServerException(Throwable cause) {
        super(cause);
    }

    public ServerException(String messageKey, Throwable cause) {
        super(messageKey, cause);
    }
}
