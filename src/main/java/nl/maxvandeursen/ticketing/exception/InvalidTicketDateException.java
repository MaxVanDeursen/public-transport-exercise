package nl.maxvandeursen.ticketing.exception;

import java.time.LocalDate;

public class InvalidTicketDateException extends Exception {
    private InvalidTicketDateException(String message) {
        super(message);
    }

    public static InvalidTicketDateException forUndefinedDate() {
        return new InvalidTicketDateException("The 'date' of the ticket should be defined.");
    }

    public static InvalidTicketDateException forExpiredDate(LocalDate date) {
        return new InvalidTicketDateException("The 'date' " + date + " is in the past and a new ticket can therefore not be created.");
    }
}
