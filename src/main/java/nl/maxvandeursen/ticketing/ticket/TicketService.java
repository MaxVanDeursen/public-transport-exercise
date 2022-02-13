package nl.maxvandeursen.ticketing.ticket;

import nl.maxvandeursen.ticketing.exception.InvalidTicketDateException;
import nl.maxvandeursen.ticketing.exception.PurchaseConditionViolationException;
import nl.maxvandeursen.ticketing.exception.UndefinedTravelerException;
import nl.maxvandeursen.ticketing.traveler.TravelerDto;
import nl.maxvandeursen.ticketing.traveler.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TravelerService travelerService;

    public TicketDto createTicket(Long travelerId, TicketDto ticketDto) throws UndefinedTravelerException, InvalidTicketDateException, PurchaseConditionViolationException {
        TravelerDto traveler = travelerService.getById(travelerId).orElseThrow(() -> new UndefinedTravelerException(travelerId));

        if (ticketDto.getDate() == null) {
            throw InvalidTicketDateException.forUndefinedDate();
        } else if (ticketDto.getDate().isBefore(LocalDate.now())) {
            throw InvalidTicketDateException.forExpiredDate(ticketDto.getDate());
        }

        Ticket ticket = new Ticket(null, traveler.asTraveler(), ticketDto.getDate());
        if (!traveler.toTraveler().canBuyTicket(ticket)) {
            throw new PurchaseConditionViolationException();
        }

        return ticketRepository.save(TicketDto.fromTicket(new Ticket(null, traveler.asTraveler(), ticket.getDate())));
    }
}
