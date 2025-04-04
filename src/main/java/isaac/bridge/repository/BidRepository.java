package isaac.bridge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import isaac.bridge.entity.Bid;

public interface BidRepository extends JpaRepository<Bid, Integer>{
    
    Optional<List<Bid>> findAllByRoundId(int roundId);
}
