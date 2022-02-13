package nl.maxvandeursen.ticketing.repository;

import nl.maxvandeursen.ticketing.dto.TicketDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends CrudRepository<TicketDto, Long> {
}
