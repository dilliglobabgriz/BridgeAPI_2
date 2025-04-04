package isaac.bridge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import isaac.bridge.entity.Trick;
import isaac.bridge.repository.TrickRepository;

@Service
public class TrickService {
    
    private TrickRepository trickRepository;

    public TrickService(TrickRepository trickRepository) {
        this.trickRepository = trickRepository;
    }

    public List<Trick> getTricksByRoundId(int roundId) {
        return trickRepository.findAllByRoundId(roundId).orElse(null);
    }
}
