package nl.maxvandeursen.ticketing.ticket;

import nl.maxvandeursen.ticketing.exception.InvalidTicketDateException;
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

    public TicketDto createTicket(Long travelerId, TicketDto ticket) throws UndefinedTravelerException, InvalidTicketDateException {
        TravelerDto traveler = travelerService.getById(travelerId).orElseThrow(() -> new UndefinedTravelerException(travelerId));

        if (ticket.getDate() == null) {
            throw InvalidTicketDateException.forUndefinedDate();
        } else if (ticket.getDate().isBefore(LocalDate.now())) {
            throw InvalidTicketDateException.forExpiredDate(ticket.getDate());
        }

        return ticketRepository.save(TicketDto.fromTicket(new Ticket(null, traveler.asTraveler(), ticket.getDate())));
    }
}
