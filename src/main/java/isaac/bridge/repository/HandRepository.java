package isaac.bridge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import isaac.bridge.entity.Hand;

public interface HandRepository extends JpaRepository<Hand, Integer>{

    Optional<List<Hand>> findAllByRoundId(int roundId);
}
