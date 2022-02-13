package nl.maxvandeursen.ticketing.repository;

import nl.maxvandeursen.ticketing.dto.TravelerDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelerRepository extends CrudRepository<TravelerDto, Long> {
}
