package isaac.bridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isaac.bridge.entity.Bid;

public interface BidRepository extends JpaRepository<Bid, Integer>{
    
}
