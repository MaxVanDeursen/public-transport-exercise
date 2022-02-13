package nl.maxvandeursen.ticketing.traveler;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelerRepository extends CrudRepository<TravelerDto, Long> {
}
