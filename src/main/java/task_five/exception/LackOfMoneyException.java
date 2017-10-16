package task_five.exception;

public class LackOfMoneyException extends Exception {

    public LackOfMoneyException() {
    }

    public LackOfMoneyException(String message) {
        super(message);
    }
}
