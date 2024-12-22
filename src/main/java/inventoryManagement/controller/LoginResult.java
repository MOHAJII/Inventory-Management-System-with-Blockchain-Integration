package inventoryManagement.controller;

public class LoginResult {
    private final LoginErrorType errorType;
    private final boolean success;

    public LoginResult(LoginErrorType errorType, boolean success) {
        this.errorType = errorType;
        this.success = success;
    }

    public LoginErrorType getErrorType() {
        return errorType;
    }

    public boolean isSuccess() {
        return success;
    }
}

