package nl.maxvandeursen.ticketing.ticket;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<TicketDto, Long> {
}
