package nl.maxvandeursen.ticketing.exception;

public class UndefinedTravelerException extends Exception {
    public UndefinedTravelerException(Long id) {
        super("No Traveler with ID '" + id + " could be found.");
    }
}
