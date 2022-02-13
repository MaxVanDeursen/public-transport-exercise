package nl.maxvandeursen.ticketing.ticket;

import nl.maxvandeursen.ticketing.exception.PurchaseConditionViolationException;
import nl.maxvandeursen.ticketing.traveler.*;
import nl.maxvandeursen.ticketing.exception.InvalidTicketDateException;
import nl.maxvandeursen.ticketing.exception.UndefinedTravelerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
    @InjectMocks
    private TicketService ticketService;

    @MockBean
    private TicketRepository ticketRepository;

    @MockBean
    private TravelerService travelerService;

    private TravelerDto traveler;

    @BeforeEach
    void instantiate() {
        traveler = new TravelerDto();
        traveler.setUsername("");
        traveler.setCredit(BigDecimal.ZERO);
        when(travelerService.getById(any())).thenReturn(Optional.of(traveler));
    }

    @Test
    void notFoundTravelerRaisesException() {
        when(travelerService.getById(any())).thenReturn(Optional.empty());

        assertThrows(UndefinedTravelerException.class, () -> ticketService.createTicket(1L, new TicketDto()));
    }

    @Test
    void undefinedTicketInPastRaisesException() {
        assertThrows(InvalidTicketDateException.class, () -> ticketService.createTicket(1L, new TicketDto()));
    }

    @Test
    void ticketInPastRaisesException() {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setDate(LocalDate.now().minusDays(1));

        assertThrows(InvalidTicketDateException.class, () -> ticketService.createTicket(1L, ticketDto));
    }

    @Test
    void notEnoughCreditRaisesException() {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setCost(Ticket.DEFAULT_COST);
        ticketDto.setDate(LocalDate.now());

        assertThrows(PurchaseConditionViolationException.class, () -> ticketService.createTicket(1L, ticketDto));
    }

    @Test
    void correctTicketIsReturned() throws Exception {
        TicketDto ticketDto = new TicketDto();
        traveler.setCredit(Ticket.DEFAULT_COST);
        ticketDto.setDate(LocalDate.now());
        TicketDto returnDto = new TicketDto();
        when(ticketRepository.save(any())).thenReturn(returnDto);

        TicketDto result = ticketService.createTicket(1L, ticketDto);

        assertEquals(returnDto, result);
    }
}