package nl.maxvandeursen.ticketing.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TravelerTest {
    private final String username = "username";

    @Test
    void nullUsernameRaisesException() {
        assertThrows(NullPointerException.class, () -> new Traveler(null));
        assertThrows(NullPointerException.class, () -> new Traveler(null, BigDecimal.ZERO));
    }

    @Test
    void nullCreditRaisesException() {
        assertThrows(NullPointerException.class, () -> new Traveler(username, null));
    }

    @Test
    void newTravelerIsInitializedWithoutMoney() {
        Traveler traveler = new Traveler(username);

        assertEquals(username, traveler.getUsername());
        assertEquals(BigDecimal.ZERO, traveler.getCredit());
    }

    @Test
    void travelerCanBeDefinedWithCredit() {
        BigDecimal credit = BigDecimal.ONE;
        Traveler traveler = new Traveler(username, credit);

        assertEquals(credit, traveler.getCredit());
    }

    @Test
    void travelerWithoutSufficientCreditCannotBuyTicket() {
        Traveler traveler = new Traveler(username);
        Ticket ticket = new Ticket(traveler, LocalDate.now());

        assertFalse(traveler.canBuyTicket(ticket));
    }

    @Test
    void travelerWithEnoughCreditCanBuyTicket() {
        Traveler traveler = new Traveler(username, Ticket.DEFAULT_COST);
        Ticket ticket = new Ticket(traveler, LocalDate.now());

        assertTrue(traveler.canBuyTicket(ticket));
    }

    @Test
    void subtractionOfCreditIsPossible() {
        Traveler traveler = new Traveler(username, Ticket.DEFAULT_COST);

        traveler = traveler.subtractCredit(Ticket.DEFAULT_COST);

        assertEquals(BigDecimal.ZERO, traveler.getCredit());
    }

    @Test
    void additionOfCreditIsPossible() {
        Traveler traveler = new Traveler(username, BigDecimal.ZERO);

        traveler = traveler.addCredit(Ticket.DEFAULT_COST);

        assertEquals(Ticket.DEFAULT_COST, traveler.getCredit());
    }
}