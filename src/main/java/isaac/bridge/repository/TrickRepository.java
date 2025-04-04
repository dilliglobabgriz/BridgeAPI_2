package isaac.bridge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import isaac.bridge.entity.Trick;

public interface TrickRepository extends JpaRepository<Trick, Integer>{
    
    Optional<List<Trick>> findAllByRoundId(int roundId);
}
