package task_five.first.exception;

public class LackOfMoneyException extends Exception {

    public LackOfMoneyException() {
    }

    public LackOfMoneyException(String message) {
        super(message);
    }
}
