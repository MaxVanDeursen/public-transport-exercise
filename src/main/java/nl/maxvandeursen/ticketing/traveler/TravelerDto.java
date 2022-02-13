package nl.maxvandeursen.ticketing.traveler;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class TravelerDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private BigDecimal credit;

    public static TravelerDto fromTraveler(Traveler traveler) {
        TravelerDto travelerDto = new TravelerDto();
        travelerDto.setId(traveler.getId());
        travelerDto.setUsername(traveler.getUsername());
        travelerDto.setCredit(traveler.getCredit());
        return travelerDto;
    }

    public Traveler asTraveler() {
        return new Traveler(id, username, credit);
    }

    public Traveler toTraveler() {
        return new Traveler(id, username, credit);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }
}
