package isaac.bridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isaac.bridge.entity.Trick;

public interface TrickRepository extends JpaRepository<Trick, Integer>{
    
}
