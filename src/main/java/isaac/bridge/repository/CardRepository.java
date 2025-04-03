package isaac.bridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isaac.bridge.entity.Card;

public interface CardRepository extends JpaRepository<Card, Integer>{
    
}
