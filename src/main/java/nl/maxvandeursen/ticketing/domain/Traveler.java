package nl.maxvandeursen.ticketing.domain;

import java.math.BigDecimal;
import java.util.Objects;

public final class Traveler {
    public static final Traveler NULL_TRAVELER = new Traveler("");

    private final String username;
    private final BigDecimal credit;

    public Traveler(String username) {
        this(username, BigDecimal.ZERO);
    }

    public Traveler(String username, BigDecimal credit) {
        this.username = Objects.requireNonNull(username, "A defined username should be supplied.");
        this.credit = Objects.requireNonNull(credit, "A defined credit should be supplied.");
    }

    public Traveler addCredit(BigDecimal credit) {
        return new Traveler(username, this.credit.add(credit));
    }

    public Traveler subtractCredit(BigDecimal credit) {
        return new Traveler(username, this.credit.subtract(credit));
    }

    public String getUsername() {
        return username;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public boolean canBuyTicket(Ticket ticket) {
        return credit.compareTo(ticket.getCost()) >= 0;
    }
}
