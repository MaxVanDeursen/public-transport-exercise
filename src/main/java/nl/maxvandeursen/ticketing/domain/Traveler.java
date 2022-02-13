package nl.maxvandeursen.ticketing.domain;

import java.math.BigDecimal;
import java.util.Objects;

public final class Traveler {
    public static final Traveler NULL_TRAVELER = new Traveler(-1L, "");
    private final Long id;
    private final String username;
    private final BigDecimal credit;

    public Traveler(Long id, String username) {
        this(id, username, BigDecimal.ZERO);
    }

    public Traveler(Long id, String username, BigDecimal credit) {
        this.id = id;
        this.username = Objects.requireNonNull(username, "A defined username should be supplied.");
        this.credit = Objects.requireNonNull(credit, "A defined credit should be supplied.");
    }

    public Traveler addCredit(BigDecimal credit) {
        return new Traveler(id, username, this.credit.add(credit));
    }

    public Traveler subtractCredit(BigDecimal credit) {
        return new Traveler(id, username, this.credit.subtract(credit));
    }

    public boolean canBuyTicket(Ticket ticket) {
        return credit.compareTo(ticket.getCost()) >= 0;
    }

    public String getUsername() {
        return username;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public Long getId() {
        return id;
    }
}
