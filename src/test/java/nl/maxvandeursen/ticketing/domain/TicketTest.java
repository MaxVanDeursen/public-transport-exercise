package nl.maxvandeursen.ticketing.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @Test
    void nullDateShouldRaiseException() {
        assertThrows(NullPointerException.class, () -> new Ticket(Traveler.NULL_TRAVELER, null));
    }

    @Test
    void nullTravelerShouldRaiseException() {
        assertThrows(NullPointerException.class, () -> new Ticket(null, LocalDate.now()));
    }

    @Test
    public void correctFieldsAreSet() {
        Traveler traveler = Traveler.NULL_TRAVELER;
        LocalDate date = LocalDate.now();
        Ticket ticket = new Ticket(traveler, date);

        assertEquals(traveler, ticket.getTraveler());
        assertEquals(date, ticket.getDate());
    }

    @Test
    public void canBeUsedIfTheTicketIsToday() {
        Ticket ticket = new Ticket(Traveler.NULL_TRAVELER, LocalDate.now());

        assertTrue(ticket.isUsable());
    }

    @Test
    public void cannotBeUsedIfTicketIsExpired() {
        Ticket ticket = new Ticket(Traveler.NULL_TRAVELER, LocalDate.now().minusDays(1));

        assertFalse(ticket.isUsable());
    }

    @Test
    public void cannotBeUsedIfTicketIsForFutureDate() {
        Ticket ticket = new Ticket(Traveler.NULL_TRAVELER, LocalDate.now().plusDays(1));

        assertFalse(ticket.isUsable());
    }

    @Test
    public void defaultTicketCostIsSetCorrectly() {
        Ticket ticket = new Ticket(Traveler.NULL_TRAVELER, LocalDate.now().plusDays(1));

        assertEquals(Ticket.DEFAULT_COST, ticket.getCost());
    }
}