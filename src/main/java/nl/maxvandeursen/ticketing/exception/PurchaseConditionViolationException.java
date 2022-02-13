package nl.maxvandeursen.ticketing.exception;

public class PurchaseConditionViolationException extends Exception {
    public PurchaseConditionViolationException() {
        super("The traveler is not able to purchase the requested ticket.");
    }
}
