package isaac.bridge.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isaac.bridge.entity.Round;

@Repository
public interface RoundRepository extends JpaRepository<Round, Integer>{
    
    Optional<List<Round>> findAllByGameId(int gameId);
}
