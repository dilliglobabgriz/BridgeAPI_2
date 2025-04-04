package isaac.bridge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import isaac.bridge.entity.Card;

public interface CardRepository extends JpaRepository<Card, Integer>{
    
    Optional<List<Card>> findAllByTrickId(int trickId);
}
