package nl.maxvandeursen.ticketing.traveler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TravelerService {
    @Autowired
    private TravelerRepository travelerRepository;

    public TravelerDto createTraveler(TravelerDto travelerDto) {
        return travelerRepository.save(travelerDto);
    }

    public List<TravelerDto> getAll() {
        Iterable<TravelerDto> travelers = travelerRepository.findAll();
        List<TravelerDto> resultList = new ArrayList<>();
        travelers.forEach(resultList::add);
        return resultList;
    }

    public Optional<TravelerDto> getById(Long id) {
        return travelerRepository.findById(id);
    }
}
