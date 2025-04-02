package isaac.bridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isaac.bridge.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>{
    
}
