package nl.maxvandeursen.ticketing.ticket;

import nl.maxvandeursen.ticketing.traveler.TravelerDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class TicketDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = TravelerDto.class)
    private TravelerDto traveler;
    private LocalDate date;
    private BigDecimal cost;

    public static TicketDto fromTicket(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setTraveler(TravelerDto.fromTraveler(ticket.getTraveler()));
        ticketDto.setDate(ticket.getDate());
        ticketDto.setCost(ticket.getCost());
        return ticketDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TravelerDto getTraveler() {
        return traveler;
    }

    public void setTraveler(TravelerDto traveler) {
        this.traveler = traveler;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
