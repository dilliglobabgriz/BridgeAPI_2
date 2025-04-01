package isaac.bridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isaac.bridge.entity.Round;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long>{
    
}
