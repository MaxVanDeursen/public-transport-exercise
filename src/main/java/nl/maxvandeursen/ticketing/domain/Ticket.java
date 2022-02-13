package nl.maxvandeursen.ticketing.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public final class Ticket {
    public static final BigDecimal DEFAULT_COST = BigDecimal.ONE;

    private final Traveler traveler;
    private final LocalDate date;
    private final BigDecimal cost;

    public Ticket(Traveler traveler, LocalDate date) {
        this.traveler = Objects.requireNonNull(traveler, "A defined traveler should be supplied.");
        this.date = Objects.requireNonNull(date, "A defined date should be supplied");
        this.cost = DEFAULT_COST;
    }

    public boolean isUsable() {
        return LocalDate.now().equals(date);
    }

    public Traveler getTraveler() {
        return traveler;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getCost() {
        return cost;
    }
}
