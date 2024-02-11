package testInInfotecs.utilsFTP;

public enum StatusCode {

    OK("200 Ok"),
    NOT_FOUND_HOST("404 The requested host is unavailable, try again"),
    FILE_STATUS("213 Incorrect data in file"),
    LOGIN_HAS_NOT_BEEN_COMPLETED("530 Login or password is incorrect, try again"),
    SYNTAX_ERROR("501 Incorrect server address"),
    FILE_NOT_AVAILABLE("550 There is no file on this server"),
    INVALID_PASSWORD("331 Incorrect password ,please try again"),
    INTERNAL_SERVER_ERROR("500 Connection error");

    private final String message;

    StatusCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
