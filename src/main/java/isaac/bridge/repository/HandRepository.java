package isaac.bridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isaac.bridge.entity.Hand;

public interface HandRepository extends JpaRepository<Hand, Integer>{
    
}
