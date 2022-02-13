package nl.maxvandeursen.ticketing.controller;

import nl.maxvandeursen.ticketing.dto.TicketDto;
import nl.maxvandeursen.ticketing.dto.TravelerDto;
import nl.maxvandeursen.ticketing.exception.InvalidTicketDateException;
import nl.maxvandeursen.ticketing.exception.UndefinedTravelerException;
import nl.maxvandeursen.ticketing.service.TicketService;
import nl.maxvandeursen.ticketing.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TravelerController {
    @Autowired
    private TravelerService travelerService;

    @Autowired
    private TicketService ticketService;

    @PostMapping("/travelers")
    public TravelerDto createTraveler(@RequestBody TravelerDto traveler) {
        return travelerService.createTraveler(traveler);
    }

    @GetMapping("/travelers")
    public List<TravelerDto> getAll() {
        return travelerService.getAll();
    }

    @PostMapping("/travelers/{travelerId}/createTicket")
    public TicketDto createTicket(@PathVariable("travelerId") Long travelerId, @RequestBody TicketDto ticket) throws UndefinedTravelerException, InvalidTicketDateException {
        return ticketService.createTicket(travelerId, ticket);
    }
}
