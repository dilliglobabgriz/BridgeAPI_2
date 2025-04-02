package isaac.bridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isaac.bridge.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{
    
}
